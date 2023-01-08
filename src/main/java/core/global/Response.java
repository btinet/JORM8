package core.global;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Response {

    public Response(){

    }

    public static void redirectToController(ActionListener listener){
        System.err.println("Redirect wurde ausgeführt");
        JButton redirectButton = new JButton();
        redirectButton.addActionListener(listener);
        redirectButton.doClick();
    }
}
