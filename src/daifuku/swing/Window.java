package daifuku.swing;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import daifuku.Context;
import daifuku.Main;

/**
 * A main window implemented with a Swing widget.
 * Since virtually all Swing applications need a main window,
 * this is a convenient class to extend.  However, you must
 * make sure to create a derived class that implements the
 * Interaction Interface for your application.
 *
 * Note: When adding and removing widgets, we downcast the
 * widget to a Component interface.  All Swing widgets implement
 * the Component interface, so this is safe to do.  With other
 * widget libraries, you might have to implement a similar
 * Interface.
 */
public class Window extends JFrame implements Main.Interaction {

	/**
	 * A Mediator to get events from the JFrame.
	 * 
	 * @author Mike Charlton
	 *
	 */
	private class FrameMediator extends WindowAdapter {
		private final Window myWindow;
		
		FrameMediator(Window aWindow) {
			myWindow = aWindow;
		}
		
		/**
		 * The windowClosing event simply requests that the window close.
		 */
		@Override
		public void windowClosing(WindowEvent arg0) {
            // Note: Do NOT call myWIndow.close() here.  The context
            // needs to dispose of the interaction, so all requests
            // must be routed through the context.
			myWindow.request_closure();
		}
		
	}

	private static final long serialVersionUID = 781198792258981628L;

	private ImageIcon myIcon;
    private final Main myContext;
    private boolean amIOpen;

	public Window(Main aContext) {
		super();
        myContext = aContext;
        amIOpen = false;
        setSize(300, 400);
	
        // Request window details from the interaction    
		setTitle(myContext.getWindowTitle());
        setIconPath(myContext.getIconURL());

        // The FrameMediator is handling the close message
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new FrameMediator(this));
	}

    /**
     * Set the resources for the icon.
     */
   public void setIconPath(URL anIconURL) {
       if (null != anIconURL) { 
           myIcon = new ImageIcon(anIconURL);
           setIconImage(myIcon.getImage());
       }
   } 

	/**
	 * Request that the window close.
	 * This is called by the FrameMediator.
     * At the moment we want the application to quit here, but
     * this can be overridden in case you want to exit the Main
     * Context, but want to keep the application running (if the
     * application continues on in the systray, for example).
     * In that case, simply override this to 
     *     myContext.exit();
	 */
	public void request_closure() {
		myContext.quit();
	}

    /**
     * Open the window frame.
     * Make it visible if the UI is being shown at the moment.
     */
    public void open() {
        amIOpen = true;
        setVisible(myContext.should_show_UI());
    }

	/**
	 * Actually close the window.
     * Note: The window is not disposed, so it can be opened again.
	 */
	public void close() {
		setVisible(false);
		amIOpen = false;
	}

	/**
	 * Returns true if the frame is visible.
     * Note: We can't just return the visibility of the frame because
     * tests that don't show the UI will fail.
	 */
	public boolean is_open() {
		return amIOpen;
	}

   /**
    * Add a widget to the contents of the pane.
    */
	public void add(Context.Interaction anInteraction) {
		this.getContentPane().add((Component)anInteraction);
	}

    /**
     * Remove a widget from the contents of the pane
     */
	public void remove(Context.Interaction anInteraction) {
		this.getContentPane().remove((Component)anInteraction);
	}

    /**
     * Returns true if the pane contains the widget
     */
	public boolean contains(Context.Interaction anInteraction) {
		boolean retVal;

        retVal= false;
		Component[] components = this.getContentPane().getComponents();
        for (Component component : components) {
            if (component == anInteraction) {
                retVal = true;
                break;
            }
        }
		return retVal;
	}
}
