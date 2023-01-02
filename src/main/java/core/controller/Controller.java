package core.controller;

import core.view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {

    protected View view;

    public Controller(View view){
       this.view = view;
    }

    public void addLayoutComponent(Component c, String constraint){
        this.view.frame.add(c,constraint);
    }

    public void setLayout(String constraint){
        this.view.cardLayout.show(this.view.frame.getContentPane(), constraint);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
