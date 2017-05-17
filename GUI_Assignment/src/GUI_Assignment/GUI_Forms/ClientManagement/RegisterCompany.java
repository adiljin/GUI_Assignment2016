package GUI_Assignment.GUI_Forms.ClientManagement;

import GUI_Assignment.Essential_Classes.Company;
import Ships.Ship;
import Ships.ports_10.Cargo;
import Ships.ports_10.Tugboat;
import Ships.ports_20.Barge;
import Ships.ports_20.Container;
import Ships.ports_20.Tanker;
import Ships.ports_3.Cruise;
import GUI_Assignment.Leasing.Leasing;
import GUI_Assignment.fileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static GUI_Assignment.IDCounter.idCounter;

/**
 * Created by adil on 26/11/16.
 */
public class RegisterCompany {
    private JTextField textFieldNameOfOrg1;
    private JTextField textFieldAddress;
    private JTextField textFieldPhoneNumber;
    private JTextField textFieldE_Mail;
    private JRadioButton contractRadioButton;
    private JRadioButton operatingRadioButton;
    private JRadioButton cruiseRadioButtonCruise;
    private JRadioButton tugboatRadioButtonTugboat;
    private JRadioButton tankerRadioButtonTanker;
    private JRadioButton cargoRadioButtonCargo;
    private JRadioButton bargeRadioButtonBarge;
    private JRadioButton containerRadioButtonContainer;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel panelRegisterCompany;
    private JTextField textFieldYearsOfLeasing;
    private JTextField textFieldNameOfTheShip;
    private JTextField textField3PortAddress;
    private static JFrame frameRegisterCompany;

    public RegisterCompany() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeRegisterCompany(false);
                clientMan.giveMeClientMan(true);
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, address, number, e_mail, typeLeas, shipName, shipAddress;
                int years = 0;
                String id;
                Ship ship = new Ship("0","0","0");
                name = textFieldNameOfOrg1.getText();
                address = textFieldAddress.getText();
                number = textFieldPhoneNumber.getText();
                e_mail = textFieldE_Mail.getText();
                typeLeas = "";
                shipName = textFieldNameOfTheShip.getText();
                shipAddress = textField3PortAddress.getText();
                try
                {
                    years = Integer.parseInt(textFieldYearsOfLeasing.getText());
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(panelRegisterCompany, "You must put number inside (Years of leasing) section");
                }

                registerFunction(name, address, number, e_mail, typeLeas, shipName, shipAddress, years);
            }
        });
    }

    public static void setRegisterCompanyVisible()
    {
        frameRegisterCompany = new JFrame("Client's Registration");
        frameRegisterCompany.setContentPane(new RegisterCompany().panelRegisterCompany);
        frameRegisterCompany.pack();
        frameRegisterCompany.setVisible(true);
        frameRegisterCompany.setLocation(380,200);
    }

    public static void giveMeRegisterCompany(boolean truth)
    {
        frameRegisterCompany.setVisible(truth);
    }

    public JPanel getPanelRegisterCompany() {
        return panelRegisterCompany;
    }

    public void registerFunction(String name, String address, String number, String e_mail, String typeLeas, String shipName, String shipAddress, int years)
    {
        try
        {
            String id;
            Ship ship = new Ship("0","0","0");
            name = textFieldNameOfOrg1.getText();
            address = textFieldAddress.getText();
            number = textFieldPhoneNumber.getText();
            e_mail = textFieldE_Mail.getText();
            typeLeas = "";

            if(name.equals("") || address.equals("") || number.equals("") ||e_mail.equals(""))
            {
                JOptionPane.showMessageDialog(panelRegisterCompany, "Something is not filled, make sure to fill each section");
            }
            else
            {
                if(contractRadioButton.isSelected()) {
                    typeLeas = "contract";
                }else if(operatingRadioButton.isSelected())
                {
                    typeLeas = "operating";
                }

                if(shipName.equals(null) || shipAddress.equals(null) || years == 0)
                {
                    JOptionPane.showMessageDialog(panelRegisterCompany, "Ships details is not filled, make sure to fill each section");
                }
                else
                {
                    id = idCounter();
                    int price = 0;

                    if(cruiseRadioButtonCruise.isSelected())
                    {
                        price = Leasing.getLeasing(typeLeas, years, 1000);
                        Cruise cruise = new Cruise(id,shipName,shipAddress, price, years, typeLeas);
                        ship = cruise;
                    }
                    else if(cargoRadioButtonCargo.isSelected())
                    {
                        price = Leasing.getLeasing(typeLeas, years, 2000);
                        Cargo cargo = new Cargo(id,shipName,shipAddress, price, years, typeLeas);
                        ship = cargo;
                    }
                    else if(tugboatRadioButtonTugboat.isSelected())
                    {
                        price = Leasing.getLeasing(typeLeas, years, 3000);
                        Tugboat tugboat = new Tugboat(id,shipName,shipAddress, price, years, typeLeas);
                        ship = tugboat;
                    }
                    else if(tankerRadioButtonTanker.isSelected())
                    {
                        price = Leasing.getLeasing(typeLeas, years, 4000);
                        Tanker tanker = new Tanker(id,shipName,shipAddress, price, years, typeLeas);
                        ship = tanker;
                    }
                    else if(bargeRadioButtonBarge.isSelected())
                    {
                        price = Leasing.getLeasing(typeLeas, years, 5000);
                        Barge barge = new Barge(id,shipName,shipAddress, price, years, typeLeas);
                        ship = barge;
                    }
                    else if(containerRadioButtonContainer.isSelected())
                    {
                        price = Leasing.getLeasing(typeLeas, years, 6000);
                        Container container = new Container(id,shipName,shipAddress, price, years, typeLeas);
                        ship = container;
                    }

                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog (null, "Your price is " + price + ". Would you like to save data?","Warning",dialogButton);
                    if(dialogResult == JOptionPane.YES_OPTION)
                    {
                        Company company = new Company(id, name, address, number, e_mail, ship);

                        ArrayList<Company> ListBackup = new ArrayList<Company>();

                        //checking about existing files
                        ListBackup = fileWorker.existFileChecker(ListBackup, "dataClient");

                        ListBackup.add(company);

                        //write into a file
                        fileWorker.fileWriter(ListBackup, "dataClient");
                        JOptionPane.showMessageDialog(panelRegisterCompany, "New company is saved");

                        giveMeRegisterCompany(false);
                        clientMan.giveMeClientMan(true);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Error detected " + e);
        }
    }


}
