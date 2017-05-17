package GUI_Assignment.GUI_Forms.CustomerManagement;

import GUI_Assignment.Essential_Classes.Customer;
import GUI_Assignment.fileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by adil on 27/11/16.
 */
public class EditCustomerShipper {
    private JPanel panelEditCustomerShipper;
    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton radioButtonName;
    private JRadioButton radioButtonAddress;
    private JRadioButton radioButtonNumberEmail;
    private JRadioButton eMailRadioButton;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton searchButton;
    private JButton changeButton;
    private JButton cancelButton;
    private static JFrame frameEditCustomerShipper;

    public EditCustomerShipper() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeEditCustomersShippers(false);
                CustomerMan.giveMeCustomerMan(true);
            }
        });
        radioButtonName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonName.isSelected())
                {
                    textField2.setEditable(true);
                    textField3.setEditable(false);
                    textField4.setEditable(false);
                    textField5.setEditable(false);
                }
            }
        });
        radioButtonAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonAddress.isSelected())
                {
                    textField3.setEditable(true);
                    textField2.setEditable(false);
                    textField4.setEditable(false);
                    textField5.setEditable(false);
                }
            }
        });
        radioButtonNumberEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonNumberEmail.isSelected())
                {
                    textField2.setEditable(false);
                    textField3.setEditable(false);
                    textField4.setEditable(true);
                    textField5.setEditable(false);
                }
            }
        });
        eMailRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(eMailRadioButton.isSelected())
                {
                    textField2.setEditable(false);
                    textField3.setEditable(false);
                    textField4.setEditable(false);
                    textField5.setEditable(true);
                }
            }
        });
        changeButton.addActionListener(new ActionListener() {//2
            @Override
            public void actionPerformed(ActionEvent e) {
                //Changer
                firstLoop:
                {
                    int changeChecker = 0;
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
                        JOptionPane.showMessageDialog(panelEditCustomerShipper, "System does not have any data.");
                    }

                    String ID = "";
                    ID = textField1.getText();
                    if(ID.equals(""))
                    {
                        JOptionPane.showMessageDialog(panelEditCustomerShipper, "You must write ID of particular customer/shipper.");
                        break firstLoop;
                    }
                    else
                    {
                        if(invite==0)
                        {

                            for(Customer x : ListBack)
                            {
                                if(x.getId().equals(ID.toUpperCase()))
                                {
                                    if(textField2.getText().equals("") || textField3.getText().equals("") || textField4.getText().equals("") || textField5.getText().equals(""))
                                    {
                                        JOptionPane.showMessageDialog(panelEditCustomerShipper, "One of the sections is empty, rewrite or cancel operation.");
                                        break firstLoop;
                                    }
                                    else
                                    {
                                        if(!textField2.getText().equals(x.getName()))
                                        {
                                            x.setName(textField2.getText());
                                            changeChecker++;
                                        }
                                        if(!textField3.getText().equals(x.getAddress()))
                                        {
                                            x.setAddress(textField3.getText());
                                            changeChecker++;
                                        }
                                        if(!textField4.getText().equals(x.getNumber()))
                                        {
                                            x.setNumber(textField4.getText());
                                            changeChecker++;
                                        }
                                        if(!textField5.getText().equals(x.getE_mail()))
                                        {
                                            x.setE_mail(textField5.getText());
                                            changeChecker++;
                                        }
                                        if(changeChecker==0)
                                        {
                                            JOptionPane.showMessageDialog(panelEditCustomerShipper, "You did not change anything, change or cancel operation!");
                                            break firstLoop;
                                        }
                                    }
                                }
                            }


                            if(ListBack.size()==0)
                            {
                                JOptionPane.showMessageDialog(panelEditCustomerShipper, "There's no such Company");
                            }
                            else
                            {
                                fileWorker.fileWriter(ListBack, "dataCustomer");
                                JOptionPane.showMessageDialog(panelEditCustomerShipper, "Information successfully saved.");

                                giveMeEditCustomersShippers(false);
                                CustomerMan.giveMeCustomerMan(true);
                            }
                        }
                    }
                }
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
                    JOptionPane.showMessageDialog(panelEditCustomerShipper, "System does not have any data.");
                }

                String ID = "";
                ID = textField1.getText();
                if(ID.equals(""))
                {
                    JOptionPane.showMessageDialog(panelEditCustomerShipper, "You must write ID of particular customer/shipper.");
                }
                else
                {
                    if(invite==0)
                    {
                        for(Customer x : ListBack)
                        {
                            if(x.getId().equals(ID.toUpperCase()))
                            {
                                radioButtonName.setEnabled(true);
                                radioButtonAddress.setEnabled(true);
                                radioButtonNumberEmail.setEnabled(true);
                                eMailRadioButton.setEnabled(true);

                                textField2.setText(x.getName());
                                textField3.setText(x.getAddress());
                                textField4.setText(x.getNumber());
                                textField5.setText(x.getE_mail());

                            }
                        }

                        if(textField2.getText().equals(""))
                        {
                            JOptionPane.showMessageDialog(panelEditCustomerShipper, "There's no such ID, or you made a mistake!");
                        }

                        if(ListBack.size()==0)
                        {
                            JOptionPane.showMessageDialog(panelEditCustomerShipper, "There's no such Company");
                        }
                    }
                }
            }
        });
    }

    public static void setEditCustomerShipperVisible()
    {
        frameEditCustomerShipper = new JFrame("Client's Report");
        frameEditCustomerShipper.setContentPane(new EditCustomerShipper().panelEditCustomerShipper);
        frameEditCustomerShipper.pack();
        frameEditCustomerShipper.setVisible(true);
        frameEditCustomerShipper.setLocation(450,150);
    }

    public static void giveMeEditCustomersShippers(boolean truth)
    {
        frameEditCustomerShipper.setVisible(truth);
    }
}
