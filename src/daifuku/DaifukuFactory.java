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

	public Main.Interaction create_interaction(Main context) {
		return null;
	}
}
