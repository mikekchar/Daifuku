package daifuku;

import java.util.ResourceBundle;

/**
 * Creates an Interaction given a Context.
 * @author Mike Charlton
 *
 */
public class DaifukuFactory implements FactoryInterface {
	
	private ResourceBundle strings;

    /**
     * Returns true if the UI should be shown.
     * This is useful in tests.  If you mock the factory you can turn
     * off the UI and run the tests without a display.
     */
	public boolean showUI() {
        // By default show the UI
		return true;
	}

    /**
     * Return the name of the string Resource.
     * This must be overriden in the concrete classes.
     */
    public String stringsBundleName() {
        return null;
    }

    /** 
     * Load the string resources for these Contexts.
     */
	public void loadStrings() {
		strings = ResourceBundle.getBundle(stringsBundleName());
	}
	

    /**
     * Return the correct string for a given key
     */
	public String getString(String key) {
		return strings.getString(key);
	}

    /**
     * Create an Interaction for the daifuku.Main context.
     * Note: This returns null because I don't want people
     * accidently creating an Interaction for daifuku.Main
     * rather than their concrete Main class.
     * TODO: Perhaps I should just remove this, but I want
     * to show an example of how to construct it.
     */
	public Main.Interaction create_interaction(Main context) {
		return null;
	}
}
