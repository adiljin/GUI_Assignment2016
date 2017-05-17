package GUI_Assignment.GUI_Forms.CustomerManagement;

import GUI_Assignment.Essential_Classes.Customer;
import GUI_Assignment.fileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import static GUI_Assignment.GUI_Forms.CustomerManagement.CustomerMan.giveMeCustomerMan;

/**
 * Created by adil on 27/11/16.
 */
public class deleteCustomerShipper {
    private JPanel panelDeleteShipping;
    private JTextField textField1;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton cancelButton;
    private JTextArea textArea1;
    private static JFrame frameDeleteCustomerShipper;

    private String IDToDelete = "";
    private String NameToDelete = "";

    public deleteCustomerShipper() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeDeleteCustomerShipper(false);
                giveMeCustomerMan(true);
            }
        });
        searchButton.addActionListener(new ActionListener() {
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
                    JOptionPane.showMessageDialog(panelDeleteShipping, "System does not have any data.");
                }

                int infoCkecker = 0;
                String ID = "";
                ID = textField1.getText();
                if(ID.equals(""))
                {
                    JOptionPane.showMessageDialog(panelDeleteShipping, "You must write ID of particular customer/shipper.");
                }
                else
                {
                    String report="";

                    if(invite==0)
                    {
                        for(Customer x : ListBack)
                        {
                            if(x.getId().equals(ID.toUpperCase()))
                            {
                                IDToDelete = x.getId();
                                NameToDelete = x.getName();
                                report = report + "ID: " + x.getId() + "\n";
                                report = report + "Name of Customer/Shipper: " + x.getName() + "\n";
                                report = report + "Address: " + x.getAddress() + "\n";
                                report = report + "Phone number: " + x.getNumber() + "\n";
                                report = report + "E-mail: " + x.getE_mail() + "\n";
                                report = report + "-----------------------" + "\n";
                                infoCkecker++;
                            }

                            textArea1.setText(report);
                        }

                        if(infoCkecker==0)
                        {
                            JOptionPane.showMessageDialog(panelDeleteShipping, "There's no such ID, or you made a mistake!");
                            IDToDelete = "";
                        }

                        if(ListBack.size()==0)
                        {
                            JOptionPane.showMessageDialog(panelDeleteShipping, "There's no such Customer/Shipper");
                        }
                    }
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstLoop:
                {
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
                        JOptionPane.showMessageDialog(panelDeleteShipping, "System does not have any data.");
                    }

                    String ID = "";
                    ID = textField1.getText();
                    if(ID.equals(""))
                    {
                        JOptionPane.showMessageDialog(panelDeleteShipping, "You must write ID of particular customer/shipper.");
                    }
                    else
                    {
                        if(invite==0)
                        {
                            int deleted = 0;

                            Iterator<Customer> iter = ListBack.iterator();

                            while(iter.hasNext())
                            {
                                Customer check = iter.next();
                                if(check.getId().equals(IDToDelete))
                                {
                                    int dialogButton = JOptionPane.YES_NO_OPTION;
                                    int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to remove " + NameToDelete +" customer/shipper completely?","Warning",dialogButton);
                                    if(dialogResult == JOptionPane.YES_OPTION)
                                    {
                                        ListBack.remove(check);
                                        deleted++;
                                        break;
                                    }
                                }
                            }
                            if(deleted==0)
                            {
                                JOptionPane.showMessageDialog(panelDeleteShipping, "Write ID of the customer, and then try to delete again!");
                                break firstLoop;
                            }


                                fileWorker.fileWriter(ListBack, "dataCustomer");
                                JOptionPane.showMessageDialog(panelDeleteShipping, "Information successfully deleted.");
                                giveMeDeleteCustomerShipper(false);
                                giveMeCustomerMan(true);

                            if(ListBack.size()==0)
                            {
                                JOptionPane.showMessageDialog(panelDeleteShipping, "There's no data left");
                            }
                        }
                    }
                }
            }
        });
    }

    public static void setFrameDeleteCustomerShipperVisible()
    {
        frameDeleteCustomerShipper = new JFrame("Delete Customer's information");
        frameDeleteCustomerShipper.setContentPane(new deleteCustomerShipper().panelDeleteShipping);
        frameDeleteCustomerShipper.pack();
        frameDeleteCustomerShipper.setVisible(true);
        frameDeleteCustomerShipper.setLocation(520,200);
    }

    public static void giveMeDeleteCustomerShipper(boolean truth)
    {
        frameDeleteCustomerShipper.setVisible(truth);
    }
}
