package daifuku;

/**
 * The Application.
 * The Application only exists to setup the correct MainContext and
 * FactoryInterface.  On entry, it enters the MainContext and has
 * no other functionality until it exits.
 * You can test to see if the Application is running 
 * (has not exited the Main Context yet) by calling is_running().
 * 
 * @author Mike Charlton
 *
 */
public abstract class Application implements ParentInterface {
	
	protected boolean amIRunning;
    protected FactoryInterface myFactory;
    protected Context myMainContext;
	
	public Application(FactoryInterface aFactory) {
		myFactory = aFactory;
		amIRunning = false;
	}

    /**
     * Returns the version information for this application.
     * @return the version
     */
    public String version() {
        return Application.class.getPackage().getImplementationVersion();
    }
	
	/**
	 * Creates a new MainContext.
     * This must be overridden to setup the context before entering it.
	 */
    protected abstract Context create_main_context();
	
	/**
	 * Creates a new main context and enters it.  
     * Returns the context it created.
	 */
	public Context run() {
		myMainContext = create_main_context();
		amIRunning = true;
        myMainContext.create_interaction();
		myMainContext.enter();
		return myMainContext;
	}
	
	/**
     * Exit the application.
	 * This will be called by the main context when it exits.  
     * This is called by Context.exit() so if you want to do 
     * anything before exiting in the Main Context you must do it before
	 * calling super().
	 */
	public void exit() {
		amIRunning = false;
        if (myMainContext != null) {
            myMainContext.dispose_interaction();
            myMainContext = null;
        }
		exit_system();
	}

	/**
	 * Quits everything.  
     * This method can be over ridden in the tests to ensure that
	 * the application actually quits, without actually quitting 
     * the application.
	 */
    protected void exit_system() {
		System.exit(0);
	}

	/**
	 * Returns true if the application is currently running.
	 */
	public boolean is_running() {
		return amIRunning;
	}
	
	/**
	 * The Application has no interaction so it returns null
	 */
	public Context.Interaction getInteraction() {
		return null;
	}

}
