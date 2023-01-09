package core.view;

import java.awt.*;

public class BagConstraints extends GridBagConstraints {

    public BagConstraints(){
        this.setAnchor(GridBagConstraints.NORTH);
        this.setFill(GridBagConstraints.BOTH);
        this.setPaddingX(0);
        this.setPaddingY(0);
        this.setWidth(1);
        this.setStretch(1,1);
        this.setInsets(new Insets(5,5,5,5));
    }

    public void setRow(int row){
        this.gridy = row;
    }

    public void setColumn(int column){
        this.gridx = column;
    }

    public void setFill(int direction){
        this.fill = direction;
    }

    public void setWeightX(double x){
        this.weightx = x;
    }

    public void setWeightY(double y){
        this.weighty = y;
    }

    public void setStretch(int x, int y){
        this.weightx = x;
        this.weighty = y;
    }

    public void setWidth(int width){
        this.gridwidth = width;
    }

    public void setHeight(int height){
        this.gridheight = height;
    }

    public void setAnchor (int anchor){
        this.anchor = anchor;
    }

    public void setPaddingX(int x){
        this.ipadx = x;
    }

    public void setPaddingY(int y){
        this.ipady = y;
    }

    public void setPadding(int x, int y){
        this.ipadx = x;
        this.ipady = y;
    }

    public void setInsets(Insets insets){
        this.insets = insets;
    }

}
