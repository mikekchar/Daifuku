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

    public Main(daifuku.ParentInterface aParent, daifuku.FactoryInterface aFactory) {
        super(aParent, aFactory);
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
