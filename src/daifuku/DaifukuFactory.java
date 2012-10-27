package daifuku;

import java.util.ResourceBundle;

/**
 * Implementation of a Factory Interface.
 *
 * This implementation uses ResourceBundles to translate strings.
 * You must override stringsBundleName() to provide the path
 * to the bundle.
 *
 * By default, the UI is shown to the user.  If you wish to use
 * this factory for tests, you should probably override showUI()
 * to return false.
 *
 * Finally there is an example of how to create a method for instantiating
 * a Main.Interaction.  It returns null, because nobody should be using
 * a daifuku.Main.Interaction.  
 *
 * @author Mike Charlton
 *
 */
public abstract class DaifukuFactory implements FactoryInterface {
	
	private ResourceBundle strings;

    public DaifukuFactory() {
        String bundleName = stringsBundleName();

        if (bundleName != null) {
            loadStrings(stringsBundleName());
        }
    }

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
    public abstract String stringsBundleName();

    /** 
     * Load the string resources for these Contexts.
     */
	public void loadStrings(String bundleName) {
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
