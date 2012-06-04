package daifuku.swing;

import java.awt.Image;
import java.net.URL;
import java.lang.String;

import daifuku.Context;
import daifuku.InteractionFactory;
import daifuku.Main;
import daifuku.Widget;
import daifuku.swing.widgets.Window;

/**
 * This is the main window for the application.
 */
public class MainInteraction implements Main.Interaction {

	private final Main myContext;
	private boolean amIOpen;
	private final Window myWidget;
	private final InteractionFactory myFactory;

	public MainInteraction(Main aContext, InteractionFactory aFactory) {
		myFactory = aFactory;
		myContext = aContext;
		amIOpen = false;
		myWidget = new Window(this, 300, 400);
	}

    /**
     * returns true if the UI should be shown
     */    
	public boolean showUI() {
		return myFactory.showUI();
	}

	/**
	 * Open the window frame.
	 */
	public void open() {
		amIOpen = true;
		myWidget.setVisible(showUI());
	}

    /**
     * Retuns the icon image of the Window
     */    
	public Image get_icon_image() {
		return myWidget.getIconImage();
	}
	
	/**
	 * Actually close the window.
     * Note: The window is disposed, so it can't be opened again.
     * TODO: Move dispose somewhere else.
	 */
	public void close() {
		myWidget.setVisible(false);
        myWidget.dispose();
		amIOpen = false;
	}

	/**
	 * Returns true if the frame is visible.
	 */
	public boolean is_open() {
		return amIOpen;
	}

	/**
	 * Request that the interaction close.
	 * This is called by the widget itself.  You mustn't close the 
     * interaction directly here because the Context needs to dispose 
     * of the interaction.  So we ask the Context to exit which
	 * will close the Interaction.
	 */
	public void request_closure() {
		myContext.exit();
	}

    public String request_title() {
        return myContext.getWindowTitle();
    }

    public URL request_icon_URL() {
        return myContext.getIconURL();
    }

    /**
     * Add the widget from the interaction to the window.
     */
	public void add(Context.Interaction anInteraction) {
		getWidget().add(anInteraction.getWidget());
	}

    /**
     * Returns true if the Window contains the widget for the interaction
     */
	public boolean contains(Context.Interaction anInteraction) {
		return getWidget().contains(anInteraction.getWidget());
	}

    /**
     * Returns the Window
     */
	public Widget getWidget() {
		return myWidget;
	}

    /**
     * Remove the widget for the interaction if it is in the window.
     */
	public void remove(Context.Interaction anInteraction) {
		getWidget().remove(anInteraction.getWidget());
	}

    /**
     * FIXME: I'm not sure what this does.
     */
	public void update() {
		myWidget.validate();
	}
}
