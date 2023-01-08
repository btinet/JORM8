package controller;

import core.controller.Controller;
import core.global.Resources;
import core.global.Response;
import core.global.Session;
import core.model.Condition;
import core.model.EntityManager;
import core.model.ResultSorter;
import core.view.View;
import entity.Benutzer;
import entity.Kollegiat;
import repository.BenutzerRepository;
import repository.KollegiatRepository;
import repository.LehrkraftRepository;
import view.app.AppMenuBar;
import view.app.LoginMenuBar;
import view.authentication.Login;
import view.kollegiat.DetailPanel;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AuthenticationController extends Controller {

    public AuthenticationController(View view){
        super(view,new BenutzerRepository(true));

    }

    public void index(ActionEvent e){
        JPanel jPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH; //Fill the panels horizontally. A weightx is needed for this to work.
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(5,5,5,5);


        jPanel.add(new Login(this.view),constraints);

        addLayoutComponent(jPanel, "login");
        setLayout("login");
    }

    public void login(ActionEvent e){
        String username = Session.get("username");
        String password = Session.getHash(Session.get("password"));
        System.out.println("Benutzername:" + username);
        System.out.println("Passwort:" + password);

        Benutzer benutzer = (Benutzer) getRepository().findOneBy(new Condition("benutzerName",username).getMap());
        if(null != benutzer){
            System.out.println("Benutzer mit LehrerID '" + benutzer.getLid() + "' gefunden!");
            System.out.println("User Passwort: '" + benutzer.getPasswort().toString() + "'");
            if(Objects.equals(benutzer.getPasswort(),password)){
                System.out.println("Passwort stimmt überein!");
                Session.setBenutzer(benutzer);
                Response.redirectToController(new AppController(this.view)::index);
            } else {
                System.err.println("Passwort stimmt nicht überein!");
                Session.set("login_error","Passwort stimmt nicht überein!");
                Response.redirectToController(new AuthenticationController(this.view)::index);
            }
        } else {
            System.err.println("Kein Benutzer gefunden!");
            Session.set("login_error","Benutzer nicht gefunden!");
            Response.redirectToController(new AuthenticationController(this.view)::index);
        }

    }

    public void logoff(ActionEvent e) {
        this.view.frame.setJMenuBar(null);
        // this.view.frame.setJMenuBar(new LoginMenuBar().getComponent(this.view));
        Session.destroySession();
        Response.redirectToController(new AuthenticationController(this.view)::index);
    }


}
