package core.global;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    private static final String DB_DRIVER_CLASS="driver.class.name";
    private static final String DB_USERNAME="db.username";
    private static final String DB_PASSWORD="db.password";
    private static final String DB_URL ="db.url";
    private static Connection connection = null;

    public static void connect(){
            try {
                Properties properties = new Properties();
                properties.load(Resources.getConfig("database.properties").openStream());
                Class.forName(properties.getProperty(DB_DRIVER_CLASS));
                connection = DriverManager.getConnection(properties.getProperty(DB_URL), properties.getProperty(DB_USERNAME) , properties.getProperty(DB_PASSWORD) );
                System.out.println("Verbindung hergestellt.");
            } catch (ClassNotFoundException | SQLException | IOException e) {
                e.printStackTrace();
            }
    }

    public static Connection getConnection(){
        return connection;
    }

}
