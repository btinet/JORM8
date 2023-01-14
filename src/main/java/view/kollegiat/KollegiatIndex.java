package view.kollegiat;

import com.formdev.flatlaf.FlatClientProperties;
import controller.AppController;
import controller.KollegiatController;
import core.global.Resources;
import core.global.Response;
import core.global.Session;
import core.model.Entity;
import core.view.BagConstraints;

import entity.Kollegiat;
import enums.SystemColor;
import enums.SystemMessage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class KollegiatIndex extends JPanel implements SystemMessage, SystemColor {
    private JList<Object> list1;
    private JScrollPane scrollPane;
    private JPanel panel1;
    private JLabel title;
    private JButton neuButton;
    private JButton detailsButton;
    private JTextField searchField;
    private JLabel resultCount;

    private ArrayList<? extends Entity> entities = new ArrayList<>();

    public KollegiatIndex(){
        setLayout(new GridBagLayout());
        title.setFont(new Font("Sans-serif",Font.BOLD,14));
        title.setBorder(new EmptyBorder(0,5,0,0));
        title.setText("Alle Kollegiat:innen");
        title.setForeground(PRIMARY);
        neuButton.putClientProperty(FlatClientProperties.BUTTON_TYPE,FlatClientProperties.BUTTON_TYPE_BORDERLESS);
        detailsButton.putClientProperty(FlatClientProperties.BUTTON_TYPE,FlatClientProperties.BUTTON_TYPE_BORDERLESS);
        detailsButton.setEnabled(false);
        searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Kollegiat:in suchen");

        if(0 < Session.copy("search_string").length()){
            searchField.setText(Session.get("search_string"));
        }

        this.updateSearchResult();

        searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON,true);

        searchField.addActionListener(e -> onSearch());

        scrollPane.putClientProperty(FlatClientProperties.SCROLL_PANE_SMOOTH_SCROLLING,true);
        scrollPane.putClientProperty(FlatClientProperties.SCROLL_BAR_SHOW_BUTTONS,true);



        this.addListSelectionListener(this.list1);
        this.addMouseListener(this.list1);

        add(panel1,new BagConstraints());
    }

    public void addData(ArrayList<? extends Entity> entities){
        this.list1.setListData(entities.toArray());
    }

    public void addSearchResult(ArrayList<? extends Entity> entities){
        this.entities = entities;
        this.updateSearchResult();
    }

    public void updateSearchResult(){
        this.resultCount.setText(String.valueOf(this.entities.size()));
    }

    private void onSearch(){
        Session.set("search_string",searchField.getText());
        Response.redirectToController(new KollegiatController()::index);
    }

    private void addListSelectionListener(JList<Object> list){
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                    if(list.getSelectedValue() instanceof Kollegiat){
                        Kollegiat kollegiat = (Kollegiat) list.getSelectedValue();
                        detailsButton.setActionCommand(String.valueOf(kollegiat.getKID()));
                        // kollegiatDetail.setKollegiat(kollegiat);
                        detailsButton.setEnabled(true);
                    }

                    if(list.getSelectedValue() == null){
                        detailsButton.setEnabled(false);
                    }
                }
            }

        });
    }

    private void addMouseListener(JList<Object> list){
        list.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                JList<Object> listenedList = (JList<Object>) mouseEvent.getSource();
                if (mouseEvent.getClickCount() == 2) {
                    int index = list.locationToIndex(mouseEvent.getPoint());
                    if (index >= 0) {
                        Object o = listenedList.getModel().getElementAt(index);
                        if(o instanceof Kollegiat){
                            Kollegiat kollegiat = (Kollegiat) list.getSelectedValue();

                            JButton click = new JButton();
                            click.setActionCommand(String.valueOf(kollegiat.getKID()));

                            click.addActionListener((new AppController()::show));
                            click.doClick();
                            System.out.println("Doppelklick auf Listenpunkt: " + o);
                        }

                    }
                }
            }
        });
    }


}
