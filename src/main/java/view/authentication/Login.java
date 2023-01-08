package view.authentication;

import com.formdev.flatlaf.FlatClientProperties;
import controller.AppController;
import controller.AuthenticationController;
import core.global.*;
import core.view.View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Login extends JPanel {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1;
    private JLabel errorLabel;
    private JLabel onlineLabel;
    private JPanel infoCard;

    private final View view;

    public Login(View view){
        this.view = view;

        if(null == Database.getConnection()){
            this.onlineLabel.setIcon(new ImageIcon(Resources.getImage("icons8_offline_16px.png")));
            this.onlineLabel.setText("OFFLINE");
            this.onlineLabel.setForeground(SysColor.DANGER.get());
            this.button1.setEnabled(false);
        } else {
            this.onlineLabel.setIcon(new ImageIcon(Resources.getImage("icons8_online_16px.png")));
            this.onlineLabel.setText("ONLINE");
            this.onlineLabel.setForeground(SysColor.SUCCESS.get());
            this.button1.setEnabled(true);
        }

        if(0 < Session.copy("login_error").length()){
            this.infoCard.setBackground(SysColor.SECONDARY.get());
            this.infoCard.setVisible(true);
            this.setErrorLabel(Session.get("login_error"));
        } else {
            this.infoCard.setVisible(false);
            this.setErrorLabel("Alles in Ordnung");
        }

        button1.addActionListener(e -> onOK());
        button1.putClientProperty( FlatClientProperties.BUTTON_TYPE, FlatClientProperties.BUTTON_TYPE_SQUARE );
        textField1.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON,true);
        textField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Kennung");
        passwordField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT,"Passwort");
        passwordField1.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON,true);

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.ipadx = 10;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH; //Fill the panels horizontally. A weightx is needed for this to work.
        constraints.gridx = GridBagConstraints.RELATIVE;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;

        add(panel1,constraints);
    }


    private void onOK() {
        Session.set("username",this.textField1.getText());
        Session.set("password", String.valueOf(this.passwordField1.getPassword()));
        Response.redirectToController((new AuthenticationController(view)::login));
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    protected void setErrorLabel(String message){
        this.errorLabel.setForeground(SysColor.DANGER.get());
        this.errorLabel.setText(message);
        this.errorLabel.setIcon(new ImageIcon(Resources.getImage("icons8_error_16px.png")));
    }
}
