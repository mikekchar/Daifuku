package example;
import java.util.ResourceBundle;

import daifuku.DaifukuFactory;
import example.swing.Window;


/**
 * Implements the Swing version of the widgets for the Example.
 * 
 * @author Mike Charlton
 *
 */
public class ExampleFactory extends DaifukuFactory implements example.FactoryInterface {

    /**
     * Provides the name for the Strings resource bundle.
     */
    @Override    
    public String stringsBundleName() {
        return "example.Strings";
    }

    /**
     * Creates a Main Interaction.
     */
	public Main.Interaction create_interaction(Main context) {
		return new example.swing.Window(context);
	}
}
