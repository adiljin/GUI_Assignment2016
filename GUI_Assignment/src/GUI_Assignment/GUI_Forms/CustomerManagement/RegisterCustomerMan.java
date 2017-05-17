package GUI_Assignment.GUI_Forms.CustomerManagement;

import GUI_Assignment.Essential_Classes.Customer;
import GUI_Assignment.fileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static GUI_Assignment.GUI_Forms.CustomerManagement.CustomerMan.giveMeCustomerMan;
import static GUI_Assignment.IDCounter.idCounterCustomer;

/**
 * Created by adil on 27/11/16.
 */
public class RegisterCustomerMan {
    private JPanel panelRegisterCompany;
    private JTextField textFieldNameOfCust;
    private JTextField textFieldAddress;
    private JTextField textFieldPhoneNumber;
    private JTextField textFieldE_Mail;
    private JButton saveButton;
    private JButton cancelButton;
    private static JFrame frameCustomerMan;

    public RegisterCustomerMan() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeCustomerRegistrationMan(false);
                giveMeCustomerMan(true);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id;
                String name;
                String address;
                String number;
                String e_mail;

                try
                {
                    name = textFieldNameOfCust.getText();
                    address = textFieldAddress.getText();
                    number = textFieldPhoneNumber.getText();
                    e_mail = textFieldE_Mail.getText();

                    if(name.equals("") || address.equals("") || number.equals("") ||e_mail.equals(""))
                    {
                        JOptionPane.showMessageDialog(panelRegisterCompany, "Something is not filled, make sure to fill each section");
                    }
                    else
                    {
                        id = idCounterCustomer();
                        Customer customer = new Customer(id, name, address, number, e_mail);

                        int dialogButton = JOptionPane.YES_NO_OPTION;
                        int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to save data?","Warning",dialogButton);
                        if(dialogResult == JOptionPane.YES_OPTION)
                        {
                            ArrayList<Customer> ListBackup = new ArrayList<Customer>();

                            //checking about existing files
                            ListBackup = fileWorker.existFileCheckerCustomer(ListBackup, "dataCustomer");

                            ListBackup.add(customer);

                            //write into a file
                            fileWorker.fileWriter(ListBackup, "dataCustomer");
                            giveMeCustomerRegistrationMan(false);
                            giveMeCustomerMan(true);
                        }
                    }
                }
                catch(Exception ex)
                {

                }
            }
        });
    }

    public static void setCustomerManRegistrationVisible()
    {
        frameCustomerMan = new JFrame("Customer/Shipper's Registration");
        frameCustomerMan.setContentPane(new RegisterCustomerMan().panelRegisterCompany);
        frameCustomerMan.pack();
        frameCustomerMan.setVisible(true);
        frameCustomerMan.setLocation(520,200);
    }

    public static void giveMeCustomerRegistrationMan(boolean truth)
    {
        frameCustomerMan.setVisible(truth);
    }
}
