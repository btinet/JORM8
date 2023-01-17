package controller;

import core.controller.Controller;
import core.global.Session;
import core.model.ResultSorter;
import entity.Kollegiat;
import repository.KollegiatRepository;
import view.app.AppMenuBar;
import view.app.MainPanel;
import view.kollegiat.KollegiatIndex;
import view.kollegiat.KollegiatIndexToolBar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class KollegiatController extends Controller {


    public KollegiatController(){
        super(new KollegiatRepository(true));
    }

    public void index(ActionEvent e){

        // Alle Kollegiat-Datensätze nach Nachname sortiert abrufen.
        ArrayList<Kollegiat> kollegiaten = (ArrayList<Kollegiat>) this.repository.findAll(new ResultSorter("name","asc").getMap());

        ArrayList<Kollegiat> kollegiatResult = new ArrayList<>();

        if(0 < Session.copy("search_string").length()){
            if(this.repository instanceof KollegiatRepository){
                kollegiatResult = (ArrayList<Kollegiat>) ((KollegiatRepository) this.repository).findBySearchString(Session.copy("search_string"));
            }
        }

        // JList erstellen und Daten zuweisen.
        KollegiatIndex kollegiatIndex = new KollegiatIndex();
        kollegiatIndex.addData(kollegiaten);
        kollegiatIndex.addSearchResult(kollegiatResult);

        // Neues Panel erstellen und Liste hinzufügen.
        MainPanel main = new MainPanel()
                .addNorth(new KollegiatIndexToolBar(), new Insets(0,0,5,0))
                .addCenter(kollegiatIndex)
                ;

        // Panel zum Kartenlayout hinzufügen
        addLayoutComponent(main, "kollegiatIndex");
        view.frame.setJMenuBar(new AppMenuBar().getComponent());

        // Panel aufdecken
        setLayout("kollegiatIndex");

    }
}
