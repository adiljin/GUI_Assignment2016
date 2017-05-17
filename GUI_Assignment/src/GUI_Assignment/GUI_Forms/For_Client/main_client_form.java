package GUI_Assignment.GUI_Forms.For_Client;

import GUI_Assignment.GUI_Forms.Users_Stuff.login_form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI_Assignment.GUI_Forms.For_Client.CL_ViewCustomerShippers.setViewCustomersVisible;

/**
 * Created by adil on 26/11/16.
 */
public class main_client_form {
    private static JFrame frameClient;
    private JPanel panelClient;
    private JButton clientManagementButton;
    private JButton customerManagementButton;
    private JButton freightsManagementButton;
    private JButton changeUserButton;
    private JButton exitButton;

    public main_client_form() {
        changeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to change user?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION) {
                    frameClient.setVisible(false);
                    login_form.giveMeLogin(true);
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to exit?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        clientManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CL_clientMan.setCL_clientManVisible();
                giveMeMainClientForm(false);
            }
        });
        customerManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setViewCustomersVisible();
                giveMeMainClientForm(false);
            }
        });
        freightsManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CL_ViewFreight.setFrameViewFreightVisible();
                giveMeMainClientForm(false);
            }
        });
    }

    public static void setClientVisible()
    {
        frameClient = new JFrame("Client's Menu");
        frameClient.setContentPane(new main_client_form().panelClient);
        frameClient.pack();
        frameClient.setVisible(true);
        frameClient.setLocation(520,200);
    }

    public static void giveMeMainClientForm(boolean truth)
    {
        frameClient.setVisible(truth);
    }
}
