package GUI_Assignment.GUI_Forms.CustomerManagement;

import GUI_Assignment.Essential_Classes.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static GUI_Assignment.GUI_Forms.CustomerManagement.CustomerMan.giveMeCustomerMan;

/**
 * Created by adil on 27/11/16.
 */
public class ViewCustomers {
    private JPanel panelViewCustomers;
    private JButton loadInformationButton;
    private JButton cancelButton;
    private JTextArea textAreaCurrentCompanies;
    private static JFrame frameViewCustomers;

    public ViewCustomers() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeViewCustomers(false);
                giveMeCustomerMan(true);
            }
        });
        loadInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int invite=0;
                ArrayList<Customer> ListBack = null;
                try {
                    FileInputStream fileIn = new FileInputStream("dataCustomer");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    ListBack = (ArrayList<Customer>) in.readObject();
                    in.close();
                    fileIn.close();
                }catch (Exception ex) {
                    invite++;
                    JOptionPane.showMessageDialog(panelViewCustomers, "System does not have any data.");
                }

                String report="";

                if(invite==0)
                {
                    for(Customer x : ListBack)
                    {
                        report = report + "ID: " + x.getId() + "\n";
                        report = report + "Name of customer/shipper: " + x.getName() + "\n";
                        report = report + "Address: " + x.getAddress() + "\n";
                        report = report + "Phone number: " + x.getNumber() + "\n";
                        report = report + "E-mail: " + x.getE_mail() + "\n";
                        report = report + "-----------------------" + "\n";
                    }

                    textAreaCurrentCompanies.setText(report);

                    if(ListBack.size()==0)
                    {
                        textAreaCurrentCompanies.setText("System does not have any data");
                    }
                }
            }
        });
    }

    public static void setViewCustomersVisible()
    {
        frameViewCustomers = new JFrame("Client's Report");
        frameViewCustomers.setContentPane(new ViewCustomers().panelViewCustomers);
        frameViewCustomers.pack();
        frameViewCustomers.setVisible(true);
        frameViewCustomers.setLocation(450,150);
    }

    public static void giveMeViewCustomers(boolean truth)
    {
        frameViewCustomers.setVisible(truth);
    }
}
