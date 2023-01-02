package core.global;

import java.net.URL;

public class Resources {

    public static URL getImage(String image){
        return Resources.class.getClassLoader().getResource("icons/" + image);
    }

    public static URL getConfig(String file){
        return Resources.class.getClassLoader().getResource("config/" + file);
    }

}
