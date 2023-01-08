package controller;

import core.controller.Controller;
import core.global.Resources;
import core.model.EntityManager;
import core.model.ResultSorter;
import core.view.View;
import entity.Kollegiat;
import repository.KollegiatRepository;
import view.app.AppMenuBar;
import view.kollegiat.DetailPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

@SuppressWarnings("unchecked")

public class AppController extends Controller {

    public AppController(View view){
        super(view,new KollegiatRepository(true));
    }

    public void index(ActionEvent e) {

        DetailPanel kollegiatDetail = new DetailPanel(new Kollegiat());
        ArrayList<Kollegiat> kollegiatArrayList = (ArrayList<Kollegiat>) this.repository.findAll(new ResultSorter("name","asc").getMap());

        ArrayList<HashMap<String, String>> kJoin = ((KollegiatRepository) this.repository).getKollegiatJoinAntrag(1);
        this.view.frame.setJMenuBar(new AppMenuBar().getComponent(this.view));

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
        showBtn.setEnabled(false);
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
                        showBtn.setEnabled(true);
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
                            System.out.println("Doppelklick auf Listenpunkt: " + o);
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
        JScrollPane sp = new JScrollPane(kollegiatJList);
        sp.setBorder(BorderFactory.createEmptyBorder());
        panel.add(sp,panelBag);
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
        addLayoutComponent(main,"Panel");
        setLayout("Panel");
    }

    public void show(ActionEvent e) {

        JPanel main = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 10;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL; //Fill the panels horizontally.
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
            Kollegiat kollegiat = (Kollegiat) getRepository().find(id,"kid");
            DetailPanel kollegiatDetail = new DetailPanel(kollegiat);
            kollegiatDetail.updateFields();
            p.add(kollegiatDetail,gbc);

            EntityManager em = new EntityManager(true);
            // Führt SQL INSERT aus:
            em.persist(kollegiat,"kid");
            // Führt SQL UPDATE aus:
            kollegiat.setName("UPDATE");
            em.persist(kollegiat,kollegiat.getKID(),"kid");

            HashMap<String, String> condition = new HashMap<>();
            condition.put("name","UPDATE Datensatz");
            Kollegiat testKollegiat = (Kollegiat) getRepository().findOneBy(condition);
            //em.remove(testKollegiat,testKollegiat.getKID(),"KID");

            main.add(p,constraints);
            JScrollPane sp = new JScrollPane(main);
            sp.setBorder(BorderFactory.createEmptyBorder());

            addLayoutComponent(sp,"Panel");
            setLayout("Panel");

        } else {
            l.setText("Kein Element wurde ausgewählt");
            this.redirectToController(new AppController(this.view)::index);
        }

    }

    public void restartApplication(ActionEvent e)
    {
        final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
        final File currentJar;
        try {
            currentJar = new File(AppController.class.getProtectionDomain().getCodeSource().getLocation().toURI());
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

        /* is it a jar file? */
        if(!currentJar.getName().endsWith(".jar"))
            return;

        /* Build command: java -jar application.jar */
        final ArrayList<String> command = new ArrayList<String>();
        command.add(javaBin);
        command.add("-jar");
        command.add(currentJar.getPath());

        final ProcessBuilder builder = new ProcessBuilder(command);
        try {
            builder.start();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        System.exit(0);
    }

}
