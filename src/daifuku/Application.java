package daifuku;

/**
 * The Application Context.
 * The Application only exists to setup the correct MainContext and
 * InteractionFactory.  On entry, it enters the MainContext and has
 * no other functionality until it exits.
 * You can test to see if the Application is running 
 * (has not exited the Main Context yet) by calling is_running().
 * 
 * @author Mike Charlton
 *
 */
public class Application extends Context {
	
	protected boolean amIRunning;
	
	protected Application(InteractionFactory aFactory) {
		super(aFactory);
		amIRunning = false;
		aFactory.loadStrings();
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
     * This can be overridden to setup the context before entering it.
	 */
    Context create_main_context(InteractionFactory aFactory) {
	    return new Main(aFactory);
	}
	
	/**
	 * Creates a new main context and enters it.  
     * Returns the context it created.
	 */
	public Context run() {
		Context context = create_main_context(getFactory());
		amIRunning = true;
		context.enter(this);
		return context;
	}
	
	/**
     * Exit the application.
	 * This will be called by the main context when it exits.  
     * This is called by Context.exit() so if you want to do 
     * anything before exiting in the Main Context you must do it before
	 * calling super().
	 */
	@Override
	public void exit() {
		amIRunning = false;
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
	@Override
	public Interaction create_interaction() {
		return null;
	}

}
