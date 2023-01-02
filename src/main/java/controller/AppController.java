package controller;

import core.controller.Controller;
import core.view.View;
import entity.Kollegiat;
import repository.KollegiatRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AppController extends Controller {

    public AppController(View view){
        super(view, new KollegiatRepository(true));
    }

    public void index(ActionEvent e) {
        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel l = new JLabel("Alle Anträge");
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

        Kollegiat kollegiat = (Kollegiat) getRepository().find(1,"KID");

        JTextField name = new JTextField();
        JTextField name2 = new JTextField();
        name.setText(kollegiat.getVorname());
        name.setToolTipText("Vorname");
        name.setPreferredSize(new Dimension(200,20));
        name2.setText(kollegiat.getName());
        name2.setPreferredSize(new Dimension(200,20));

        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel l = new JLabel("Antrag anzeigen");
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(5,5,5,5);
        p.add(l,gbc);
        p.add(name,gbc);
        p.add(name2,gbc);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        addLayoutComponent(p,"Panel");
        setLayout("Panel");
        System.out.println("AppController::show wurde aufgerufen!");
    }

}
