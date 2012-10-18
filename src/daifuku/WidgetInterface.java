package daifuku;

/**
 * A WidgetInterface represents the main UI container for an Interaction.
 * The main reason for having a WidgetInterface is simply to abstract the
 * layout of UI components.  When an Interaction is opened, its
 * UI must be added to the container of its parent.  Thus each
 * Interaction has exactly one WidgetInterface that must be able to
 * contain other widgets.  Exactly how they are laid out is
 * dependent upon the actual Interaction.
 * 
 * @author Mike Charlton
 *
 */
public interface WidgetInterface {
    // Add a widget from another Interaction to this one
	public void add(WidgetInterface aWidget);

    // Remove a widget from another Interaction from this one
	public void remove(WidgetInterface aWidget);

    // Returns true if this widget contains aWidget
	public boolean contains(WidgetInterface aWidget);
}
