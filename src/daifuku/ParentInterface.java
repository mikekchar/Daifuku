package daifuku;

/**
 * Define what methods parent Contexts must implement.
 * Contexts are entered and their parent is recorded. This is so that
 * the child context can make requests of the parent context.  A
 * good example is to be able to tell the rest of the application
 * to exit.
 */
public interface ParentInterface {

    /**
     * Return the interaction for the parent.
     * This is required whan a context wants to place the widget from
     * their interaction into the parent's widget.  Be warned that
     * this method may return null.
     */
    Context.Interaction getInteraction();

    /**
     * Signal that the application should exit.
     * When this method is called on the Application, it will exit.
     * Other contexts should simply forward the request up the
     * chain.
     */
    void exit();
}
