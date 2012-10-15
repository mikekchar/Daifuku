package daifuku;

import java.util.ResourceBundle;

/**
 * Creates an Interaction given a Context.
 * @author Mike Charlton
 *
 */
public class DaifukuFactory implements FactoryInterface {
	
	private ResourceBundle strings;
	public boolean showUI() {
        // This is a production factory, show the UI
		return true;
	}

    public String stringsBundleName() {
        return "example.Strings";
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
