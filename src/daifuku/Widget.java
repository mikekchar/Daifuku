package daifuku;

/**
 * A Widget represents the main UI container for an Interaction.
 * The main reason for having a Widget is simply to abstract the
 * layout of UI components.  When an Interaction is opened, its
 * UI must be added to the container of its parent.  Thus each
 * Interaction has exactly one Widget that must be able to
 * contain other widgets.  Exactly how they are laid out is
 * dependent upon the actual Interaction.
 * 
 * @author Mike Charlton
 *
 */
public interface Widget {
    // Add a widget from another Interaction to this one
	public void add(Widget aWidget);

    // Remove a widget from another Interaction from this one
	public void remove(Widget aWidget);

    // Returns true if this widget contains aWidget
	public boolean contains(Widget aWidget);
}
