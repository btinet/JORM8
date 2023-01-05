package controller;

import core.controller.Controller;
import core.global.Resources;
import core.model.Entity;
import core.model.EntityManager;
import core.model.ResultSorter;
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
        ArrayList<Kollegiat> kollegiatArrayList = (ArrayList<Kollegiat>) this.repository.findAll(new ResultSorter("Name","asc").getMap());

        JPanel main = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 10;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL; //Fill the panels horizontally. A weightx is needed for this to work.
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(5,5,5,5);

        JButton showBtn = new JButton("Details...");
        showBtn.addActionListener((new AppController(view)::show));
        showBtn.setActionCommand("0");

        JPanel panel = new JPanel(new GridBagLayout());

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
        panelBag.anchor = GridBagConstraints.NORTH;
        panelBag.gridwidth = 1;
        panelBag.fill = GridBagConstraints.HORIZONTAL; //Fill the panels horizontally. A weightx is needed for this to work.
        panelBag.gridx = 0;
        panelBag.gridy = 0;
        panelBag.weightx = 0;
        panelBag.weighty = 1;
        panelBag.insets = new Insets(5,5,5,5);

        JLabel title = new JLabel("Kollegiat:innen - Übersicht");
        JLabel subTitle = new JLabel("Wähle einen Lernenden aus, um mehr zu erfahren.");
        subTitle.setForeground(new Color(67,67,64));
        title.setFont(new Font("sans-serif",Font.PLAIN,14));

        panelBag.weightx = 2;
        panelBag.gridwidth = 4;
        panel.add(title,panelBag);
        panelBag.gridy = 1;
        panel.add(subTitle,panelBag);
        panelBag.gridy = 2;
        panel.add(new JSeparator(),panelBag);
        panelBag.gridy = 3;
        panel.add(kollegiatJList,panelBag);
        panelBag.weightx = 0;
        panelBag.gridwidth = 1;
        panelBag.gridx = 0;
        panelBag.gridy = 4;
        panel.add(showBtn,panelBag);
        panelBag.gridx = 0;
        panelBag.weightx = 1;
        panelBag.gridwidth = 4;
        panelBag.gridy = 5;
        panel.add(new JSeparator(),panelBag);
        panelBag.gridy = 6;
        panel.add(kollegiatDetail,panelBag);

        constraints.gridy = 0;
        main.add(panel,constraints);
        JScrollPane sp = new JScrollPane(main);
        sp.setBorder(BorderFactory.createEmptyBorder());
        addLayoutComponent(sp,"Panel");
        setLayout("Panel");
        System.out.println("AppController::index wurde aufgerufen!");
    }

    public void show(ActionEvent e) {

        JPanel main = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 10;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL; //Fill the panels horizontally. A weightx is needed for this to work.
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(5,5,5,5);

        JLabel title = new JLabel("Kollegiat:in - Detailansicht");
        JLabel subTitle = new JLabel("Übersicht der Anträge und Angaben.");
        subTitle.setForeground(new Color(67,67,64));
        title.setFont(new Font("sans-serif",Font.PLAIN,14));

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
        dialog.setVisible(false);

        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel l = new JLabel("Antrag anzeigen");
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        p.add(title,gbc);
        gbc.gridy = 1;
        p.add(subTitle,gbc);
        gbc.gridy = 2;
        p.add(new JSeparator(),gbc);
        gbc.gridy = 3;

        int id = Integer.decode(e.getActionCommand());
        if(0 != id){
            Kollegiat kollegiat = (Kollegiat) getRepository().find(id,"KID");
            DetailPanel kollegiatDetail = new DetailPanel(kollegiat);

            EntityManager em = new EntityManager(true);
            em.persist(kollegiat,"KID");

            kollegiatDetail.updateFields();
            p.add(kollegiatDetail,gbc);

        } else {
            l.setText("Kein Element wurde ausgewählt");
        }


        main.add(p,constraints);
        JScrollPane sp = new JScrollPane(main);
        sp.setBorder(BorderFactory.createEmptyBorder());

        addLayoutComponent(sp,"Panel");
        setLayout("Panel");
        System.out.println("AppController::show wurde aufgerufen!");



    }

}
