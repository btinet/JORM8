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
    protected JTextField fieldNachname;
    protected JTextField fieldTutor;
    protected JTextField fieldBetreuer;

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

        JLabel nachname = new JLabel("Nachname");
        this.fieldNachname = new JTextField();
        this.fieldNachname.setEditable(false);
        this.fieldNachname.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        JLabel tutor = new JLabel("Tutor:in");
        this.fieldTutor = new JTextField();
        this.fieldTutor.setEditable(false);
        this.fieldTutor.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        JLabel betreuer = new JLabel("Betreuer:in");
        this.fieldBetreuer = new JTextField();
        this.fieldBetreuer.setEditable(false);
        this.fieldBetreuer.setCursor(new Cursor(Cursor.TEXT_CURSOR));

        // Zellen setzen

        // Row 1

        this.gbc.setRow(0);
        this.gbc.setColumn(0);
        this.gbc.setInsets(new Insets(10,0,0,10));
        add(vorname,this.gbc);

        this.gbc.setRow(0);
        this.gbc.setColumn(1);
        add(nachname,this.gbc);

        this.gbc.setRow(0);
        this.gbc.setColumn(2);
        add(tutor,this.gbc);

        this.gbc.setRow(0);
        this.gbc.setColumn(3);
        this.gbc.setInsets(new Insets(10,0,0,0));
        add(betreuer,this.gbc);

        // Row 2

        this.gbc.setRow(1);
        this.gbc.setColumn(0);
        this.gbc.setInsets(new Insets(5,0,0,10));
        add(this.fieldVorname,this.gbc);

        this.gbc.setRow(1);
        this.gbc.setColumn(1);
        add(this.fieldNachname,this.gbc);

        this.gbc.setRow(1);
        this.gbc.setColumn(2);
        add(this.fieldTutor,this.gbc);

        this.gbc.setRow(1);
        this.gbc.setColumn(3);
        this.gbc.setInsets(new Insets(5,0,0,0));
        add(this.fieldBetreuer,this.gbc);

    }

    public void updateFields(){
        this.fieldVorname.setText(this.kollegiat.getVorname());
        this.fieldNachname.setText(this.kollegiat.getName());

        if(this.kollegiat.getBetreuer().isBenutzer()){
            this.fieldBetreuer.setForeground(new Color(180,64,64));
        } else {
            this.fieldBetreuer.setForeground(null);
        }
        this.fieldTutor.setText(this.kollegiat.getTutor().toString());
        this.fieldBetreuer.setText(this.kollegiat.getBetreuer().toString());
    }




}
