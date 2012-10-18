package example;

import java.net.URL;
import java.lang.String;

import daifuku.Context;
import daifuku.ParentInterface;
import daifuku.FactoryInterface;

public class Main extends daifuku.Main {

    public interface Interaction extends daifuku.Main.Interaction {
        // Nothing to do
    }
    
    protected Interaction myInteraction;
    protected example.FactoryInterface myFactory;

    public Main(daifuku.ParentInterface aParent, example.FactoryInterface aFactory) {
        super(aParent, aFactory);
        myFactory = aFactory;
        setup_interaction();
    }

    @Override
    protected void setup_interaction() {
        myInteraction = myFactory.create_interaction(this);
    }

    /**
     * Return the interaction for this object
     */
    @Override
    public Context.Interaction getInteraction() {
        return (Context.Interaction)myInteraction;
    }

    @Override
    public URL getIconURL() {
        URL retVal = null;
        String resourcePath = myFactory.getString("IconResourcePath");
        // The resource path for the icon should be relative to the
        // factory since that is where it is defined.
        System.out.println(resourcePath);
        retVal = myFactory.getClass().getResource(resourcePath);

        return retVal;
    }

    @Override
    public String getWindowTitle() {
        return myFactory.getString("WindowName");
    }

}
