package core.global;

import java.awt.*;

public enum SysColor {

    PRIMARY (14, 142, 206),

    SECONDARY (37, 45, 55),
    SUCCESS (96, 178, 96),
    WARNING (204, 204, 96),
    DANGER (178, 56, 96),

    DEFAULT (56,56,56);

    private final Color color;

    SysColor(int red, int green, int blue) {
        this.color = new Color(red,green, blue);
    }

    public Color get(){
        return color;
    }

}
