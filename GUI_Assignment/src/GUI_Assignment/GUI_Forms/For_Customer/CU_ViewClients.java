package GUI_Assignment.GUI_Forms.For_Customer;

import GUI_Assignment.Essential_Classes.Company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by adil on 28/11/16.
 */
public class CU_ViewClients {
    private JTextArea textAreaCurrentCompanies;
    private JButton loadInformationButton;
    private JButton cancelButton;
    private JPanel panelViewCompanies;
    private static JFrame frameViewCompanies;


    public CU_ViewClients() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeViewCompanies(false);
                main_customer_form.giveMeMainCustomerForm(true);
            }
        });
        loadInformationButton.addActionListener(new ActionListener() {
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
                    JOptionPane.showMessageDialog(panelViewCompanies, "System does not have any data.");
                }

                String report="";

                if(invite==0)
                {
                    for(Company x : ListBack)
                    {
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
                    }

                    textAreaCurrentCompanies.setText(report);

                    if(ListBack.size()==0)
                    {
                        textAreaCurrentCompanies.setText("System does not have any data");
                    }
                }
            }
        });
    }

    public static void setViewCompaniesVisible()
    {
        frameViewCompanies = new JFrame("Client's Report");
        frameViewCompanies.setContentPane(new CU_ViewClients().panelViewCompanies);
        frameViewCompanies.pack();
        frameViewCompanies.setVisible(true);
        frameViewCompanies.setLocation(450,150);
    }

    public static void giveMeViewCompanies(boolean truth)
    {
        frameViewCompanies.setVisible(truth);
    }
}
