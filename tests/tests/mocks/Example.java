package tests.mocks;

import daifuku.Application;
import example.FactoryInterface;

/**
 * Mock for Example.
 * 
 * The application exits the system on exit.  Of course we don't want 
 * to do that in tests, so we override the method here.
 * 
 * @author Mike Charlton
 *
 */
public class Example extends example.Example {
	
	public Example(example.FactoryInterface aFactory){
		super(aFactory);
	}
	
	@Override
	public void exit_system() {
		// Don't exit in the tests
	}

}
