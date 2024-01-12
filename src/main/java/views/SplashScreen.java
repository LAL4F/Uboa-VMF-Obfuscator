package views;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.*;
import javax.swing.*;

public class SplashScreen extends JWindow {

private int duration;

    public SplashScreen(int d) {
        duration = d;
    }

    public void showSplash() {

        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.blue);

        // Set the window's bounds, centering the window
        int width = 700;
        int height = 450;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);

        // Build the splash screen
        JLabel label = new JLabel(new ImageIcon("java-tip.gif"));
        JLabel copyrt = new JLabel
            ("Splash Screen!!!", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);
        Color oraRed = new Color(200, 200, 200, 255);
        content.setBorder(BorderFactory.createLineBorder(oraRed, 10));

        // Display it
        setVisible(true);

        // Wait a little while, maybe while loading resources
        try { Thread.sleep(duration); } catch (Exception e) {}

        setVisible(false);
    }
    public void showSplashAndExit() {
        showSplash();
        setVisible(false);

        new MainWindow().setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel( new FlatMacDarkLaf());
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
        // Throw a nice little title page up on the screen first
        SplashScreen splash = new SplashScreen(2000);

        // Normally, we'd call splash.showSplash() and get on
        // with the program. But, since this is only a test...
        splash.showSplashAndExit();
    }
}