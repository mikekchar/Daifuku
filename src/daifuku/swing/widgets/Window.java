package daifuku.swing.widgets;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.lang.String;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import daifuku.WidgetInterface;
import daifuku.swing.MainInteraction;

public class Window extends JFrame implements WidgetInterface {

	/**
	 * A Mediator to get events from the JFrame
	 * 
	 * @author Mike Charlton
	 *
	 */
	private class FrameMediator extends WindowAdapter {
		private final MainInteraction myInteraction;
		
		FrameMediator(MainInteraction theInteraction) {
			myInteraction = theInteraction;
		}
		
		/**
		 * The windowClosing event simply requests that the interaction close
		 */
		@Override
		public void windowClosing(WindowEvent arg0) {
			myInteraction.request_closure();
		}
		
	}

	private static final long serialVersionUID = 781198792258981628L;

	private ImageIcon myIcon;
    private final MainInteraction myInteraction;

	public Window(MainInteraction anInteraction, int xSize, int ySize) {
		super();
        myInteraction = anInteraction;
        setSize(xSize, ySize);
	
        // Request window details from the interaction    
		setTitle(anInteraction.request_title());
        setIconPath(anInteraction.request_icon_URL());

        // The FrameMediator is handling the close message
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new FrameMediator(anInteraction));
	}

    /**
     * Set the resources for the icon
     */
   public void setIconPath(URL anIconURL) {
       if (null != anIconURL) { 
           myIcon = new ImageIcon(anIconURL);
           setIconImage(myIcon.getImage());
       }
   } 

   /**
    * Add a widget to the contents of the pane
    */
	public void add(WidgetInterface aWidget) {
		this.getContentPane().add((Component) aWidget);
	}

    /**
     * Remove a widget from the contents of the pane
     */
	public void remove(WidgetInterface aWidget) {
		this.getContentPane().remove((Component) aWidget);
	}

    /**
     * Returns true if the pane contains the widget
     */
	public boolean contains(WidgetInterface aWidget) {
		boolean retVal;

        retVal= false;
		Component[] components = this.getContentPane().getComponents();
        for (Component component : components) {
            if (component == aWidget) {
                retVal = true;
                break;
            }
        }
		return retVal;
	}
	
}
