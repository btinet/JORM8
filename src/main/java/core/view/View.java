package core.view;

import com.formdev.flatlaf.*;
import com.formdev.flatlaf.intellijthemes.*;
import controller.AuthenticationController;
import core.global.Resources;
import core.global.Response;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class View {

    public static View view;

    public JFrame frame;

    public CardLayout cardLayout;

    private URL imagePath;

    public View(){
        this.init();

    }

    protected void init() {

        try {
            UIManager.setLookAndFeel( new FlatLightLaf());
            FlatVuesionIJTheme.setup();
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        view = this;


        this.frame = new JFrame("eSchool Manager");

        this.frame.setIconImage(new ImageIcon(Resources.getImage("favicon-32x32.png")).getImage());
        this.cardLayout = new CardLayout();
        this.frame.setLayout(this.cardLayout);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(1280, 720));
        this.frame.setResizable(false);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);

        Response.redirectToController(new AuthenticationController(this)::index);
    }
}
