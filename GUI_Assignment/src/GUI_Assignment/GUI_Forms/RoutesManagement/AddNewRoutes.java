package GUI_Assignment.GUI_Forms.RoutesManagement;

import GUI_Assignment.Routes_Management.Route;
import GUI_Assignment.fileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static GUI_Assignment.IDCounter.RoutesIDCounter;

/**
 * Created by adil on 27/11/16.
 */
public class AddNewRoutes {
    private JPanel panelRegisterCompany;
    private JTextField textFieldNameOfCust;
    private JTextField textFieldAddress;
    private JButton saveButton;
    private JButton cancelButton;
    private static JFrame frameAddnewRoutes;

    public AddNewRoutes() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeAddNewRoutes(false);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstLoop:
                {
                    String NameRoute = "";
                    int PriceRoute = 0;

                    ArrayList<Route> ListBack = null;
                    try {
                        FileInputStream fileIn = new FileInputStream("dataRoutes");
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        ListBack = (ArrayList<Route>) in.readObject();
                        in.close();
                        fileIn.close();
                    }catch (Exception ex) {
                        ListBack = new ArrayList<Route>();
                    }

                    try
                    {
                        NameRoute = textFieldNameOfCust.getText();
                        PriceRoute = Integer.parseInt(textFieldAddress.getText());
                    }
                    catch(Exception ex)
                    {

                    }


                    try
                    {
                        if(!NameRoute.equals("") && PriceRoute > 0)
                        {
                            Route route = new Route(RoutesIDCounter(), NameRoute, PriceRoute);

                            ListBack.add(route);

                            fileWorker.fileWriter(ListBack, "dataRoutes");
                            JOptionPane.showMessageDialog(panelRegisterCompany, "New Route successfully saved");
                            giveMeAddNewRoutes(false);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(panelRegisterCompany, "Each section must be filled, price must be more than zero");
                        }
                    }
                    catch(Exception ex)
                    {

                    }
                }
            }

        });
    }

    public static void setAddNewRoutesVisible() {
        frameAddnewRoutes = new JFrame("Add new route");
        frameAddnewRoutes.setContentPane(new AddNewRoutes().panelRegisterCompany);
        frameAddnewRoutes.pack();
        frameAddnewRoutes.setVisible(true);
        frameAddnewRoutes.setLocation(450, 80);
    }

    public static void giveMeAddNewRoutes(boolean truth) {
        frameAddnewRoutes.setVisible(truth);
    }
}
