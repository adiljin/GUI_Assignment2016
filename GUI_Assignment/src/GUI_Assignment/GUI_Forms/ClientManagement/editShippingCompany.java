package GUI_Assignment.GUI_Forms.ClientManagement;

import GUI_Assignment.Essential_Classes.Company;
import GUI_Assignment.fileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by adil on 26/11/16.
 */
public class editShippingCompany {
    private JPanel panelEditShipping;
    private JTextField textField1;
    private JButton searchButton;
    private JRadioButton radioButtonName;
    private JRadioButton radioButtonAddress;
    private JRadioButton radioButtonNumberEmail;
    private JRadioButton eMailRadioButton;
    private JTextField textField2;
    private JButton changeButton;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton cancelButton;
    private static JFrame frameEditShipping;

    public editShippingCompany() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeEditShippingCompany(false);
                clientMan.giveMeClientMan(true);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int invite=0;
                ArrayList<Company> ListBack = null;
                try {
                    FileInputStream fileIn = new FileInputStream("dataClient");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    ListBack = (ArrayList<Company>) in.readObject();
                    in.close();
                    fileIn.close();
                }catch (Exception ex) {
                    invite++;
                    JOptionPane.showMessageDialog(panelEditShipping, "System does not have any data.");
                }

                String ID = "";
                ID = textField1.getText();
                if(ID.equals(""))
                {
                    JOptionPane.showMessageDialog(panelEditShipping, "You must write ID of particular company.");
                }
                else
                {
                    if(invite==0)
                    {
                        for(Company x : ListBack)
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
                            JOptionPane.showMessageDialog(panelEditShipping, "There's no such ID, or you made a mistake!");
                        }

                        if(ListBack.size()==0)
                        {
                            JOptionPane.showMessageDialog(panelEditShipping, "There's no such Company");
                        }
                    }
                }
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
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Changer
                firstLoop:
                {
                    int changeChecker = 0;
                    int invite=0;
                    ArrayList<Company> ListBack = null;
                    try {
                        FileInputStream fileIn = new FileInputStream("dataClient");
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        ListBack = (ArrayList<Company>) in.readObject();
                        in.close();
                        fileIn.close();
                    }catch (Exception ex) {
                        invite++;
                        JOptionPane.showMessageDialog(panelEditShipping, "System does not have any data.");
                    }

                    String ID = "";
                    ID = textField1.getText();
                    if(ID.equals(""))
                    {
                        JOptionPane.showMessageDialog(panelEditShipping, "You must write ID of particular company.");
                    }
                    else
                    {
                        if(invite==0)
                        {

                            for(Company x : ListBack)
                            {
                                if(x.getId().equals(ID.toUpperCase()))
                                {
                                    if(textField2.getText().equals("") || textField3.getText().equals("") || textField4.getText().equals("") || textField5.getText().equals(""))
                                    {
                                        JOptionPane.showMessageDialog(panelEditShipping, "One of the sections is empty, rewrite or cancel operation.");
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
                                    }
                                }
                            }

                            if(changeChecker==0)
                            {
                                JOptionPane.showMessageDialog(panelEditShipping, "You did not change anything, change or cancel operation!");
                                break firstLoop;
                            }


                            if(ListBack.size()==0)
                            {
                                JOptionPane.showMessageDialog(panelEditShipping, "There's no such Company");
                            }
                            else
                            {
                                fileWorker.fileWriter(ListBack, "dataClient");
                                JOptionPane.showMessageDialog(panelEditShipping, "Information successfully saved.");
                                giveMeEditShippingCompany(false);
                                clientMan.giveMeClientMan(true);
                            }
                        }
                    }
                }
            }
        });
    }

    public static void setClientShippingCompany()
    {
        frameEditShipping = new JFrame("Edit Client's information");
        frameEditShipping.setContentPane(new editShippingCompany().panelEditShipping);
        frameEditShipping.pack();
        frameEditShipping.setVisible(true);
        frameEditShipping.setLocation(520,200);
    }

    public static void giveMeEditShippingCompany(boolean truth)
    {
        frameEditShipping.setVisible(truth);
    }
}
