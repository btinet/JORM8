package core.view;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.*;

import controller.AuthenticationController;

import core.global.Resources;
import core.global.Response;

import javax.swing.*;
import java.awt.*;

public class View {

    public static View view;
    public JFrame frame;
    public CardLayout cardLayout;

    public View(){
        this.init();
    }

    protected void init() {

        // Look and Feel Instanz erstellen
        try {
            UIManager.setLookAndFeel( new FlatLightLaf());
            FlatVuesionIJTheme.setup();
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        // View global verfügbar machen
        view = this;

        // Frame erstellen
        this.frame = new JFrame("eSchool Manager");

        // Eigenschaften festlegen
        this.frame.setIconImage(new ImageIcon(Resources.getImage("favicon-32x32.png")).getImage());
        this.cardLayout = new CardLayout();
        this.frame.setLayout(this.cardLayout);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(1280, 720));
        this.frame.setResizable(false);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        // Zum Login-Gate weiterleiten
        Response.redirectToController(new AuthenticationController()::index);

    }
}
