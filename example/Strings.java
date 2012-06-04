package example;

import java.util.ListResourceBundle;

/**
 * List of translateable strings used by the Contexts.
 * The UI will need to display strings to the users and these need to
 * be localized.  Some of these strings are related to the business
 * logic of the code rather than the GUI.  For example, there may
 * be default values, etc.
 *
 * This is an example.  Make a similar ResourceBundle in your project
 * and ten overload the stringsBundleName in the InteractionFactory to
 * point to it.
 */
public class Strings extends ListResourceBundle {

	private final static Object[][] contents = {
		{ "WindowName", "Daifuku Example Main Window" },
        { "IconResourcePath", "resources/icon.png" }
	};
	
	protected Object[][] getContents() {
		return contents;
	}

}
