package view.app;

import controller.AppController;
import controller.AuthenticationController;
import core.global.Resources;
import core.view.View;

import javax.swing.*;
import java.awt.*;

public class AppMenuBar {

    public JMenuBar getComponent(View view){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(224,224,224));

        JMenu menu0 = new JMenu("Datei");
        JMenuItem menu0_1 = new JMenuItem("abmelden", new ImageIcon(Resources.getImage("icons8_view_all_16px.png")));
        menu0_1.addActionListener((new AuthenticationController()::logoff));
        menu0.add(menu0_1);

        JMenu menu1 = new JMenu("Kollegiat:innen");
        JMenu menu2 = new JMenu("Lehrkräfte");
        JMenuItem menu2_1 = new JMenuItem("Auflisten", new ImageIcon(Resources.getImage("icons8_lips_16px.png")));
        JMenuItem menu1_1 = new JMenuItem("Auflisten", new ImageIcon(Resources.getImage("icons8_view_all_16px.png")));
        menu1_1.addActionListener((new AppController()::index));
        menu1_1.setActionCommand("1");
        JMenuItem menu1_2 = new JMenuItem("suchen...", new ImageIcon(Resources.getImage("icons8_eye_16px.png")));
        menu1_2.addActionListener((new AppController()::show));
        menu1_2.setActionCommand("1");
        menu1.add(menu1_1);
        menu1.add(menu1_2);
        menu2.add(menu2_1);
        menuBar.add(menu0);
        menuBar.add(menu1);
        menuBar.add(menu2);

        return menuBar;
    }

}
