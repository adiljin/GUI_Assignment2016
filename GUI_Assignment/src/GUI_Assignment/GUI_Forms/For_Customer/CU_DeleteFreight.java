package GUI_Assignment.GUI_Forms.For_Customer;

import GUI_Assignment.Essential_Classes.Freight;
import GUI_Assignment.fileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by adil on 28/11/16.
 */
public class CU_DeleteFreight {
    private JPanel panelDeleteShipping;
    private JTextField textField1;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton cancelButton;
    private JTextArea textArea1;
    private static JFrame frameDeleteFreight;
    private String IDToDelete;
    private String NameToDelete;

    public CU_DeleteFreight() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CU_FreightMan.giveMeFreightsMan(true);
                giveMeDeleteFreight(false);
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
                    JOptionPane.showMessageDialog(panelDeleteShipping, "System does not have any data.");
                }

                int go = 0;
                String ID = "";
                ID = textField1.getText().toUpperCase();
                if(ID.equals(""))
                {
                    JOptionPane.showMessageDialog(panelDeleteShipping, "You must write ID of particular customer/shipper.");
                }
                else
                {
                    String report = "";
                    if(invite==0)
                    {
                        for(Freight x : ListBack)
                        {
                            if(x.getId().equals(ID))
                            {
                                IDToDelete = x.getId();
                                NameToDelete = x.getType();
                                report = report + "ID: " + x.getId() + "\n";
                                report = report + "Type of freight: " + x.getType() + "\n";
                                report = report + "Weight: " + x.getWeight() + "\n";
                                report = report + "Price: " + x.getPrice() + "\n";
                                report = report + "" + "\n";
                                report = report + "\n";
                                report = report + "Customer's ID " + x.getCustomer().getId() + "\n";
                                report = report + "Customer's name " + x.getCustomer().getName() + "\n";
                                report = report + "" + "\n";
                                report = report + "Ships's ID: " + x.getShip().getShipID() + "\n";
                                report = report + "Ships's type: " + x.getShip().getClass().getSimpleName() + "\n";
                                report = report + "Routes type: " + x.getRouteType() + " ports" + "\n";
                                report = report + "" + "\n";
                                report = report + "Start point: " + x.getChosenRoute().getPort1() + "\n";
                                report = report + "Final point: " + x.getChosenRoute().getPort2() + "\n";
                                report = report + "Total price: " + x.getPrice() + "\n";
                                report = report + "-----------------------------" + "\n";
                                report = report + "" + "\n";
                                go++;
                            }
                        }

                        textArea1.setText(report);

                        if(go==0)
                        {
                            JOptionPane.showMessageDialog(panelDeleteShipping, "There's no such freight, or you made a mistake");
                        }

                        if(ListBack.size()==0)
                        {
                            JOptionPane.showMessageDialog(panelDeleteShipping, "There's no such freight");
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
                    ArrayList<Freight> ListBack = null;
                    try {
                        FileInputStream fileIn = new FileInputStream("dataFreight");
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        ListBack = (ArrayList<Freight>) in.readObject();
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

                            Iterator<Freight> iter = ListBack.iterator();

                            while(iter.hasNext())
                            {
                                Freight check = iter.next();
                                if(check.getId().equals(IDToDelete))
                                {
                                    int dialogButton = JOptionPane.YES_NO_OPTION;
                                    int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to remove " + NameToDelete +" freight completely?","Warning",dialogButton);
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


                            fileWorker.fileWriter(ListBack, "dataFreight");
                            JOptionPane.showMessageDialog(panelDeleteShipping, "Information successfully deleted.");
                            giveMeDeleteFreight(false);
                            CU_FreightMan.giveMeFreightsMan(true);

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

    public static void setDeleteFreightVisible()
    {
        frameDeleteFreight = new JFrame("Delete freight");
        frameDeleteFreight.setContentPane(new CU_DeleteFreight().panelDeleteShipping);
        frameDeleteFreight.pack();
        frameDeleteFreight.setVisible(true);
        frameDeleteFreight.setLocation(520,180);
    }

    public static void giveMeDeleteFreight(boolean truth)
    {
        frameDeleteFreight.setVisible(truth);
    }
}
