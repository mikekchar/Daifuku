package daifuku;

/**
 * This is a user interaction context.
 * Basically, all user interaction is grouped into a Context.  
 * The Context contains all the model objects that are necessary 
 * to provide the functionality that the user needs in this context.  
 * The Context also contains a single interaction that defines
 * what information can be sent to and from the user.
 * 
 * @author Mike Charlton
 *
 */
public abstract class Context {
	
	/**
	 * The information or actions that can be sent to or from a user.
	 * Generally speaking an interaction will be associated with one or more
	 * UI widgets that the user can interact with.  The interaction defines how
	 * information passes to and from the UI.
	 * 
	 * @author Mike Charlton
	 *
	 */
	public interface Interaction {
        // Display the interaction to the user
		public void open();

        // Hide the interaction from the user
		public void close();

        // Returns true if the interaction is open
		public boolean is_open();

        // Place the wigets in the passed interaction inside this one.
		public void add(Interaction anInteraction);

        // Remove the widgets in the passed interaction from this one.
		public void remove(Interaction anInteraction);

        // Return the main container Widget for this interaction.
		public Widget getWidget();

        // Returns true if this interaction contains the passed interaction.
		public boolean contains(Interaction anInteraction);
	}
	
	private boolean haveIEntered;
	final FactoryInterface myFactory;
	final ParentInterface myParent;

	Context(ParentInterface aParent, FactoryInterface aFactory) {
		haveIEntered = false;
		myParent = aParent;
        myFactory = aFactory;
	}

    /**
     * Returns the Interaction for this context.
     * The concrete class must return this
     */
    public abstract Interaction getInteraction();

	/**
	 * Enter a new UI Context.
	 * It is important to realize that even though the new Context 
     * is entered, the old Context is still active.  There is 
     * no concept of "modal" Contexts.  However, a child
	 * Context must be able to request functionality from 
     * a parent, and so the parent is kept.  Ideally, the parent 
     * should not contact the child except for entering it.
	 * It is common for a child Context to call exit() on the parent Context.
	 * 
	 * @param aParent The context that was active when this Context is entered.
	 */
    void enter() {
		haveIEntered = true;
		if (getInteraction() != null) {
			getInteraction().open();
			if (myParent.getInteraction() != null) {
				myParent.getInteraction().add(getInteraction());
			}
		}
	}

	/**
	 * @return true if the Context has been entered but not exited.
	 */
	public boolean is_entered() {
		return haveIEntered;
	}

	/**
	 * Exit the Context.
	 * This will close the interaction if it isn't already closed.
	 */
    void exit() {
		if (is_entered()) {
			if(getInteraction() != null) {
				if (myParent.getInteraction() != null) {
					myParent.getInteraction().remove(getInteraction());
				}
				getInteraction().close();
			}
			haveIEntered = false;
		}
	}

    /**
     * Return the factory for this context.
     */
    public FactoryInterface getFactory() {
        return myFactory;
    }

    /**
     * Return the parent for this context.
     */
    public ParentInterface getParent() {
        return myParent;
    }
}
