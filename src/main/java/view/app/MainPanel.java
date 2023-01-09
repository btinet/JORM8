package view.app;

import core.view.BagConstraints;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    protected BagConstraints constraints = new BagConstraints();

    protected LayoutManager layoutManager = new GridBagLayout();

    public MainPanel(){
       setLayout(this.layoutManager);
    }

    public MainPanel addComponent(Component component){

        this.add(component,this.constraints);
        return this;
    }

    public MainPanel addComponent(Component component, GridBagConstraints constraints){
        this.add(component,constraints);
        return this;
    }
}
