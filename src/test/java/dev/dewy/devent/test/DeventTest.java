package dev.dewy.devent.test;

import dev.dewy.devent.EventManager;
import dev.dewy.devent.api.EventMethod;
import dev.dewy.devent.api.Listener;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * A simple unit test to show Devent in action.
 *
 * @author iBuyMountainDew
 */
public class DeventTest implements Listener
{
    private EventManager events;
    private int responses;

    @Before
    public void before()
    {
        events = new EventManager();
        events.registerListener(this);
    }

    @Test
    public void test()
    {
        Assert.assertEquals(0, responses);
        events.execute(new SampleEvent());
        Assert.assertEquals(1, responses);
    }

    @EventMethod
    public void onSampleEvent(SampleEvent e)
    {
        responses++;

        System.out.println("pp smol i work lul");
    }
}
