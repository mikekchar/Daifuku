package example;
import daifuku.Context;
import daifuku.Application;

import javax.swing.*;

/**
 * This is the Swing version of the main application.
 * 
 * @author Mike Charlton
 *
 */
public class Example extends Application {

    protected example.FactoryInterface myExampleFactory;

	public Example(example.FactoryInterface aFactory) {
		super(aFactory);
        myExampleFactory = aFactory;        
	}

	/**
	 * Creates a new MainContext.
	 */
    @Override
    protected Context create_main_context() {
	    return new Main(this, myExampleFactory);
	}

    public String currentLookAndFeel() {
        return UIManager.getLookAndFeel().getName();
    }

    /**
     * Default to the system look and feel.  
     * However, GTK+ seems to have bugs
     * on OpenJDK.  So if that is set, choose Nimbus instead (if it is available).
     */
    public void selectLookAndFeel() {
        String systemLAF = UIManager.getSystemLookAndFeelClassName();

        // The cross platform look and feel and the GTK+ look and feel have bugs in OpenJDK.
        // If these are selected, try to use Nimbus instead.
        if (systemLAF.equals("com.sun.java.swing.plaf.gtk.GTKLookAndFeel")) {
            try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) {
                try {
                    UIManager.setLookAndFeel(
                            UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e2) {
                    // Not much we can do except hope it works
                }
            }
        } else {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e2) {
                // Not much we can do except hope it works
            }
        }
    }

    @Override
    public Context run() {
        selectLookAndFeel();

        return super.run();
    }

	/**
	 * Starts up the Swing Example Application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Example example = new Example(new ExampleFactory());

		example.run();
	}
}
