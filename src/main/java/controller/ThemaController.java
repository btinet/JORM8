package controller;

import core.controller.Controller;
import core.view.View;
import repository.LehrkraftRepository;
import repository.ThemaRepository;

import java.awt.event.ActionEvent;

public class ThemaController extends Controller {

    public ThemaController(){
        super(new ThemaRepository(true));
    }

    public void index(ActionEvent e){

    }
}
