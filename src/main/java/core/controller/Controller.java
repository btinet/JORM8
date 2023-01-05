package core.controller;

import controller.AppController;
import core.model.Entity;
import core.model.Repository;
import core.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    public void redirectToController(ActionListener listener){
        System.err.println("Redirect wurde ausgeführt");
        JButton redirectButton = new JButton();
        redirectButton.addActionListener(listener);
        redirectButton.doClick();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
