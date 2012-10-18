package example;

/**
 * Implements the Swing version of the widgets for the Example.
 * 
 * @author Mike Charlton
 *
 */
public interface FactoryInterface extends daifuku.FactoryInterface {

	public Main.Interaction create_interaction(Main context);
}

