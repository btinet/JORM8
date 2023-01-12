package view.authentication;

import view.app.AppToolBar;
import view.app.OnlineStatusLabel;

public class AuthenticationToolBar extends AppToolBar {

    public AuthenticationToolBar(){
        addGlue();
        add(new OnlineStatusLabel());
    }

}
