package GUI_Assignment.GUI_Forms.FreightsManagement;

import GUI_Assignment.Essential_Classes.Freight;
import GUI_Assignment.Routes_Management.Route;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static GUI_Assignment.GUI_Forms.FreightsManagement.FreightsMan.giveMeFreightsMan;

/**
 * Created by adil on 28/11/16.
 */
public class ViewFreights {
    private JPanel panelViewCompanies;
    private JButton loadInformationButton;
    private JButton cancelButton;
    private JTextArea textAreaCurrentFreights;
    private static JFrame frameViewFreights;

    public ViewFreights() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeFreightsMan(true);
                giveMeViewFreights(false);

            }
        });
        loadInformationButton.addActionListener(new ActionListener() {
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
                    JOptionPane.showMessageDialog(panelViewCompanies, "System does not have any data.");
                }

                String NameRoute = "";
                int PriceRoute = 0;

                ArrayList<Route> ListBack2 = null;
                try {
                    FileInputStream fileIn = new FileInputStream("dataRoutes");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    ListBack2 = (ArrayList<Route>) in.readObject();
                    in.close();
                    fileIn.close();
                }catch (Exception ex) {
                    ListBack2 = new ArrayList<Route>();
                }

                String portName1 = null, portName2 = null;

                String report="";

                if(invite==0)
                {
                    for(Freight x : ListBack)
                    {
                        for(Route y : ListBack2)
                        {
                            if(x.getChosenRoute().getPort1()==y.getPortNumber())
                            {
                                portName1 = y.getPortName();
                            }
                            if(x.getChosenRoute().getPort2()==y.getPortNumber())
                            {
                                portName2 = y.getPortName();
                            }
                        }
                        report = report + "ID: " + x.getId() + "\n";
                        report = report + "Type of freight: " + x.getType() + "\n";
                        report = report + "Weight: " + x.getWeight() + "\n";
                        report = report + "Price: " + x.getPrice() + "\n";
                        report = report + "" + "\n";
                        report = report + "Customer's ID " + x.getCustomer().getId() + "\n";
                        report = report + "Customer's name " + x.getCustomer().getName() + "\n";
                        report = report + "" + "\n";
                        report = report + "Ships's ID: " + x.getShip().getShipID() + "\n";
                        report = report + "Ships's type: " + x.getShip().getClass().getSimpleName() + "\n";
                        report = report + "Routes type: " + x.getRouteType() + " ports" + "\n";
                        report = report + "" + "\n";
                        report = report + "Start point: [" + x.getChosenRoute().getPort1() + "] [" + portName1 + "]\n";
                        report = report + "Final point: [" + x.getChosenRoute().getPort2() + "] [" + portName2 + "]\n";
                        report = report + "" + "\n";
                        report = report + "Total price: " + x.getPrice() + "\n";
                        report = report + "-----------------------------" + "\n";
                        report = report + "" + "\n";
                    }

                    textAreaCurrentFreights.setText(report);

                    if(ListBack.size()==0)
                    {
                        textAreaCurrentFreights.setText("System does not have any data");
                    }
                }
            }
        });
    }

    public static void setFrameViewFreightVisible()
    {
        frameViewFreights = new JFrame("Freight's Report");
        frameViewFreights.setContentPane(new ViewFreights().panelViewCompanies);
        frameViewFreights.pack();
        frameViewFreights.setVisible(true);
        frameViewFreights.setLocation(480,150);
    }

    public static void giveMeViewFreights(boolean truth)
    {
        frameViewFreights.setVisible(truth);
    }
}
