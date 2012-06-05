package tests.startup;

import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import tests.Story;
import tests.mocks.InteractionFactory;

/**
 * Story to describe what happens when you start the Example
 * 
 * @author Mike Charlton
 *
 */

public class Startup extends Story{

	public Startup() {
		super(new InteractionFactory());
	}

	@Before
	public void before() throws Exception {
		setUp();
	}
	
	@After
	public void after() throws Exception {
		tearDown();
	}

	/**
	 * What should happen when the Example is first started.
	 */
	@Test
	public void running_Example_opens_the_main_interaction() {
		// When
		assertTrue(myExample.is_running());
		
		// It should
		assertTrue(myMainContext.is_entered());
		assertTrue(myMainInteraction.is_open());
	}
	
	@Test
	public void example_should_have_a_build_version() {
		// When
		assertTrue(myExample.is_running());
		
		// It should
		String version = myExample.version();
		// The version is null unless it is in the Jar.  Not sure 
        // how I should test this.
		assertTrue(version == null);
	}

    @Test
    public void test_look_and_feel_should_not_be_Metal_or_GTK() {
        // TODO: This will fail unless you have the Nimbus LAF
        // installed, so I should probably remove this test...
		// When
		assertTrue(myExample.is_running());

		// It should
		String lookAndFeel = myExample.currentLookAndFeel();
        assertFalse(lookAndFeel.equals("Metal"));
        assertFalse(lookAndFeel.equals("GTK look and feel"));
    }
}
