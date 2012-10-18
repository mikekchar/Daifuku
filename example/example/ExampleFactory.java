package example;
import java.util.ResourceBundle;

import daifuku.DaifukuFactory;
import example.Main;
import example.MainInteraction;

/**
 * Implements the Swing version of the widgets for the Example.
 * 
 * @author Mike Charlton
 *
 */
public class ExampleFactory extends DaifukuFactory implements example.FactoryInterface {

    @Override    
    public String stringsBundleName() {
        return "example.Strings";
    }

	public Main.Interaction create_interaction(Main context) {
		return new MainInteraction(context, this);
	}
}
