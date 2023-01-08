import core.global.Database;
import core.view.View;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // TODO: Ausnahmebehandlung, falls die Internetverbindung unterbrochen ist.
        Database.connect();
        SwingUtilities.invokeLater(View::new);
    }
}
