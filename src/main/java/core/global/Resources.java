package core.global;

import entity.Benutzer;

import java.net.URL;

public class Resources {

    protected static Benutzer benutzer;

    public static URL getImage(String image){
        return Resources.class.getClassLoader().getResource("icons/" + image);
    }

    public static URL getConfig(String file){
        return Resources.class.getClassLoader().getResource("config/" + file);
    }

    public static Benutzer getBenutzer(){
        return Resources.benutzer;
    }

    public static void destroyBenutzer(){
        Resources.benutzer = null;
    }
}
