package controller;

import core.controller.Controller;
import core.view.View;
import repository.KollegiatRepository;
import repository.LehrkraftRepository;

import java.awt.event.ActionEvent;

public class LehrkraftController extends Controller {

    public LehrkraftController(){
        super(new LehrkraftRepository(true));
    }

    public void index(ActionEvent e){

    }
}
