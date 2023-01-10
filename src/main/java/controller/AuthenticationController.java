package controller;

import core.controller.Controller;
import core.global.Response;
import core.global.Session;
import core.model.Condition;

import entity.Benutzer;

import repository.BenutzerRepository;

import view.app.MainPanel;
import view.authentication.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

public class AuthenticationController extends Controller {

    public AuthenticationController(){
        super(new BenutzerRepository(true));
    }

    public void index(ActionEvent e){
        // Neues Panel erstellen und Login-Formular hinzufügen
        MainPanel main = new MainPanel()
                // TODO: addNorth(), addSouth() und addCenter() als Shortcut hinzufügen.
                .addComponent(new Login())
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
