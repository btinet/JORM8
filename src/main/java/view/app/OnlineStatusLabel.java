package view.app;

import core.global.Database;
import core.global.Resources;
import enums.SystemColor;
import enums.SystemMessage;

import javax.swing.*;

public class OnlineStatusLabel extends JButton implements SystemMessage, SystemColor {

    public OnlineStatusLabel(){
        if(null == Database.getConnection()){
            setIcon(new ImageIcon(Resources.getImage("icons8_offline_16px_1.png")));
            setText("Offline");
        } else {
            setIcon(new ImageIcon(Resources.getImage("icons8_online_16px_1.png")));
            setText("Online");
        }
    }

}
