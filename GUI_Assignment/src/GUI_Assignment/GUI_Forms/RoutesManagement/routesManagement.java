package GUI_Assignment.GUI_Forms.RoutesManagement;

import GUI_Assignment.Routes_Management.Route;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static GUI_Assignment.GUI_Forms.For_Admin.main_admin_form.giveMeAdmin;
import static GUI_Assignment.GUI_Forms.RoutesManagement.EditRoutes.setEditRoutesVisible;
import static GUI_Assignment.IDCounter.RoutesIDCounter;
import static GUI_Assignment.fileWorker.fileWriter;

/**
 * Created by adil on 27/11/16.
 */
public class routesManagement {
    private JPanel panelRoutesManagement;
    private JTextArea textAreaAvailableRoutes;
    private JButton showAllRoutesButton;
    private JButton addNewRouteButton;
    private JButton editRoutesButton;
    private JButton deleteRoutesButton;
    private JButton returnButton;
    private static JFrame frameRoutesManagement;


    public routesManagement() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeRoutesManagement(false);
                giveMeAdmin(true);
            }
        });
        showAllRoutesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uploadTable();
            }
        });
        addNewRouteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewRoutes.setAddNewRoutesVisible();
            }
        });
        editRoutesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEditRoutesVisible();
            }
        });
        deleteRoutesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteRoutes.setDeleteRoutesVisible();
            }
        });
    }

    public static void setRoutesManagementVisible() {
        frameRoutesManagement = new JFrame("Client's Report");
        frameRoutesManagement.setContentPane(new routesManagement().panelRoutesManagement);
        frameRoutesManagement.pack();
        frameRoutesManagement.setVisible(true);
        frameRoutesManagement.setLocation(450, 80);
    }

    public static void giveMeRoutesManagement(boolean truth) {
        frameRoutesManagement.setVisible(truth);
    }

    public static void routeDownloader() {
        Route port1 = new Route(RoutesIDCounter(), "New York1", 99);
        Route port2 = new Route(RoutesIDCounter(), "Troy Lock", 134);
        Route port3 = new Route(RoutesIDCounter(), "Watervilet", 132);
        Route port4 = new Route(RoutesIDCounter(), "Troyania", 132);
        Route port5 = new Route(RoutesIDCounter(), "Renseeker", 126);
        Route port6 = new Route(RoutesIDCounter(), "Albany", 126);
        Route port7 = new Route(RoutesIDCounter(), "Coeymans", 126);
        Route port8 = new Route(RoutesIDCounter(), "Athens", 108);
        Route port9 = new Route(RoutesIDCounter(), "Hudson", 102);
        Route port10 = new Route(RoutesIDCounter(), "Catskill", 99);
        Route port11 = new Route(RoutesIDCounter(), "Poughkeepsie", 89);
        Route port12 = new Route(RoutesIDCounter(), "Kingston", 80);
        Route port13 = new Route(RoutesIDCounter(), "Hyde Park", 71);
        Route port14 = new Route(RoutesIDCounter(), "Ossinina", 66);
        Route port15 = new Route(RoutesIDCounter(), "Newburgh", 53);
        Route port16 = new Route(RoutesIDCounter(), "West Point", 45);
        Route port17 = new Route(RoutesIDCounter(), "Peeksskill", 38);
        Route port18 = new Route(RoutesIDCounter(), "Haverstraw", 33);
        Route port19 = new Route(RoutesIDCounter(), "Saugerties", 29);
        Route port20 = new Route(RoutesIDCounter(), "New York2", 134);

        ArrayList<Route> routesArray = new ArrayList<Route>();
        routesArray.add(port1);
        routesArray.add(port2);
        routesArray.add(port3);
        routesArray.add(port4);
        routesArray.add(port5);
        routesArray.add(port6);
        routesArray.add(port7);
        routesArray.add(port8);
        routesArray.add(port9);
        routesArray.add(port10);
        routesArray.add(port11);
        routesArray.add(port12);
        routesArray.add(port13);
        routesArray.add(port14);
        routesArray.add(port15);
        routesArray.add(port16);
        routesArray.add(port17);
        routesArray.add(port18);
        routesArray.add(port19);
        routesArray.add(port20);

        fileWriter(routesArray, "dataRoutes");
    }

    public void uploadTable()
    {
        firstLoop:
        {
            int invite = 0;

            String report = "";

            ArrayList<Route> ListBack = null;
            try {
                FileInputStream fileIn = new FileInputStream("dataRoutes");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                ListBack = (ArrayList<Route>) in.readObject();
                in.close();
                fileIn.close();
            } catch (Exception ex) {
                invite++;
                JOptionPane.showMessageDialog(panelRoutesManagement, "System does not have any data, yet.");
                routeDownloader();
            }
            if (invite == 0) {
                for (Route x : ListBack) {
                    report = String.format(report) + String.format("%-10s\t",x.getPortNumber()+")") + String.format("%-10s\t","Port => " + x.getPortName()) + "Price => " + x.getPriceFrom() + "\n";
                }

                if (ListBack.size() == 0) {
                    JOptionPane.showMessageDialog(panelRoutesManagement, "System does not have any routes.");
                    break firstLoop;
                }



            }
            textAreaAvailableRoutes.setText(report);
        }
    }
}
