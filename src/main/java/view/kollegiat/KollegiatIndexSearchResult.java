package view.kollegiat;

import com.formdev.flatlaf.FlatClientProperties;
import enums.SystemColor;

import javax.swing.*;

public class KollegiatIndexSearchResult implements SystemColor {

    private JPanel panel;
    private JButton button1;


    public void setName(String name) {
        this.button1.setText(name);
    }

    public JPanel getPanel(){
        return this.panel;
    }
}
