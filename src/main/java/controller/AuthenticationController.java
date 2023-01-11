package controller;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatPropertiesLaf;
import core.controller.Controller;
import core.global.Database;
import core.global.Resources;
import core.global.Response;
import core.global.Session;
import core.model.Condition;

import entity.Benutzer;

import enums.SysColor;
import repository.BenutzerRepository;

import view.app.MainPanel;
import view.authentication.Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class AuthenticationController extends Controller {

    public AuthenticationController(){
        super(new BenutzerRepository(true));
    }

    public void index(ActionEvent e){



        // TODO: in eigene Label-Klasse verschieben.
        JLabel onlineLabel = new JLabel();
        onlineLabel.setBorder(new EmptyBorder(0,0,0,5));
        if(null == Database.getConnection()){
            onlineLabel.setIcon(new ImageIcon(Resources.getImage("icons8_offline_16px.png")));
            onlineLabel.setText("OFFLINE");
            onlineLabel.setForeground(DANGER);
        } else {
            onlineLabel.setIcon(new ImageIcon(Resources.getImage("icons8_online_16px.png")));
            onlineLabel.setText("ONLINE");
            onlineLabel.setForeground(SUCCESS);
        }

        // TODO: in eigene ToolBar-Klasse übersetzen und nach view verschieben.
        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(SECONDARY);
        JButton tBtn1 = new JButton("neuer Benutzer");
        JButton tBtn2 = new JButton("Datensatz öffnen");
        JButton tBtn3 = new JButton("Datensatz speichern");
        tBtn1.setIcon(new ImageIcon(Resources.getImage("icons8_add_new_16px.png")));
        tBtn2.setIcon(new ImageIcon(Resources.getImage("icons8_documents_folder_16px.png")));
        tBtn3.setIcon(new ImageIcon(Resources.getImage("icons8_save_16px_1.png")));
        toolBar.add(tBtn1);
        toolBar.addSeparator();
        toolBar.add(tBtn2);
        toolBar.add(tBtn3);
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(onlineLabel);

        // Neues Panel erstellen und Login-Formular hinzufügen
        MainPanel main = new MainPanel()
                .addNorth(toolBar, new Insets(0,0,5,0))
                .addCenter(new Login())
        ;

        // Panel zum Kartenlayout hinzufügen
        addLayoutComponent(main, "login");

        // Panel aufdecken
        setLayout("login");
    }

    public void login(ActionEvent e){

        // Formulardaten aus globalem Speicher holen (und entfernen)
        String username = Session.get("username");
        String password = Session.getHash(Session.get("password"));

        // Benutzer gemäß Formulardaten finden
        Benutzer benutzer = (Benutzer) getRepository().findOneBy(new Condition("benutzerName",username).getMap());

        // Wenn Benutzer gefunden
        if(null != benutzer){

            // Passwort stimmt
            if(Objects.equals(benutzer.getPasswort(),password)){
                Session.setBenutzer(benutzer);
                Response.redirectToController(new AppController()::index);

                // Passwort stimmt nicht
            } else {
                Session.set(LOGIN_ERROR, PASSWORD_NOT_FOUND);
                Response.redirectToController(new AuthenticationController()::index);
            }

            // Benutzer nicht gefunden
        } else {
            Session.set(LOGIN_ERROR,USERNAME_NOT_FOUND);
            Response.redirectToController(new AuthenticationController()::index);
        }
    }

    public void logoff(ActionEvent e) {
        this.view.frame.setJMenuBar(null);
        Session.destroySession();
        Response.redirectToController(new AuthenticationController()::index);
    }

}
