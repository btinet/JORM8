package controller;

import core.controller.Controller;
import core.view.View;
import repository.FachRepository;
import repository.ThemaRepository;

import java.awt.event.ActionEvent;

public class FachController extends Controller {

    public FachController(View view){
        super(view, new FachRepository(true));
    }

    public void index(ActionEvent e){

    }
}
