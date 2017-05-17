package GUI_Assignment.GUI_Forms.ClientManagement;

import GUI_Assignment.Essential_Classes.Company;
import GUI_Assignment.fileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by adil on 26/11/16.
 */
public class deleteShippingCompany {
    private JPanel panelDeleteShipping;
    private JTextField textField1;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton cancelButton;
    private JTextArea textArea1;
    private static JFrame frameDeleteShipping;

    private String IDToDelete = "";
    private String NameToDelete = "";

    public deleteShippingCompany() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeDeleteShippingCompany(false);
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
                    JOptionPane.showMessageDialog(panelDeleteShipping, "System does not have any data.");
                }

                int infoCkecker = 0;
                String ID = "";
                ID = textField1.getText();
                if(ID.equals(""))
                {
                    JOptionPane.showMessageDialog(panelDeleteShipping, "You must write ID of particular company.");
                }
                else
                {
                    String report="";

                    if(invite==0)
                    {
                        for(Company x : ListBack)
                        {
                            if(x.getId().equals(ID.toUpperCase()))
                            {
                                IDToDelete = x.getId();
                                NameToDelete = x.getName();
                                report = report + "ID: " + x.getId() + "\n";
                                report = report + "Name of organization: " + x.getName() + "\n";
                                report = report + "Address: " + x.getAddress() + "\n";
                                report = report + "Phone number: " + x.getNumber() + "\n";
                                report = report + "E-mail: " + x.getE_mail() + "\n";
                                report = report + "\n";
                                report = report + "Ships type: " + x.getShip().getClass().getSimpleName() + "\n";
                                report = report + "Ships ID: " + x.getShip().getShipID() + "\n";
                                report = report + "Name of the ship: " + x.getShip().getShipName() + "\n";
                                report = report + "Address of the port: " + x.getShip().getShipAddress() + "\n";
                                report = report + "Min of weight: " + x.getShip().getWeightMin() + "\n";
                                report = report + "Max of weight: " + x.getShip().getWeightMax() + "\n";
                                report = report + "\n";
                                report = report + "Type of Leasing: " + x.getShip().getTypeLease() + "\n";
                                report = report + "Years of leasing: " + x.getShip().getYears() + "\n";
                                report = report + "Price per year(without percent): " + x.getShip().getPricePerYear() + "\n";
                                report = report + "Total price: " + x.getShip().getPrice() + "\n";
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
                            JOptionPane.showMessageDialog(panelDeleteShipping, "There's no such Company");
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
                    ArrayList<Company> ListBack = null;
                    try {
                        FileInputStream fileIn = new FileInputStream("dataClient");
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        ListBack = (ArrayList<Company>) in.readObject();
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
                        JOptionPane.showMessageDialog(panelDeleteShipping, "You must write ID of particular company.");
                    }
                    else
                    {
                        if(invite==0)
                        {
                            int deleted = 0;

                            Iterator<Company> iter = ListBack.iterator();

                            while(iter.hasNext())
                            {
                                Company check = iter.next();
                                if(check.getId().equals(IDToDelete))
                                {
                                    int dialogButton = JOptionPane.YES_NO_OPTION;
                                    int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to remove " + NameToDelete +" company completely?","Warning",dialogButton);
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
                                JOptionPane.showMessageDialog(panelDeleteShipping, "Write ID of the company, and then try to delete again!");
                                break firstLoop;
                            }

                                fileWorker.fileWriter(ListBack, "dataClient");
                                JOptionPane.showMessageDialog(panelDeleteShipping, "Information successfully deleted.");
                                giveMeDeleteShippingCompany(false);
                                clientMan.giveMeClientMan(true);

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

    public static void setFrameDeleteShipping()
    {
        frameDeleteShipping = new JFrame("Delete Client's information");
        frameDeleteShipping.setContentPane(new deleteShippingCompany().panelDeleteShipping);
        frameDeleteShipping.pack();
        frameDeleteShipping.setVisible(true);
        frameDeleteShipping.setLocation(520,200);
    }

    public static void giveMeDeleteShippingCompany(boolean truth)
    {
        frameDeleteShipping.setVisible(truth);
    }
}
