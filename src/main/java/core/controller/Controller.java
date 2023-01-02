package core.controller;

import core.model.Entity;
import core.model.Repository;
import core.view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {

    protected View view;

    protected Repository repository;

    public Controller(View view, Repository repository){
       this.view = view;
       this.repository = repository;
    }

    public Repository getRepository(){
        return this.repository;
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
