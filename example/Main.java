package example;

import java.net.URL;
import java.lang.String;

import daifuku.Context;

public class Main extends daifuku.Main {

    public interface Interaction extends daifuku.Main.Interaction {
        // Nothing to do
    }

    public Main(InteractionFactory aFactory) {
        super(aFactory);
    }

    @Override
    public Context.Interaction create_interaction() {
        return getFactory().create_interaction((daifuku.Main)this);
    }

    @Override
    public URL getIconURL() {
        String resourcePath = getFactory().getString("IconResourcePath");
		URL url = getInteraction().getClass().getResource(resourcePath);
        return url;
    }

    @Override
    public String getWindowTitle() {
        return getFactory().getString("WindowName");
    }

}
