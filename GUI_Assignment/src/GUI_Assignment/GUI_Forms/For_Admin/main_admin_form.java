package GUI_Assignment.GUI_Forms.For_Admin;

import GUI_Assignment.GUI_Forms.ClientManagement.clientMan;
import GUI_Assignment.GUI_Forms.CustomerManagement.CustomerMan;
import GUI_Assignment.GUI_Forms.FreightsManagement.FreightsMan;
import GUI_Assignment.GUI_Forms.RoutesManagement.routesManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI_Assignment.GUI_Forms.Users_Stuff.CreateNewUser.setNewLoginVisible;
import static GUI_Assignment.GUI_Forms.Users_Stuff.UserManagingMenu.setUserManagementVisible;
import static GUI_Assignment.GUI_Forms.Users_Stuff.login_form.setLoginVisible;

/**
 * Created by adil on 26/11/16.
 */
public class main_admin_form {
    private JButton clientManagementButton;
    private JButton customerManagementButton;
    private JButton routesManagementButton;
    private JButton freightsManagementButton;
    private JButton changeUserButton;
    private JButton terminateProgramButton;
    private JPanel panelAdmin;
    private JButton manageUsersButton;
    private static JFrame frameAdmin;

    public static void setAdminVisible()
    {
        frameAdmin = new JFrame("Admin's Main Menu");
        frameAdmin.setContentPane(new main_admin_form().getPanelAdmin());
        frameAdmin.pack();
        frameAdmin.setVisible(true);
        frameAdmin.setLocation(520,200);
    }

    public static void giveMeAdmin(boolean truth)
    {
        frameAdmin.setVisible(truth);
    }

    public main_admin_form() {
        terminateProgramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to exit?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });


        changeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to change user?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION) {
                    frameAdmin.setVisible(false);
                    setLoginVisible();
                }
            }
        });

        clientManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeAdmin(false);
                clientMan.setClientManVisible();
            }
        });
        customerManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeAdmin(false);
                CustomerMan.setCustomerManVisible();
            }
        });
        routesManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeAdmin(false);
                routesManagement.setRoutesManagementVisible();
            }
        });
        freightsManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeAdmin(false);
                FreightsMan.setFreightsManVisible();
            }
        });
        manageUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeAdmin(false);
                setUserManagementVisible();
            }
        });
    }

    public JPanel getPanelAdmin() {
        return panelAdmin;
    }
}
