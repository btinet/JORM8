package core.view;

import view.app.AppMenuBar;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class View {

    public JFrame frame;

    public CardLayout cardLayout;

    private URL imagePath;

    public View(){
        this.init();

    }

    protected void init() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        // Frame erstellen

        this.frame = new JFrame("eSchool Manager");
        this.cardLayout = new CardLayout();
        this.frame.setLayout(this.cardLayout);

        // JPane hinzufügen
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("Start"));
        this.frame.add(jPanel, "One");

        // Setze Inhalt nach Name. Hier kann die Ansicht beeinflusst werden.
        this.cardLayout.show(frame.getContentPane(), "One");

        this.frame.setJMenuBar(new AppMenuBar().getComponent(this));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(800, 600));
        this.frame.setResizable(false);
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }
}
