package view.kollegiat;

import core.view.BagConstraints;
import entity.Kollegiat;

import javax.swing.*;
import java.awt.*;

public class DetailPanel extends JPanel {

    protected GridBagLayout gridBagLayout;

    protected  BagConstraints gbc;

    protected Kollegiat kollegiat;

    protected JTextField fieldVorname;

    public DetailPanel(Kollegiat kollegiat){
        this.gridBagLayout = new GridBagLayout();
        this.gbc = new BagConstraints();
        this.kollegiat = kollegiat;

        this.init();
    }

    public void setKollegiat(Kollegiat kollegiat){
        this.kollegiat = kollegiat;
        this.updateFields();
    }

    protected void init(){
        setLayout(this.gridBagLayout);

        JLabel vorname = new JLabel("Vorname");
        this.fieldVorname = new JTextField();
        this.fieldVorname.setEditable(false);
        this.fieldVorname.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        this.gbc.setWeightX(0.5);
        this.gbc.setWidth(1);
        this.gbc.setRow(0);
        this.gbc.setColumn(0);
        this.gbc.setInsets(new Insets(10,0,0,10));
        add(vorname,this.gbc);
        this.gbc.setWeightX(0.5);
        this.gbc.setWidth(1);
        this.gbc.setRow(0);
        this.gbc.setColumn(1);
        add(new JLabel("Test"),this.gbc);
        this.gbc.setWeightX(0);
        this.gbc.setWidth(2);
        this.gbc.setRow(1);
        this.gbc.setColumn(0);
        this.gbc.setInsets(new Insets(10,0,0,0));
        add(this.fieldVorname,this.gbc);


    }

    protected void updateFields(){
        this.fieldVorname.setText(this.kollegiat.getVorname());
    }




}
