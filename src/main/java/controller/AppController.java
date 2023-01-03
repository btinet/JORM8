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
        JPanel main = new JPanel();
        BorderLayout borderBox = new BorderLayout();
        main.setLayout(borderBox);
        main.setBackground(new Color(200,200,210));

        JPanel p = new JPanel();
        GridBagLayout borderLayout = new GridBagLayout();
        p.setLayout(borderLayout);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL; //Fill the panels horizontally. A weightx is needed for this to work.
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;

        JPanel titleCard = new JPanel();
        titleCard.setBackground(new Color(200,200,190));

        JPanel cardTop = new JPanel();
        cardTop.setBackground(new Color(200,200,220));

        JPanel cardBottom = new JPanel();
        cardBottom.setBackground(new Color(200,200,220));
        cardBottom.setPreferredSize(new Dimension(800,200));



        JLabel l = new JLabel("Alle Anträge");
        JLabel l2 = new JLabel("Alle Daten");

        cardTop.add(l);
        titleCard.add(new JLabel("Alle Kollegiaten"));
        cardBottom.add(l2);

        p.add(titleCard,constraints);

        constraints.gridy = 1;
        p.add(cardTop,constraints);
        constraints.gridy = 2;
        p.add(cardBottom,constraints);

        main.add(p,BorderLayout.PAGE_START);

        addLayoutComponent(main,"Panel");
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
