package controller;

import core.controller.Controller;
import core.view.View;
import repository.AntragRepository;
import repository.FachRepository;

import java.awt.event.ActionEvent;

public class AntragController extends Controller {

    public AntragController(){
        super(new AntragRepository(true));
    }

    public void index(ActionEvent e){

    }
}
