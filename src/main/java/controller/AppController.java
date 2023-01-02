package controller;

import core.controller.Controller;
import core.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppController extends Controller {

    public AppController(View view){
        super(view);
    }

    public void index(ActionEvent e) {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel l = new JLabel("All Anträge");
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5,5,5,5);
        p.add(l,gbc);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        addLayoutComponent(p,"Panel");
        setLayout("Panel");
        System.out.println("AppController::index wurde aufgerufen!");
    }

    public void show(ActionEvent e) {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel l = new JLabel("Antrag anzeigen");
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5,5,5,5);
        p.add(l,gbc);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        addLayoutComponent(p,"Panel");
        setLayout("Panel");
        System.out.println("AppController::show wurde aufgerufen!");
    }

}
