package view.authentication;

import com.formdev.flatlaf.FlatClientProperties;

import controller.AppController;
import controller.AuthenticationController;

import core.global.*;

import enums.SysColor;
import enums.SystemMessage;

import javax.swing.*;
import java.awt.*;

public class Login extends JPanel implements SystemMessage {
    private JPanel panel1;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton button1;
    private JLabel errorLabel;
    private JLabel onlineLabel;
    private JPanel infoCard;
    private JButton restartButton;


    public Login(){

        if(null == Database.getConnection()){
            this.onlineLabel.setIcon(new ImageIcon(Resources.getImage("icons8_offline_16px.png")));
            this.onlineLabel.setText("OFFLINE");
            this.onlineLabel.setForeground(SysColor.DANGER.get());
            this.button1.setEnabled(false);
            this.restartButton.setVisible(true);
            this.restartButton.putClientProperty(FlatClientProperties.BUTTON_TYPE,FlatClientProperties.BUTTON_TYPE_ROUND_RECT);
            this.restartButton.addActionListener(new AppController()::restartApplication);
        } else {
            this.restartButton.setVisible(false);
            this.onlineLabel.setIcon(new ImageIcon(Resources.getImage("icons8_online_16px.png")));
            this.onlineLabel.setText("ONLINE");
            this.onlineLabel.setForeground(SysColor.SUCCESS.get());
            this.button1.setEnabled(true);
        }

        if(0 < Session.copy(LOGIN_ERROR).length()){
            this.infoCard.setBackground(SysColor.SECONDARY.get());
            this.infoCard.setVisible(true);
            this.setErrorLabel(Session.get(LOGIN_ERROR));
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
        Response.redirectToController((new AuthenticationController()::login));
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
