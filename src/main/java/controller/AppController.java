package controller;

import core.controller.Controller;
import core.view.View;
import entity.Kollegiat;
import javafx.scene.layout.Pane;
import jdk.nashorn.internal.runtime.ScriptObject;
import repository.KollegiatRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AppController extends Controller {

    public AppController(View view){
        super(view, new KollegiatRepository(true));
    }

    public void index(ActionEvent e) {

        ArrayList<Kollegiat> kollegiatArrayList = (ArrayList<Kollegiat>) this.repository.findAll();

        JPanel main = new JPanel();
        main.setLayout(new GridBagLayout());

        JLabel l2 = new JLabel("Alle Daten");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 10;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL; //Fill the panels horizontally. A weightx is needed for this to work.
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(10,10,0,10);

        JPanel panel = new JPanel(new GridBagLayout());


        int i = 1;
        for(Kollegiat kollegiat : kollegiatArrayList){
            JPanel titleCard = new JPanel();
            BoxLayout boxLayout = new BoxLayout(titleCard,BoxLayout.LINE_AXIS);
            titleCard.setLayout(boxLayout);

            titleCard.add(new JLabel("Vorname"));
            titleCard.add(Box.createRigidArea(new Dimension(10, 0)));
            JTextField vorname = new JTextField(kollegiat.getVorname());
            vorname.setPreferredSize(new Dimension(100,20));
            titleCard.add(vorname);
            titleCard.add(Box.createRigidArea(new Dimension(10, 0)));

            titleCard.add(new JLabel("Name"));
            titleCard.add(Box.createRigidArea(new Dimension(10, 0)));
            JTextField name = new JTextField(kollegiat.getName());
            name.setPreferredSize(new Dimension(100,20));
            titleCard.add(name);
            titleCard.add(Box.createRigidArea(new Dimension(10, 0)));

            titleCard.add(new JLabel("Tutor"));
            titleCard.add(Box.createRigidArea(new Dimension(10, 0)));
            JTextField tutor = new JTextField(kollegiat.getTutorID().toString());
            tutor.setPreferredSize(new Dimension(100,20));
            titleCard.add(tutor);
            titleCard.add(Box.createRigidArea(new Dimension(10, 0)));

            titleCard.add(new JLabel("Betreuer"));
            titleCard.add(Box.createRigidArea(new Dimension(10, 0)));
            JTextField betreuer = new JTextField(kollegiat.getBetreuerID().toString());
            betreuer.setPreferredSize(new Dimension(100,20));
            titleCard.add(betreuer);

            panel.add(titleCard, constraints);

            if(i == kollegiatArrayList.toArray().length){
                JPanel end = new JPanel();
                BoxLayout endLayout = new BoxLayout(end,BoxLayout.LINE_AXIS);
                end.setLayout(endLayout);
                end.add(Box.createRigidArea(new Dimension(0, 10)));

                panel.add(end,constraints);
            }
            i++;
        }

        JPanel cardBottom = new JPanel();
        cardBottom.setBackground(new Color(200,200,220));
        cardBottom.add(l2);
        constraints.insets = new Insets(10,10,10,10);

        JScrollPane scrollPane = new JScrollPane (panel,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(main.getWidth(),400));
        constraints.gridy = 0;
        main.add(cardBottom,constraints);
        constraints.gridy = 1;
        main.add(scrollPane,constraints);

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
