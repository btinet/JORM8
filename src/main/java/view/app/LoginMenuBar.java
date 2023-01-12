package view.app;

import controller.AppController;
import core.global.Resources;
import core.view.View;

import javax.swing.*;
import java.awt.*;

public class LoginMenuBar {

    public JMenuBar getComponent(){
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(224,224,224));
        JMenu menu1 = new JMenu("Datei");
        JMenuItem menu1_1 = new JMenuItem("anmelden", new ImageIcon(Resources.getImage("icons8_view_all_16px.png")));
        menu1_1.addActionListener((new AppController()::index));
        menu1_1.setActionCommand("1");
        menu1.add(menu1_1);
        menuBar.add(menu1);

        return menuBar;
    }

}
