package GUI_Assignment.GUI_Forms.For_Customer;

import GUI_Assignment.Essential_Classes.Freight;
import GUI_Assignment.fileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by adil on 28/11/16.
 */
public class CU_EditFreight {
    private JPanel panelEditCustomerShipper;
    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton radioButtonName;
    private JRadioButton radioButtonAddress;
    private JTextField textField3;
    private JButton searchButton;
    private JButton changeButton;
    private JButton cancelButton;
    private static JFrame frameEditFreight;

    public CU_EditFreight() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CU_FreightMan.giveMeFreightsMan(true);
                giveMeEditFreight(false);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int invite=0;
                ArrayList<Freight> ListBack = null;
                try {
                    FileInputStream fileIn = new FileInputStream("dataFreight");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    ListBack = (ArrayList<Freight>) in.readObject();
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
                        for(Freight x : ListBack)
                        {
                            if(x.getId().equals(ID.toUpperCase()))
                            {
                                radioButtonName.setEnabled(true);
                                radioButtonAddress.setEnabled(true);

                                textField2.setText(x.getType());
                                textField3.setText(x.getWeight());
                            }
                        }

                        if(textField2.getText().equals(""))
                        {
                            JOptionPane.showMessageDialog(panelEditCustomerShipper, "There's no such ID, or you made a mistake!");
                        }

                        if(ListBack.size()==0)
                        {
                            JOptionPane.showMessageDialog(panelEditCustomerShipper, "There's no such Customer/Shipper");
                        }
                    }
                }
            }
        });
        radioButtonName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField2.setEditable(true);
                textField3.setEditable(false);
            }
        });
        radioButtonAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField2.setEditable(false);
                textField3.setEditable(true);
            }
        });
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstLoop:
                {
                    int changeChecker = 0;
                    int invite=0;
                    ArrayList<Freight> ListBack = null;
                    try {
                        FileInputStream fileIn = new FileInputStream("dataFreight");
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        ListBack = (ArrayList<Freight>) in.readObject();
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

                            for(Freight x : ListBack)
                            {
                                if(x.getId().equals(ID.toUpperCase()))
                                {
                                    if(textField2.getText().equals("") || textField3.getText().equals(""))
                                    {
                                        JOptionPane.showMessageDialog(panelEditCustomerShipper, "One of the sections is empty, rewrite or cancel operation.");
                                        break firstLoop;
                                    }
                                    else
                                    {
                                        if(!textField2.getText().equals(x.getType()))
                                        {
                                            x.setType(textField2.getText());
                                            changeChecker++;
                                        }
                                        if(!textField3.getText().equals(x.getWeight()))
                                        {
                                            try
                                            {
                                                if(Integer.parseInt(textField3.getText()) <= Integer.parseInt(x.getWeight()))
                                                {
                                                    x.setWeight(textField3.getText());
                                                    changeChecker++;
                                                }
                                                else if (Integer.parseInt(textField3.getText()) > Integer.parseInt(x.getWeight()))
                                                {
                                                    JOptionPane.showMessageDialog(panelEditCustomerShipper, "You cannot make weight bigger, only smaller.");
                                                    break firstLoop;
                                                }
                                            }
                                            catch(Exception ex)
                                            {

                                            }
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
                                fileWorker.fileWriter(ListBack, "dataFreight");
                                JOptionPane.showMessageDialog(panelEditCustomerShipper, "Information successfully saved.");

                                giveMeEditFreight(false);
                                CU_FreightMan.giveMeFreightsMan(true);
                            }
                        }
                    }
                }
            }
        });
    }

    public static void setEditFreightVisible()
    {
        frameEditFreight = new JFrame("Edit freight");
        frameEditFreight.setContentPane(new CU_EditFreight().panelEditCustomerShipper);
        frameEditFreight.pack();
        frameEditFreight.setVisible(true);
        frameEditFreight.setLocation(520,200);
    }

    public static void giveMeEditFreight(boolean truth)
    {
        frameEditFreight.setVisible(truth);
    }
}

