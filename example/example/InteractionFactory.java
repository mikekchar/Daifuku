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
public class InteractionFactory extends DaifukuFactory {

    @Override    
    public String stringsBundleName() {
        return "example.Strings";
    }

    @Override
	public daifuku.Main.Interaction create_interaction(daifuku.Main context) {
		return new MainInteraction((example.Main)context, this);
	}
}
