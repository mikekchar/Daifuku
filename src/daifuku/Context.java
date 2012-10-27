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

        // Free resources and indicate that the window can not be opened again.
        public void dispose();

        // Returns true if the interaction is open
		public boolean is_open();

        // Place the wigets in the passed interaction inside this one.
		public void add(Interaction anInteraction);

        // Remove the widgets in the passed interaction from this one.
		public void remove(Interaction anInteraction);

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
     * Creates the Interaction for this context.
     * The concrete class must implement this, but generally it will look like:
     * myInteraction = myFactory.create_interaction(this);
     * Make sure that myFactory is using the correct (highest level)
     * factory interface or else you will create the wrong kind of interaction.
     */
    public abstract void create_interaction();

    /**
     * Returns the Interaction for this context.
     * The concrete class must return this
     */
    public abstract Interaction getInteraction();

    /**
     * Dispose the Interaction.
     * This frees the resources for the interaction.  The interaction
     * can not be opened again unless create_interaction() is called.
     * The concrete class will likely want to override this functionality
     * in order to set their myInteraction reference to null.
     */
    public void dispose_interaction() {
        Interaction interaction = getInteraction();
        if (interaction != null) {
            interaction.dispose();
        }
    }

	/**
	 * Enter a new UI Context.
	 * It is important to realize that even though the new Context 
     * is entered, the old Context is still active.  There is 
     * no concept of "modal" Contexts.  However, a child
	 */
    public void enter() {
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
    public void exit() {
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
     * Returns true if the UI should be shown.
     */
    public boolean should_show_UI() {
        return myFactory.showUI();
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
