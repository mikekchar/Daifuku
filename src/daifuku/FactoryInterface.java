package daifuku;

/**
 * Creates an Interaction given a Context.
 * This is an interface.
 * The Interactions are implemented separately from the Context.  This
 * is for two reasons.  First, it creates a separation from the business
 * logic of the application (which exists in the Context) and the
 * actual GUI (which exists in the Interaction).  Secondly, it allows
 * us to implement the GUI using more than one widget library without
 * changing the business logic.  Unfortunately, creating this separation
 * means that we need to instantiate the Interactions separately from
 * the Contexts. 
 *
 * The InteractionFactory also currently acts as a place to hold
 * string resources for all the Contexts.
 * TODO: It makes some sense to put it here, but something smells bad. 
 * 
 * @author Mike Charlton
 *
 */
public interface FactoryInterface {
    /** 
     * Load the string resources for these Contexts.
     */
    public void loadStrings();

    /**
     * Bundle resource class name for strings in the application.
     */
    public String stringsBundleName();

    /**
     * Return the correct string for a given key
     */
	public String getString(String key);
	 
	/**
     * Returns true if the Interactions should display on the screen.
	 * There are times (when testing for instance) when we don't 
     * want to display the UI to the user.  This method should be 
     * called by open() on the interaction before displaying the UI.
	 * @return true if the UI should be shown.
	 */
	public boolean showUI();

    /**
     * The default FactoryInteraction demands that you can create
     * an interaction for a Main context.
     */
	public Main.Interaction create_interaction(Main context);
}
