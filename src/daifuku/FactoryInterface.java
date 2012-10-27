package daifuku;

/**
 * An Interface for creating UI specific ojects.
 *
 * The factory allows the creation of concrete objects of
 * the correct type for the UI that is being used.  We may want
 * to create different UIs for different devices or even
 * just different locales.  We may need to even use different
 * widget libraries for different UIs. This interface allows
 * us to abstract the creation of the concrete UI objects.
 *
 * There are three different kinds of UI abstraction here.
 * The first is for strings.  The concrete factory must
 * provide a method to look up string translations.
 *
 * The second is for test UI's.  The concrete factory must
 * determine if the UI should be visible to the user or not.
 *
 * The third is for the construction of the concrete
 * Interactions.
 * 
 * @author Mike Charlton
 *
 */
public interface FactoryInterface {

    /**
     * Return the correct translation string for a given key
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
