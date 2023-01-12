package view.kollegiat;

import com.formdev.flatlaf.FlatClientProperties;
import core.model.Entity;
import core.view.BagConstraints;

import enums.SystemColor;
import enums.SystemMessage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class KollegiatIndex extends JPanel implements SystemMessage, SystemColor {
    private JList<Object> list1;
    private JScrollPane scrollPane;
    private JPanel panel1;
    private JLabel title;
    private JButton neuButton;
    private JButton detailsButton;

    public KollegiatIndex(){
        setLayout(new GridBagLayout());
        title.setFont(new Font("Sans-serif",Font.BOLD,14));
        title.setBorder(new EmptyBorder(0,5,0,0));
        title.setText("Alle Kollegiat:innen");
        title.setForeground(PRIMARY);
        neuButton.putClientProperty(FlatClientProperties.BUTTON_TYPE,FlatClientProperties.BUTTON_TYPE_BORDERLESS);
        detailsButton.putClientProperty(FlatClientProperties.BUTTON_TYPE,FlatClientProperties.BUTTON_TYPE_BORDERLESS);

        add(panel1,new BagConstraints());
    }

    public void addData(ArrayList<? extends Entity> entities){
        this.list1.setListData(entities.toArray());
    }

}
