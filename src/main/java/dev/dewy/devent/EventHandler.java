package dev.dewy.devent;

import dev.dewy.devent.api.Event;
import dev.dewy.devent.api.EventMethod;
import dev.dewy.devent.api.Listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventHandler implements Comparable<EventHandler>
{
    private final Listener listener;
    private final Method method;
    private final EventMethod annotation;

    public EventHandler(Listener listener, Method method, EventMethod annotation)
    {
        this.listener = listener;
        this.method = method;
        this.annotation = annotation;
    }

    public Listener getListener()
    {
        return listener;
    }

    public EventMethod getAnnotation()
    {
        return annotation;
    }

    public int getPriority()
    {
        return annotation.priority();
    }

    /**
     * Calls all methods handling a provided {@link Event}.
     *
     * @param event The event to "execute".
     * @throws IllegalStateException if it's not possible to invoke the event on a {@link Listener}.
     */
    public void executeEvent(Event event) throws IllegalStateException
    {
        try
        {
            method.invoke(listener, event);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
        {
            System.err.println("Oes noes! There was an exception when performing EventHandler " + this.listener + " for event " + event.toString() + ". How annoying!");
            e.printStackTrace();

            throw new IllegalStateException("Unable to call the method: " + method, e);
        }
    }

    @Override
    public String toString()
    {
        return "(DEVENT) Event: " + this.listener  + "\nMethod: " + method.getName();
    }

    @Override
    public int compareTo(EventHandler other)
    {
        int prioDiff = getAnnotation().priority() - other.getAnnotation().priority();

        if (prioDiff == 0)
        {
            prioDiff = getListener().hashCode() - other.getListener().hashCode();
        }

        return prioDiff == 0 ? hashCode() - other.hashCode() : prioDiff;
    }
}
