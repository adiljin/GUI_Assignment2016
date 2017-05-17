package GUI_Assignment.GUI_Forms.RoutesManagement;

import GUI_Assignment.Routes_Management.Route;
import GUI_Assignment.fileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import static GUI_Assignment.GUI_Forms.RoutesManagement.routesManagement.giveMeRoutesManagement;

/**
 * Created by adil on 27/11/16.
 */
public class DeleteRoutes {
    private JPanel panelDeleteRoutes;
    private JTextField textField1;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton cancelButton;
    private JTextArea textArea1;
    private static JFrame frameDeleteRoutes;

    private int IDToDelete = 0;
    private String NameToDelete = "";

    public DeleteRoutes() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeDeleteRoutes(false);
                giveMeRoutesManagement(true);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int invite=0;
                ArrayList<Route> ListBack = null;
                try {
                    FileInputStream fileIn = new FileInputStream("dataRoutes");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    ListBack = (ArrayList<Route>) in.readObject();
                    in.close();
                    fileIn.close();
                }catch (Exception ex) {
                    invite++;
                    JOptionPane.showMessageDialog(panelDeleteRoutes, "System does not have any data.");
                }

                int infoCkecker = 0;
                int ID = 0;

                try
                {
                    ID = Integer.parseInt(textField1.getText());
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(panelDeleteRoutes, "Perhaps, you wrote wrong information into ID field.");
                }

                if(ID == 0)
                {
                    JOptionPane.showMessageDialog(panelDeleteRoutes, "You must write ID of particular Route.");
                }
                else
                {
                    String report="";

                    if(invite==0)
                    {
                        for(Route x : ListBack)
                        {
                            if(x.getPortNumber() == ID)
                            {
                                IDToDelete = x.getPortNumber();
                                NameToDelete = x.getPortName();
                                report = report + "ID: " + x.getPortNumber() + "\n";
                                report = report + "Name of port: " + x.getPortName() + "\n";
                                report = report + "Price of port: " + x.getPriceFrom() + "\n";
                                infoCkecker++;
                            }

                            textArea1.setText(report);
                        }

                        if(infoCkecker==0)
                        {
                            JOptionPane.showMessageDialog(panelDeleteRoutes, "There's no such ID, or you made a mistake!");
                            IDToDelete = 0;
                        }

                        if(ListBack.size()==0)
                        {
                            JOptionPane.showMessageDialog(panelDeleteRoutes, "There's no such Customer/Shipper");
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
                    ArrayList<Route> ListBack = null;
                    try {
                        FileInputStream fileIn = new FileInputStream("dataRoutes");
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        ListBack = (ArrayList<Route>) in.readObject();
                        in.close();
                        fileIn.close();
                    }catch (Exception ex) {
                        invite++;
                        JOptionPane.showMessageDialog(panelDeleteRoutes, "System does not have any data.");
                    }

                    String ID = "";
                    ID = textField1.getText();
                    if(ID.equals(""))
                    {
                        JOptionPane.showMessageDialog(panelDeleteRoutes, "You must write ID of particular route.");
                    }
                    else
                    {
                        if(invite==0)
                        {
                            int deleted = 0;

                            Iterator<Route> iter = ListBack.iterator();

                            while(iter.hasNext())
                            {
                                Route check = iter.next();
                                if(check.getPortNumber() == IDToDelete)
                                {
                                    int dialogButton = JOptionPane.YES_NO_OPTION;
                                    int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to remove " + NameToDelete +" route completely?","Warning",dialogButton);
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
                                JOptionPane.showMessageDialog(panelDeleteRoutes, "Write ID of the route, and then try to delete again!");
                                break firstLoop;
                            }


                            fileWorker.fileWriter(ListBack, "dataRoutes");
                            JOptionPane.showMessageDialog(panelDeleteRoutes, "Information successfully deleted.");
                            giveMeDeleteRoutes(false);
                            giveMeRoutesManagement(true);

                            if(ListBack.size()==0)
                            {
                                JOptionPane.showMessageDialog(panelDeleteRoutes, "There's no data left");
                            }
                        }
                    }
                }
            }
        });
    }

    public static void setDeleteRoutesVisible() {
        frameDeleteRoutes = new JFrame("Delete Routes");
        frameDeleteRoutes.setContentPane(new DeleteRoutes().panelDeleteRoutes);
        frameDeleteRoutes.pack();
        frameDeleteRoutes.setVisible(true);
        frameDeleteRoutes.setLocation(450, 80);
    }

    public static void giveMeDeleteRoutes(boolean truth) {
        frameDeleteRoutes.setVisible(truth);
    }
}
