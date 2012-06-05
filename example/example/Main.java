package example;

import java.net.URL;
import java.lang.String;

import daifuku.Context;

public class Main extends daifuku.Main {

    public interface Interaction extends daifuku.Main.Interaction {
        // Nothing to do
    }

    public Main(daifuku.InteractionFactory aFactory) {
        super(aFactory);
    }

    @Override
    public Context.Interaction create_interaction() {
        return getFactory().create_interaction((daifuku.Main)this);
    }

    @Override
    public URL getIconURL() {
        URL retVal = null;
        String resourcePath = getFactory().getString("IconResourcePath");
        // The resource path for the icon should be relative to the
        // factory since that is where it is defined.
        retVal = getFactory().getClass().getResource(resourcePath);

        return retVal;
    }

    @Override
    public String getWindowTitle() {
        return getFactory().getString("WindowName");
    }

}
