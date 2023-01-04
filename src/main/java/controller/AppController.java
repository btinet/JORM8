package controller;

import core.controller.Controller;
import core.global.Resources;
import core.model.Entity;
import core.view.View;
import entity.Kollegiat;
import repository.KollegiatRepository;
import view.kollegiat.DetailPanel;
import view.kollegiat.TablePanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class AppController extends Controller {

    public AppController(View view){
        super(view, new KollegiatRepository(true));
    }

    public void index(ActionEvent e) {
        DetailPanel kollegiatDetail = new DetailPanel(new Kollegiat());
        ArrayList<Kollegiat> kollegiatArrayList = (ArrayList<Kollegiat>) this.repository.findAll();

        JPanel main = new JPanel(new GridBagLayout());



        JLabel l2 = new JLabel("Alle Daten");

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 10;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL; //Fill the panels horizontally. A weightx is needed for this to work.
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(10,10,0,10);

        JButton showBtn = new JButton("ausgewähltes Element anzeigen");
        showBtn.addActionListener((new AppController(view)::show));
        showBtn.setActionCommand("0");

        JPanel panel = new JPanel(new GridBagLayout());

        JTextField tutorText = new JTextField();
        tutorText.setPreferredSize(new Dimension(200,20));
        tutorText.setEnabled(false);
        JTextField betreuerText = new JTextField();
        betreuerText.setEnabled(false);
        betreuerText.setPreferredSize(new Dimension(200,20));
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
                        kollegiatDetail.setKollegiat(kollegiat);
                    }

                }
            }
        });

        MouseListener mouseListener = new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList theList = (JList) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2) {
                    int index = theList.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        Object o = theList.getModel().getElementAt(index);
                        if(o instanceof Kollegiat){
                            Kollegiat kollegiat = (Kollegiat) kollegiatJList.getSelectedValue();
                            JButton click = new JButton();

                            click.setActionCommand(String.valueOf(kollegiat.getKID()));

                            click.addActionListener((new AppController(view)::show));
                            click.doClick();
                            System.out.println("Double-clicked on: " + o);
                        }

                    }
                }
            }
        };

        kollegiatJList.addMouseListener(mouseListener);


        GridBagConstraints panelBag = new GridBagConstraints();
        panelBag.ipadx = 10;
        panelBag.anchor = GridBagConstraints.NORTH;
        panelBag.gridwidth = GridBagConstraints.PAGE_START;
        panelBag.fill = GridBagConstraints.HORIZONTAL; //Fill the panels horizontally. A weightx is needed for this to work.
        panelBag.gridx = 0;
        panelBag.gridy = 1;
        panelBag.weightx = 1;
        panelBag.weighty = 1;
        panelBag.insets = new Insets(10,10,0,10);


        JPanel cardBottom = new JPanel();
        cardBottom.setBackground(new Color(200,200,220));
        cardBottom.add(l2);
        constraints.insets = new Insets(5,5,5,5);

        JLabel title = new JLabel("Kollegiat:innen - Übersicht");
        JLabel subTitle = new JLabel("Wähle einen Lernenden aus, um mehr zu erfahren.");
        subTitle.setForeground(new Color(67,67,64));
        title.setFont(new Font("sans-serif",Font.PLAIN,14));


        panel.add(title,constraints);
        constraints.gridy = 1;
        panel.add(subTitle,constraints);
        constraints.gridy = 2;
        panel.add(kollegiatJList,constraints);
        constraints.gridy = 3;
        panel.add(showBtn,constraints);
        constraints.gridy = 4;
        panel.add(kollegiatDetail,constraints);

        constraints.gridy = 0;
        main.add(panel,constraints);
        JScrollPane sp = new JScrollPane(main);
        sp.setBorder(BorderFactory.createEmptyBorder());
        addLayoutComponent(sp,"Panel");
        setLayout("Panel");
        System.out.println("AppController::index wurde aufgerufen!");
    }

    public void show(ActionEvent e) {

        JDialog dialog = new JDialog();
        dialog.setTitle("Anzeige fertig.");
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setIconImage(new ImageIcon(Resources.getImage("favicon-32x32.png")).getImage());
        dialog.setResizable(false);

        dialog.setSize(600,250);
        // Dialog wird auf modal gesetzt
        dialog.setModal(true);
        dialog.setLocationByPlatform(true);
        dialog.setLocationRelativeTo(this.view.frame);
        // Wir lassen unseren Dialog anzeigen
        dialog.setVisible(true);

        Kollegiat kollegiat;

        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel l = new JLabel("Antrag anzeigen");
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = GridBagConstraints.PAGE_START;
        gbc.insets = new Insets(5,5,5,5);
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
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
