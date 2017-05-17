package GUI_Assignment.GUI_Forms.For_Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI_Assignment.GUI_Forms.For_Customer.CU_EditCustomerShipper.setEditCustomerShipperVisible;
import static GUI_Assignment.GUI_Forms.For_Customer.CU_RegisterCustomerMan.setCustomerManRegistrationVisible;
import static GUI_Assignment.GUI_Forms.For_Customer.CU_ViewCustomersShippers.setViewCustomersVisible;
import static GUI_Assignment.GUI_Forms.For_Customer.main_customer_form.giveMeMainCustomerForm;

/**
 * Created by adil on 28/11/16.
 */
public class CU_CustomerMan {
    private JPanel panelCustomerMan;
    private JButton registerCustomerShipperButton;
    private JButton editCustomerShipperButton;
    private JButton deleteCustomerShipperButton;
    private JButton viewCustomerShipperButton;
    private JButton returnButton;
    private static JFrame frameCU_CustomerMan;

    public CU_CustomerMan() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeMainCustomerForm(true);
                giveMeCU_CustomerMan(false);
            }
        });
        registerCustomerShipperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setCustomerManRegistrationVisible();
                giveMeCU_CustomerMan(false);
            }
        });
        editCustomerShipperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEditCustomerShipperVisible();
                giveMeCU_CustomerMan(false);
            }
        });
        viewCustomerShipperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setViewCustomersVisible();
                giveMeCU_CustomerMan(false);
            }
        });
        deleteCustomerShipperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CU_deleteCustomerShippers.setFrameDeleteCustomerShipperVisible();
                giveMeCU_CustomerMan(false);
            }
        });
    }

    public static void setCU_CustomerManVisible()
    {
        frameCU_CustomerMan = new JFrame("Customer's Main Menu");
        frameCU_CustomerMan.setContentPane(new CU_CustomerMan().panelCustomerMan);
        frameCU_CustomerMan.pack();
        frameCU_CustomerMan.setVisible(true);
        frameCU_CustomerMan.setLocation(520,200);
    }

    public static void giveMeCU_CustomerMan(boolean truth)
    {
        frameCU_CustomerMan.setVisible(truth);
    }
}
