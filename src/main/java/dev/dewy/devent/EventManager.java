package dev.dewy.devent;

import dev.dewy.devent.api.Event;
import dev.dewy.devent.api.EventMethod;
import dev.dewy.devent.api.Listener;

import java.lang.reflect.Method;
import java.util.*;

@SuppressWarnings("unchecked")
public class EventManager
{
    public boolean debugMode = false;

    public static final int PRE = -1;
    public static final int ALL = 0;
    public static final int POST = 1;

    private final Map<Class<? extends Event>, Collection<EventHandler>> eventBindings;
    private final Set<Listener> listenersRegistry;

    private static final EventHandler[] NO_HANDLERS = {};

    public EventManager()
    {
        eventBindings = new HashMap<>();
        listenersRegistry = new HashSet<>();
    }

    public void setDebugMode(boolean debugMode)
    {
        this.debugMode = debugMode;
    }

    public void clear()
    {
        eventBindings.clear();
        listenersRegistry.clear();
    }

    public EventHandler[] getListenersFor(Class<? extends Event> cls)
    {
        Collection<EventHandler> handlers = eventBindings.get(cls);

        if (handlers == null || handlers.isEmpty())
        {
            return NO_HANDLERS;
        }

        return handlers.toArray(new EventHandler[handlers.size()]);
    }

    public <T extends Event> T execute(T event, int type)
    {
        Collection<EventHandler> handlers = eventBindings.get(event.getClass());

        if (handlers == null)
        {
            if (debugMode)
            {
                System.err.println("The event " + event.getClass().getSimpleName() + " has no event handlers.");
            }

            return event;
        }

        if (debugMode)
        {
            System.out.println("Event " + event.getClass().getSimpleName() + " has " + handlers.size() + " event handlers.");
        }

        for (EventHandler handler : handlers)
        {
            if (type == PRE && handler.getPriority() >= 0)
            {
                continue;
            }

            if (type == POST && handler.getPriority() < 0)
            {
                continue;
            }

            handler.executeEvent(event);
        }

        return event;
    }

    public <T extends Event> T execute(T event)
    {
        return execute(event, ALL);
    }

    public void registerListener(final Listener listener)
    {
        System.out.println("Attempting to register event listener " + listener);

        if (listenersRegistry.contains(listener))
        {
            System.err.println("Event listener already registered: " + listener);

            return;
        }

        Method[] methods = listener.getClass().getDeclaredMethods();
        listenersRegistry.add(listener);

        for (final Method method : methods)
        {
            EventMethod annotation = method.getAnnotation(EventMethod.class);

            if (annotation == null)
            {
                continue;
            }

            Class<?>[] params = method.getParameterTypes();

            if (params.length != 1)
            {
                continue;
            }

            Class<?> param = params[0];

            if (!method.getReturnType().equals(void.class))
            {
                System.err.println("Ignoring event method because it doesn't return void: " + method.getName());

                continue;
            }

            if (Event.class.isAssignableFrom(param))
            {
                Class<? extends Event> eventParam = (Class<? extends Event>) param;

                if (!eventBindings.containsKey(eventParam))
                {
                    eventBindings.put(eventParam, new TreeSet<>());
                }

                Collection<EventHandler> eventHandlersForEvent = eventBindings.get(eventParam);
                System.out.println("Added event listener method " + method.getName() + " for event " + eventParam.getSimpleName());

                eventHandlersForEvent.add(createEventHandler(listener, method, annotation));
            }
        }
    }

    public void removeListener(Listener listener)
    {
        for (Map.Entry<Class<? extends Event>, Collection<EventHandler>> registryEntry : eventBindings.entrySet())
        {
            registryEntry.getValue().removeIf(current -> current.getListener() == listener);
        }

        listenersRegistry.remove(listener);
    }

    private EventHandler createEventHandler(final Listener listener, final Method method, final EventMethod annotation)
    {
        return new EventHandler(listener, method, annotation);
    }
}
