package GUI_Assignment.GUI_Forms.For_Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI_Assignment.GUI_Forms.For_Customer.CU_ViewClients.setViewCompaniesVisible;
import static GUI_Assignment.GUI_Forms.Users_Stuff.login_form.giveMeLogin;

/**
 * Created by adil on 26/11/16.
 */
public class main_customer_form {
    private JButton clientManagementButton;
    private JButton customerEntryButton;
    private JButton freightsManagementButton;
    private JButton changeUserButton;
    private JPanel panelCustomer;
    private JButton exitButton;
    private static JFrame frameCustomer;

    public main_customer_form() {
        changeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to change user?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION) {
                    frameCustomer.setVisible(false);
                    giveMeLogin(true);
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
                setViewCompaniesVisible();
                giveMeMainCustomerForm(false);
            }
        });
        customerEntryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CU_CustomerMan.setCU_CustomerManVisible();
                giveMeMainCustomerForm(false);
            }
        });
        freightsManagementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CU_FreightMan.setFreightsManVisible();
                giveMeMainCustomerForm(false);
            }
        });
    }

    public static void setCustomerVisible()
    {
        frameCustomer = new JFrame("Customer's Menu");
        frameCustomer.setContentPane(new main_customer_form().panelCustomer);
        frameCustomer.pack();
        frameCustomer.setVisible(true);
        frameCustomer.setLocation(520,200);
    }

    public static void giveMeMainCustomerForm(boolean truth)
    {
        frameCustomer.setVisible(truth);
    }
}
