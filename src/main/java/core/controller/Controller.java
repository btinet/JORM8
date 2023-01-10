package core.controller;

import core.global.Response;
import core.model.Repository;
import core.view.View;

import enums.SystemColor;
import enums.SystemMessage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener, SystemMessage, SystemColor {

    protected View view;

    protected Repository repository;

    public Controller(Repository repository){
       this.view = View.view;
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

    // TODO: Redirect Workaround mit ActionListener auf unsichtbaren Button "richtig" implementieren.
    public void redirectToController(ActionListener listener){
        Response.redirectToController(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
