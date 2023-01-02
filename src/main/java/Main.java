import core.global.Database;
import core.global.Resources;
import core.view.View;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Database.connect();

        Connection connection = Database.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Kollegiat");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString("Vorname") + " " + resultSet.getString("Name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        SwingUtilities.invokeLater(View::new);




    }

}
