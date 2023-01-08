package view.authentication;

import controller.AppController;
import core.global.Resources;
import core.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private View view;

    public LoginForm(View view) {
        this.view = view;
        setContentPane(contentPane);
        setModal(true);
        setTitle("Bitte anmelden");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon(Resources.getImage("favicon-32x32.png")).getImage());
        setResizable(false);
        setSize(600,250);
        setLocationByPlatform(true);
        setLocationRelativeTo(this.view.frame);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());

        buttonCancel.addActionListener(e -> onCancel());

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        JButton click = new JButton();
        click.addActionListener((new AppController(view)::index));
        click.doClick();
        dispose();

    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
