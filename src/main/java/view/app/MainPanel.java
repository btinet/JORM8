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

    public MainPanel addNorth(Component component){
        this.constraints.setRow(0);
        this.constraints.setWeightY(0);
        this.constraints.setFill(GridBagConstraints.HORIZONTAL);
        this.constraints.setAnchor(GridBagConstraints.NORTH);
        this.add(component,this.constraints);
        return this;
    }

    public MainPanel addNorth(Component component, Insets insets){
        this.constraints.setInsets(insets);
        this.constraints.setRow(0);
        this.constraints.setWeightY(0);
        this.constraints.setFill(GridBagConstraints.HORIZONTAL);
        this.constraints.setAnchor(GridBagConstraints.NORTH);
        this.add(component,this.constraints);
        return this;
    }

    public MainPanel addSouth(Component component){
        this.constraints.setRow(2);
        this.constraints.setWeightY(0);
        this.constraints.setFill(GridBagConstraints.HORIZONTAL);
        this.constraints.setAnchor(GridBagConstraints.SOUTH);
        this.add(component,this.constraints);
        return this;
    }

    public MainPanel addSouth(Component component, Insets insets){

        this.constraints.setInsets(insets);
        this.constraints.setRow(2);
        this.constraints.setWeightY(0);
        this.constraints.setFill(GridBagConstraints.HORIZONTAL);
        this.constraints.setAnchor(GridBagConstraints.SOUTH);
        this.add(component,this.constraints);
        return this;
    }

    public MainPanel addCenter(Component component){

        this.constraints.setRow(1);
        this.constraints.setWeightY(1);
        this.constraints.setFill(GridBagConstraints.BOTH);
        this.constraints.setAnchor(GridBagConstraints.NORTH);
        this.add(component,this.constraints);
        return this;
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
