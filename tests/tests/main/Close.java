/**
 * 
 */
package tests.main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import tests.mocks.ExampleFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.framework.Story;

/**
 * Story to describe what happens when you close the Example
 * 
 * @author Mike Charlton
 *
 */
public class Close extends Story {
	
	public Close() {
		super(new ExampleFactory());
	}
	
	/**
	 * Example will be started on setUp and the Main Context will be entered.
	 * @throws java.lang.Exception
	 */
	@Before
	public void before() throws Exception {
		setUp();
	}

	/**
	 * At the end of each test, reset the test
	 * @throws java.lang.Exception
	 */
	@After
	public void after() throws Exception {
		tearDown();
	}

	/**
	 * How do we close the Example?
	 */
	@Test
	public void closing_the_main_interaction_exits_Example() {
		// When
		assertTrue(myExample.is_running());
		((example.swing.Window)myMainInteraction).request_closure();
		
		// It should
		assertFalse(myMainInteraction.is_open());
		assertFalse(myExample.is_running());
	}

    /**
     * Simply exiting the main context closes the window but
     * doesn't exit the application.
     * If we enter the context again, the window opens.
     */
	@Test
	public void exiting_the_context_doesnt_exit_the_application() {
		// When
		assertTrue(myExample.is_running());
        myMainContext.exit();
		
		// It should
        assertFalse(myMainContext.getInteraction() == null);
		assertFalse(myMainInteraction.is_open());
		assertTrue(myExample.is_running());

        // Furthermore, when
        myMainContext.enter();

        // It should
        assertFalse(myMainContext.getInteraction() == null);
		assertTrue(myMainInteraction.is_open());
		assertTrue(myExample.is_running());
	}

}
