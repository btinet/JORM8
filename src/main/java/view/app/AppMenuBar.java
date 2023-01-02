package view.app;

import controller.AppController;
import core.global.Resources;
import core.view.View;

import javax.swing.*;
import java.awt.*;

public class AppMenuBar {

    public JMenuBar getComponent(View view){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(224,224,224));
        JMenu menu1 = new JMenu("Anträge");
        JMenu menu2 = new JMenu("Ehemann");
        JMenuItem menu2_1 = new JMenuItem("Ehefrau küssen", new ImageIcon(Resources.getImage("icons8_lips_16px.png")));
        JMenuItem menu1_1 = new JMenuItem("Alle Anträge", new ImageIcon(Resources.getImage("icons8_view_all_16px.png")));
        menu1_1.addActionListener((new AppController(view)::index));
        menu1_1.setActionCommand("1");
        JMenuItem menu1_2 = new JMenuItem("anzeigen...", new ImageIcon(Resources.getImage("icons8_eye_16px.png")));
        menu1_2.addActionListener((new AppController(view)::show));
        menu1_2.setActionCommand("1");
        menu1.add(menu1_1);
        menu1.add(menu1_2);
        menu2.add(menu2_1);
        menuBar.add(menu1);
        menuBar.add(menu2);

        return menuBar;
    }

}
