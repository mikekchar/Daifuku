package example;

import java.net.URL;
import java.lang.String;

import daifuku.Context;

/**
 * This is the Main Context for the Example.
 * This is the concrete Main Context class.
 *
 * @author Mike Charlton
 */
public class Main extends daifuku.Main {

    public interface Interaction extends daifuku.Main.Interaction {
        // Nothing to do
    }
    
    protected Interaction myInteraction;
    protected example.FactoryInterface myExampleFactory;

    public Main(daifuku.ParentInterface aParent, example.FactoryInterface aFactory) {
        super(aParent, aFactory);
        myExampleFactory = aFactory;
    }

    @Override
    public void create_interaction() {
        myInteraction = myExampleFactory.create_interaction(this);
    }

    @Override
    public void dispose_interaction() {
        super.dispose_interaction();
        myInteraction = null;
    }

    /**
     * Return the interaction for this object.
     * This is used by the Context class
     */
    @Override
    public Context.Interaction getInteraction() {
        return (Context.Interaction)myInteraction;
    }

    /**
     * Return the URL for the Icon.
     * This is a service for the Interaction
     **/
    @Override
    public URL getIconURL() {
        URL retVal = null;
        String resourcePath = myExampleFactory.getString("IconResourcePath");
        // The resource path for the icon should be relative to the
        // factory since that is where it is defined.
        retVal = myExampleFactory.getClass().getResource(resourcePath);

        return retVal;
    }

    /**
     * Return the URL for the Icon.
     * This is a service for the Interaction
     **/
    @Override
    public String getWindowTitle() {
        return myExampleFactory.getString("WindowName");
    }

}
