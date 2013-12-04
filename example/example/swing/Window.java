package example.swing;

/**
 * This is the main window for the application.
 * We need to create this even though it is the same as the daifuku
 * version because we need to implement the Interaction on the
 * example version of Main.
 */
public class Window extends daifuku.swing.Window implements example.Main.Interaction {
	
	private static final long serialVersionUID = 781198792258981628L;
	
    public Window(example.Main aContext) {
        super(aContext);
    }
}
