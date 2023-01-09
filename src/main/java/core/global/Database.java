package core.global;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Executors;

public class Database {

    private static final String DB_DRIVER_CLASS="driver.class.name";
    private static final String DB_USERNAME="db.username";
    private static final String DB_PASSWORD="db.password";
    private static final String DB_URL ="db.url";
    protected static Connection connection = null;

    public static Connection connect(){
        if(null == connection){
            try {
                Properties properties = new Properties();
                properties.load(Resources.getConfig("database.properties").openStream());
                Class.forName(properties.getProperty(DB_DRIVER_CLASS));
                connection = DriverManager.getConnection(properties.getProperty(DB_URL), properties.getProperty(DB_USERNAME) , properties.getProperty(DB_PASSWORD) );
                connection.setNetworkTimeout(Executors.newFixedThreadPool(16),5000);
                System.out.println("Verbindung hergestellt.");
                Session.set("connection","online");
            } catch (ClassNotFoundException | SQLException | IOException e) {
                Session.set("connection","offline");
            }
        }
        return connection;
    }

    public static Connection getConnection(){
        return connect();
    }

    public static void destroyConnection(){
        connection = null;
    }

}
