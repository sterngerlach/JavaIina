
/* App.java */

package javaiina;

import java.util.Arrays;
import java.util.Optional;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

public class App
{
    public static String ApplicationName = "JavaIina";
    
    public static void main(String[] args)
    {
        /* Call App.initializeLookAndFeel() if you like Windows look and feel */
        // App.initializeLookAndFeel();
        
        MainModel mainModel = new MainModel();
        MainView mainView = new MainView(App.ApplicationName);
        mainView.setModel(mainModel);
        MainController mainController = new MainController(mainView, mainModel);
        
        mainView.setVisible(true);
        
        Runtime.getRuntime().addShutdownHook(new Thread(
            () -> mainModel.getDBManager().saveData()));
    }
    
    private static void initializeLookAndFeel()
    {
        LookAndFeelInfo[] installedLookAndFeels = UIManager.getInstalledLookAndFeels();
        
        Optional<LookAndFeelInfo> expectedLookAndFeel =
            Arrays.stream(installedLookAndFeels)
                .filter(lookAndFeel -> lookAndFeel.getName().contains("Windows"))
                .findFirst();
        
        if (!expectedLookAndFeel.isPresent())
            return;
        
        try {
            UIManager.setLookAndFeel(expectedLookAndFeel.get().getClassName());
        } catch (
            ClassNotFoundException | InstantiationException |
            IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("App.initializeLookAndFeel() failed.");
            return;
        }
    }
}
