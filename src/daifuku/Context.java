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
	final InteractionFactory myFactory;
	final Interaction myInteraction;
	Context myParent;

	Context(InteractionFactory aFactory) {
        myFactory = aFactory;
		haveIEntered = false;
		myParent = null;
		myInteraction = create_interaction();
	}

	/**
	 * Creates the  concrete Interaction for the Context.
	 * Each Context must implement the code:
	 * <code>
	 * 			factory.create_interaction(this);
	 * </code>
	 * @return the concrete Interaction
	 * TODO Try to do using generics.
	 */
	protected abstract Interaction create_interaction();

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
    void enter(Context aParent) {
		haveIEntered = true;
		myParent = aParent;
		if (myInteraction != null) {
			myInteraction.open();
			if (myParent.myInteraction != null) {
				myParent.myInteraction.add(myInteraction);
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
			if(myInteraction != null) {
				if (myParent.myInteraction != null) {
					myParent.myInteraction.remove(myInteraction);
				}
				myInteraction.close();
			}
			haveIEntered = false;
		}
	}
	

	/**
	 * @return the interaction for this context.
	 */
	public Interaction getInteraction() {
		return myInteraction;
	}

    /**
     * Return the factory for this context.
     * Note: You *must* downcast this to your actual type when you use it!
     * Otherwise you will only be able to create the Main Context.
     */
    public InteractionFactory getFactory() {
        return myFactory;
    }

    /**
     * Return the parent for this context.
     */
    public Context getParent() {
        return myParent;
    }
}
