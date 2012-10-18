package example;

import example.Main;

/**
 * This is the main window for the application.
 * We need to create this even though it is the same as the daifuku
 * version because we need to implement the Interaction on the
 * example version of Main.
 */
public class MainInteraction extends daifuku.swing.MainInteraction implements Main.Interaction {

	public MainInteraction(Main aContext, example.FactoryInterface aFactory) {
        super(aContext, aFactory);
	}
}
