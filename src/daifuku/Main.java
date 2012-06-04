package daifuku;

import java.net.URL;
import java.lang.String;

/**
 * This is the Main Context.  
 * It corresponds to the main window frame in the application.  It provides
 * only one piece of functionality to the user -- to exit the application.
 * When this Context is closed, the application will exit.
 * The Main Context is also used to house all the other Contexts in
 * the application.
 *
 * @author Mike Charlton
 *
 */
public class Main extends Context {
	
	/**
	 * The Main.Interaction contains the main window frame widgets.
	 * It has no functionality other than to open and close.
	 * 
	 * @author Mike Charlton
	 *
	 */
	public interface Interaction extends Context.Interaction {
		// Nothing to do
	}

	public Main(InteractionFactory aFactory) {
		super(aFactory);
	}

	@Override
	public Context.Interaction create_interaction(){
		return getFactory().create_interaction(this);
	}

	/**
	 * When the Main Context exits, the Application should exit too.
	 */
	@Override 
	public void exit(){
		super.exit();
		
		getParent().exit();
	}

    /**
     * Return a URL to resource that contains an icon.
     * Override this in your own Main context.  It defaults to null
     * which means that there will be no icon.
     */
    public URL getIconURL() {
        return null;
    }

    /**
     * Return the title of the main window.
     * Override this in your own Main context.
     */
    public String getWindowTitle() {
        return "Main Window";
    }
}
