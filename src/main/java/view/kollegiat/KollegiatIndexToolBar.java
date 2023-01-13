package view.kollegiat;

import core.global.Session;
import view.app.AppToolBar;
import view.app.OnlineStatusLabel;

public class KollegiatIndexToolBar extends AppToolBar {

    public KollegiatIndexToolBar(){
        setBackground(SECONDARY);
        addButton("neuer Benutzer","icons8_add_new_16px.png");
        addButton("Datensatz öffnen","icons8_documents_folder_16px.png");
        addButton("Datensatz speichern","icons8_save_16px_1.png");
        addGlue();
        addButton(Session.getBenutzer().getLehrkraft().toString());
        add(new OnlineStatusLabel());
    }

}
