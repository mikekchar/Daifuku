package tests.main;

import static org.junit.Assert.*;
import tests.framework.Story;
import tests.mocks.ExampleFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * Story to show that an icon exists for the main Window
 * 
 * @author Mike Charlton
 *
 */
public class Icon extends Story {
	
	public Icon() {
		super(new ExampleFactory());
	}
	
	/**
	 * Masumi will be started on setUp and the Main Context will be entered.
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
	 * Example should have an icon in the main interaction.
	 */
	@Test
	public void the_Main_Interaction_displays_an_icon() {
		// When
		assertTrue(myExample.is_running());
        // Essentially asserting that the Strings resource is found
        assertTrue(myMainContext.getIconURL() != null);
		
		// It should
		assertTrue(((example.swing.Window)myMainInteraction).getIconImage() != null);
	}

}
