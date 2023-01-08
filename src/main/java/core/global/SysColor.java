package core.global;

import java.awt.*;

public enum SysColor {

    PRIMARY,
    SUCCESS,
    WARNING,
    DANGER;

    SysColor() {
    }

    public Color get(){
        Color color = null;

        switch (this) {
            case PRIMARY:
                color = new Color(14, 142, 206);
                break;
            case SUCCESS:
                color = new Color(96, 178, 96);
                break;
            case WARNING:
                color = new Color(204, 204, 96);
                break;
            case DANGER:
                color = new Color(178, 56, 96);
                break;
            default:
                color = new Color(56,56,56);
        }

        return color;
    }

}
