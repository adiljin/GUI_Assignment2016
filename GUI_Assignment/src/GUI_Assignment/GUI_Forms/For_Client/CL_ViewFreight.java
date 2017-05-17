package GUI_Assignment.GUI_Forms.For_Client;

import GUI_Assignment.Essential_Classes.Freight;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static GUI_Assignment.GUI_Forms.For_Client.main_client_form.giveMeMainClientForm;

/**
 * Created by adil on 28/11/16.
 */
public class CL_ViewFreight {
    private JPanel panelViewCompanies;
    private JButton loadInformationButton;
    private JButton cancelButton;
    private JTextArea textAreaCurrentFreights;
    private static JFrame frameViewFreights;

    public CL_ViewFreight() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeMainClientForm(true);
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

                String report="";

                if(invite==0)
                {
                    for(Freight x : ListBack)
                    {
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
        frameViewFreights.setContentPane(new CL_ViewFreight().panelViewCompanies);
        frameViewFreights.pack();
        frameViewFreights.setVisible(true);
        frameViewFreights.setLocation(480,150);
    }

    public static void giveMeViewFreights(boolean truth)
    {
        frameViewFreights.setVisible(truth);
    }
}
