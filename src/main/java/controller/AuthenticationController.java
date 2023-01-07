package controller;

import core.controller.Controller;
import core.global.Resources;
import core.model.EntityManager;
import core.model.ResultSorter;
import core.view.View;
import entity.Kollegiat;
import repository.KollegiatRepository;
import view.app.AppMenuBar;
import view.app.LoginMenuBar;
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

public class AuthenticationController extends Controller {

    public AuthenticationController(View view){
        super(view,new KollegiatRepository(true));

    }

    public void logoff(ActionEvent e) {

        this.view.frame.setJMenuBar(new LoginMenuBar().getComponent(this.view));
        Resources.destroyBenutzer();
        setLayout("One");
    }


}
