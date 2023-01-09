package controller;

import core.controller.Controller;
import core.view.View;
import repository.KollegiatRepository;

import java.awt.event.ActionEvent;

public class KollegiatController extends Controller {

    public KollegiatController(){
        super(new KollegiatRepository(true));
    }

    public void index(ActionEvent e){

    }
}
