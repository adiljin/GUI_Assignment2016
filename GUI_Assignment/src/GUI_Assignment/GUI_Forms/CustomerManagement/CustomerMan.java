package GUI_Assignment.GUI_Forms.CustomerManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI_Assignment.GUI_Forms.CustomerManagement.EditCustomerShipper.setEditCustomerShipperVisible;
import static GUI_Assignment.GUI_Forms.For_Admin.main_admin_form.giveMeAdmin;

/**
 * Created by adil on 27/11/16.
 */
public class CustomerMan {
    private JButton registerCustomerShipperButton;
    private JButton editCustomerShipperButton;
    private JButton deleteCustomerShipperButton;
    private JButton viewCustomerShipperButton;
    private JButton returnButton;
    private JPanel panelCustomerMan;
    private static JFrame frameCustomerMan;

    public CustomerMan() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeCustomerMan(false);
                giveMeAdmin(true);
            }
        });
        registerCustomerShipperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeCustomerMan(false);
                RegisterCustomerMan.setCustomerManRegistrationVisible();
            }
        });
        viewCustomerShipperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeCustomerMan(false);
                ViewCustomers.setViewCustomersVisible();
            }
        });
        editCustomerShipperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeCustomerMan(false);
                setEditCustomerShipperVisible();
            }
        });
        deleteCustomerShipperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeCustomerMan(false);
                deleteCustomerShipper.setFrameDeleteCustomerShipperVisible();
            }
        });
    }

    public static void setCustomerManVisible()
    {
        frameCustomerMan = new JFrame("Admin's Main Menu");
        frameCustomerMan.setContentPane(new CustomerMan().panelCustomerMan);
        frameCustomerMan.pack();
        frameCustomerMan.setVisible(true);
        frameCustomerMan.setLocation(520,200);
    }

    public static void giveMeCustomerMan(boolean truth)
    {
        frameCustomerMan.setVisible(truth);
    }
}
