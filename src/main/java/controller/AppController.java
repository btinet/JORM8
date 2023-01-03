package controller;

import core.controller.Controller;
import core.model.Entity;
import core.view.View;
import entity.Kollegiat;
import repository.KollegiatRepository;
import view.kollegiat.TablePanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class AppController extends Controller {

    public AppController(View view){
        super(view, new KollegiatRepository(true));
    }

    public void index(ActionEvent e) {

        ArrayList<Kollegiat> kollegiatArrayList = (ArrayList<Kollegiat>) this.repository.findAll();

        JPanel main = new JPanel(new GridBagLayout());




        JLabel l2 = new JLabel("Alle Daten");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 10;
        constraints.anchor = GridBagConstraints.PAGE_START;
        constraints.fill = GridBagConstraints.HORIZONTAL; //Fill the panels horizontally. A weightx is needed for this to work.
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(10,10,0,10);

        JButton showBtn = new JButton("ausgewähltes Element anzeigen");
        showBtn.addActionListener((new AppController(view)::show));
        showBtn.setActionCommand("0");

        JPanel panel = new JPanel(new GridBagLayout());

        JTextField tutorText = new JTextField();
        tutorText.setPreferredSize(new Dimension(200,18));
        tutorText.setEnabled(false);
        JTextField betreuerText = new JTextField();
        betreuerText.setEnabled(false);
        betreuerText.setPreferredSize(new Dimension(200,18));
        JLabel tutorLabel = new JLabel("Tutor");
        JLabel betreuerLabel = new JLabel("Betreuer");

        JPanel tutorPanel = new JPanel();
        JPanel betreuerPanel = new JPanel();
        tutorPanel.add(tutorLabel);
        tutorPanel.add(tutorText);
        betreuerPanel.add(betreuerLabel);
        betreuerPanel.add(betreuerText);

        JList<Object> kollegiatJList = new JList<>(kollegiatArrayList.toArray());
        kollegiatJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        kollegiatJList.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    if(kollegiatJList.getSelectedValue() instanceof Kollegiat){
                        Kollegiat kollegiat = (Kollegiat) kollegiatJList.getSelectedValue();
                        showBtn.setActionCommand(String.valueOf(kollegiat.getKID()));
                        tutorText.setText(kollegiat.getTutorID().toString());
                        betreuerText.setText(kollegiat.getBetreuerID().toString());
                    }

                }
            }
        });


        GridBagConstraints panelBag = new GridBagConstraints();
        panelBag.ipadx = 10;
        panelBag.anchor = GridBagConstraints.PAGE_START;
        panelBag.fill = GridBagConstraints.HORIZONTAL; //Fill the panels horizontally. A weightx is needed for this to work.
        panelBag.gridx = 0;
        panelBag.gridy = 0;
        panelBag.weightx = 1;
        panelBag.weighty = 1;
        panelBag.insets = new Insets(10,10,0,10);


        JPanel cardBottom = new JPanel();
        cardBottom.setBackground(new Color(200,200,220));
        cardBottom.add(l2);
        constraints.insets = new Insets(10,10,10,10);

        constraints.gridy = 0;
        panel.add(kollegiatJList,constraints);
        constraints.gridy = 1;
        panel.add(showBtn,constraints);
        constraints.gridy = 2;
        panel.add(tutorPanel,constraints);
        constraints.gridy = 3;
        panel.add(betreuerPanel,constraints);

        constraints.gridy = 0;
        main.add(panel,constraints);
        addLayoutComponent(new JScrollPane(main),"Panel");
        setLayout("Panel");
        System.out.println("AppController::index wurde aufgerufen!");
    }

    public void show(ActionEvent e) {

        Kollegiat kollegiat;

        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel l = new JLabel("Antrag anzeigen");
        gbc.gridwidth = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(5,5,5,5);
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        p.add(l,gbc);

        int id = Integer.decode(e.getActionCommand());
        if(0 != id){
            kollegiat = (Kollegiat) getRepository().find(id,"KID");
            JTextField name = new JTextField();
            JTextField name2 = new JTextField();
            name.setText(kollegiat.getVorname());
            name.setToolTipText("Vorname");
            name.setPreferredSize(new Dimension(200,20));
            name2.setText(kollegiat.getName());
            name2.setPreferredSize(new Dimension(200,20));
            p.add(name,gbc);
            p.add(name2,gbc);

        } else {
            l.setText("Kein Element wurde ausgewählt");
        }




        addLayoutComponent(p,"Panel");
        setLayout("Panel");
        System.out.println("AppController::show wurde aufgerufen!");



    }

}
