package example;

/**
 * Implements the Swing version of the widgets for the Example.
 * 
 * @author Mike Charlton
 *
 */
public interface FactoryInterface extends daifuku.FactoryInterface {

    /**
     * Create the interaction for the Example version of the Main context.
     */
	public example.Main.Interaction create_interaction(example.Main context);
}

