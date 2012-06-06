package example;

import example.Main;
import example.MainInteraction;

/**
 * Implements the Swing version of the widgets for the Example.
 * 
 * @author Mike Charlton
 *
 */
public class InteractionFactory extends daifuku.InteractionFactory {
	
	@Override
	public boolean showUI() {
        // This is a production factory, show the UI
		return true;
	}

    @Override
    public String stringsBundleName() {
        return "example.Strings";
    }

	public Main.Interaction create_interaction(Main context) {
		return new MainInteraction(context, this);
	}
}
