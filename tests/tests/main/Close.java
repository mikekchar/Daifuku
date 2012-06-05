/**
 * 
 */
package tests.main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import daifuku.swing.MainInteraction;
import tests.mocks.InteractionFactory;

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
		super(new InteractionFactory());
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
		((MainInteraction)myMainInteraction).request_closure();
		
		// It should
		assertFalse(myMainInteraction.is_open());
		assertFalse(myExample.is_running());
	}
}
