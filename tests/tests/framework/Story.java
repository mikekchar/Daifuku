package tests.framework;

import example.MainInteraction;
import example.FactoryInterface;
import example.Main;

import tests.mocks.Example;

/**
 * Implements a context in which to test Daifuku.
 * 
 * @author Mike Charlton
 *
 */
public class Story {

	protected final Example myExample;
	protected Main myMainContext;
	protected MainInteraction myMainInteraction;
	
	protected Story(example.FactoryInterface aFactory) {
		myExample = new Example(aFactory);
		myMainContext = null;
		myMainInteraction = null;
	}
	
	/**
	 * Run example.  Usually this will be run by the setUp 
     * methods in the concrete classes.
	 */
    void runExample() {
		myMainContext = (Main)myExample.run();
		myMainInteraction = (MainInteraction)(myMainContext.getInteraction());
	}
	
	/**
	 * Example will be started on setUp and the Main Context will be entered.
	 * @throws java.lang.Exception
	 */
	public void setUp() throws Exception {
		runExample();
	}

	/**
	 * Make sure that Masumi has properly exited.  
     * Usually this will be run by the tearDown methods.
     * @throws java.lang.Exception
     */
	public void tearDown() throws Exception {
		if ((myMainContext != null) && (myMainContext.is_entered())) {
			myMainContext.exit();
		}
		myMainContext = null;
		myMainInteraction = null;
	}
}
