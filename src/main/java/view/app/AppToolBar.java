package view.app;

import core.global.Resources;
import enums.SystemColor;
import enums.SystemMessage;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AppToolBar extends JToolBar implements SystemMessage, SystemColor {

    public AppToolBar(){

    }

    public AppToolBar addButton(String name){
        add(new JButton(name));
        return this;
    }

    public AppToolBar addButton(String name, String icon){
        JButton btn = new JButton(name);
        btn.setIcon(new ImageIcon(Resources.getImage(icon)));
        add(btn);
        return this;
    }

    public AppToolBar addButton(String name, String icon, ActionListener e){
        JButton btn = new JButton(name);
        btn.setIcon(new ImageIcon(Resources.getImage(icon)));
        btn.addActionListener(e);
        add(btn);
        return this;
    }

    public AppToolBar addGlue(){
        add(Box.createHorizontalGlue());
        return this;
    }

}
