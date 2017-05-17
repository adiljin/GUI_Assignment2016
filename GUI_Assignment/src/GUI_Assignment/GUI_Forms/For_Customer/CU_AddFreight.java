package GUI_Assignment.GUI_Forms.For_Customer;

import GUI_Assignment.Essential_Classes.Company;
import GUI_Assignment.Essential_Classes.Customer;
import GUI_Assignment.Essential_Classes.Freight;
import GUI_Assignment.Essential_Classes.FreightType.LTL;
import GUI_Assignment.Essential_Classes.FreightType.TL;
import Ships.Ship;
import Ships.ports_10.Cargo;
import Ships.ports_10.Tugboat;
import Ships.ports_20.Barge;
import Ships.ports_20.Container;
import Ships.ports_20.Tanker;
import Ships.ports_3.Cruise;
import GUI_Assignment.Routes_Management.Route;
import GUI_Assignment.fileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

import static GUI_Assignment.GUI_Forms.For_Customer.CU_FreightMan.giveMeFreightsMan;
import static GUI_Assignment.GUI_Forms.RoutesManagement.routesManagement.routeDownloader;
import static GUI_Assignment.Routes_Management.Routes.priceGenerator;
import static GUI_Assignment.Routes_Management.Routes.routeType;
import static GUI_Assignment.fileWorker.searchCustomer;

/**
 * Created by adil on 27/11/16.
 */
public class CU_AddFreight {
    private JPanel panelAddFreight;
    private JTextField textFieldCustomerID;
    private JButton searchButton;
    private JButton saveButton;
    private JButton cancelButton;
    private JRadioButton longTripRadioButton;
    private JRadioButton mediumTripRadioButton;
    private JRadioButton shortTripRadioButton;
    private JTextArea textArea1;
    private JTextField textFieldPortFrom;
    private JTextField textFieldPortTo;
    private JTextField textFieldWeightOfFreight;
    private JLabel PortFrom;
    private JLabel PortTo;
    private JLabel WeightOfFreight;
    private JRadioButton wholeRouteRadioButton;
    private JRadioButton customRouteRadioButton;
    private JLabel labelChooseRoute;
    private JRadioButton a1RadioButton;
    private JRadioButton a2RadioButton;
    private JRadioButton a3RadioButton;
    private JLabel typeOfFreight;
    private JLabel LabelShipsID;
    private JTextField textField2;
    private JButton chooseShipButton;
    private JTextField textFieldPrice;
    private JLabel labelPrice;
    private JComboBox comboBoxTypeOfFreight;
    private static JFrame frameAddFreight;

    private String idFreight;
    private Customer real_Customer = null;
    private String standardReport = "";
    private Ship ship;
    private String typeFreight;
    private int weightFreight;
    private int price;
    private int route;
    private String routeType;
    private Route chosenRoute = null;
    private int from = 0;
    private int to = 0;

    private String kindOfTrip;
    private int kindOfTripQuant;

    private LTL LTLF;
    private TL TLF;

    Freight freight = new Freight(null, null, null, null, null, 0, 0, null, null);

    public CU_AddFreight() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeAddFreight(false);
                giveMeFreightsMan(true);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Route chosenRoute = null;

                String CustID;


                firstLoop:
                {
                    CustID = (textFieldCustomerID.getText().toUpperCase());
                    real_Customer = searchCustomer(CustID);

                    if (real_Customer == null) {
                        JOptionPane.showMessageDialog(panelAddFreight, "System does not find ID, try again");
                        break firstLoop;
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(panelAddFreight, "Customer - " + real_Customer.getId() + "; " + real_Customer.getName() + " found!");
                        searchButton.setEnabled(false);

                        textFieldCustomerID.setEnabled(false);

                        textFieldCustomerID.setText("Customer - " + real_Customer.getId() + "; " + real_Customer.getName());

                        labelChooseRoute.setEnabled(true);
                        wholeRouteRadioButton.setEnabled(true);
                        customRouteRadioButton.setEnabled(true);

                    /*
                    ChooseYourTrip.setEnabled(true);
                    longTripRadioButton.setEnabled(true);
                    mediumTripRadioButton.setEnabled(true);
                    shortTripRadioButton.setEnabled(true);
                    findShipButton.setEnabled(true);
                    */

                    /*
                    textArea1.setEnabled(true);
                    PortFrom.setEnabled(true);
                    PortTo.setEnabled(true);
                    WeightOfFreight.setEnabled(true);
                    textFieldPortFrom.setEnabled(true);
                    textFieldPortTo.setEnabled(true);
                    textFieldWeightOfFreight.setEnabled(true);
                    saveButton.setEnabled(true);
                    */
                    }
                }
            }
        });
        wholeRouteRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                wholeRouteRadioButton.setEnabled(false);
                customRouteRadioButton.setEnabled(false);

                String report = "";
                if(wholeRouteRadioButton.isSelected())
                {
                    route = 1;
                    textArea1.setEnabled(true);
                    a1RadioButton.setEnabled(true);
                    a2RadioButton.setEnabled(true);
                    a3RadioButton.setEnabled(true);
                    report = report + "Choose appropriate route:" + "\n";
                    report = report + "1 - New York to New York ( Long Distance with 20 ports in between the source and destination)" + "\n";
                    report = report + "___________________________________________________________________________________________" + "\n";
                    report = report + "2 - Troyania to Ossinina  (Medium Distance with 10 ports in between the source and destination)" + "\n";
                    report = report + "___________________________________________________________________________________________" + "\n";
                    report = report + "3 - WestPoint to Saugerties  (Short Distance with 3 ports between the source and destination)" + "\n";

                    standardReport = report;

                    textArea1.setText(report);
                }
            }
        });
        a1RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(a1RadioButton.isSelected())
                {
                    PortFrom.setEnabled(true);
                    PortTo.setEnabled(true);
                    LabelShipsID.setEnabled(true);
                    textField2.setEnabled(true);
                    chooseShipButton.setEnabled(true);
                    textFieldPortFrom.setEnabled(true);
                    textFieldPortFrom.setText("New York");
                    textFieldPortTo.setEnabled(true);
                    textFieldPortTo.setText("New York");
                    comboBoxTypeOfFreight.setEnabled(true);

                    textArea1.setText(fileReaderShips("l"));
                    routeType = routeType(route, 20);
                }
            }
        });
        a2RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(a2RadioButton.isSelected())
                {
                    PortFrom.setEnabled(true);
                    PortTo.setEnabled(true);
                    LabelShipsID.setEnabled(true);
                    textField2.setEnabled(true);
                    chooseShipButton.setEnabled(true);
                    textFieldPortFrom.setEnabled(true);
                    textFieldPortFrom.setText("Troyania");
                    textFieldPortTo.setEnabled(true);
                    textFieldPortTo.setText("Ossinina");
                    comboBoxTypeOfFreight.setEnabled(true);

                    textArea1.setText(fileReaderShips("m"));
                    routeType = routeType(route, 10);
                }
            }
        });
        a3RadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(a3RadioButton.isSelected())
                {
                    PortFrom.setEnabled(true);
                    PortTo.setEnabled(true);
                    LabelShipsID.setEnabled(true);
                    textField2.setEnabled(true);
                    chooseShipButton.setEnabled(true);
                    textFieldPortFrom.setEnabled(true);
                    textFieldPortFrom.setText("WestPoint");
                    textFieldPortTo.setEnabled(true);
                    textFieldPortTo.setText("Saugerties");
                    comboBoxTypeOfFreight.setEnabled(true);

                    textArea1.setText(fileReaderShips("s"));
                    routeType = routeType(route, 3);
                }
            }
        });
        chooseShipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int showPrice = 0;

                firstLoop:
                {
                    if (!textField2.getText().equals("")) {
                        try {
                            String idShip = textField2.getText().toUpperCase();

                            typeOfFreight.setEnabled(true);
                            WeightOfFreight.setEnabled(true);
                            textFieldWeightOfFreight.setEnabled(true);
                            saveButton.setEnabled(true);

                            if (a1RadioButton.isSelected()) {
                                kindOfTrip = "l";
                                ship = IDReaderShips(idShip);
                                price = priceGenerator(1, 20);
                                if(comboBoxTypeOfFreight.getSelectedItem().toString().equals(LTLF.getName()))
                                {
                                    price = (int)(price + (ship.getPricePerYear()*LTLF.getPercent()));
                                }
                                else if(comboBoxTypeOfFreight.getSelectedItem().toString().equals(TLF.getName()))
                                {
                                    price = (int)(price + (ship.getPricePerYear()*TLF.getPercent()));
                                }
                                try {
                                    chosenRoute = getPorts(1, 20);
                                } catch (IOException e1) {

                                }
                                showPrice++;
                            } else if (a2RadioButton.isSelected()) {
                                kindOfTrip = "m";
                                ship = IDReaderShips(idShip);
                                try {
                                    chosenRoute = getPorts(4, 14);
                                } catch (IOException e1) {

                                }
                                price = priceGenerator(4, 14);
                                if(comboBoxTypeOfFreight.getSelectedItem().toString().equals(LTLF.getName()))
                                {
                                    price = (int) (price + (ship.getPricePerYear()*LTLF.getPercent()));
                                }
                                else if(comboBoxTypeOfFreight.getSelectedItem().toString().equals(TLF.getName()))
                                {
                                    price = (int) (price + (ship.getPricePerYear()*TLF.getPercent()));
                                }
                                showPrice++;
                            } else if (a3RadioButton.isSelected()) {
                                kindOfTrip = "s";
                                ship = IDReaderShips(idShip);
                                try {
                                    chosenRoute = getPorts(16, 19);
                                } catch (IOException e1) {

                                }
                                price = priceGenerator(16, 19);
                                if(comboBoxTypeOfFreight.getSelectedItem().toString().equals(LTLF.getName()))
                                {
                                    price = (int) (price + (ship.getPricePerYear()*LTLF.getPercent()));
                                }
                                else if(comboBoxTypeOfFreight.getSelectedItem().toString().equals(TLF.getName()))
                                {
                                    price = (int) (price + (ship.getPricePerYear()*TLF.getPercent()));
                                }
                                showPrice++;
                            }

                            if (longTripRadioButton.isSelected()) {
                                try {
                                    from = Integer.parseInt(textFieldPortFrom.getText());
                                    to = Integer.parseInt(textFieldPortTo.getText());

                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(panelAddFreight, "Ports from and to must be numerical");
                                }
                                try {
                                    chosenRoute = getPorts(from, to);
                                    if(chosenRoute != null)
                                    {
                                        if (chosenRoute.getQuantity() <= 20) {
                                            ship = IDReaderShips(idShip);
                                            price = priceGenerator(chosenRoute.getPort1(), chosenRoute.getPort2());
                                            if(comboBoxTypeOfFreight.getSelectedItem().toString().equals(LTLF.getName()))
                                            {
                                                price = (int) (price + (ship.getPricePerYear()*LTLF.getPercent()));
                                            }
                                            else if(comboBoxTypeOfFreight.getSelectedItem().toString().equals(TLF.getName()))
                                            {
                                                price = (int) (price + (ship.getPricePerYear()*TLF.getPercent()));
                                            }
                                            if (price == 0) {
                                                break firstLoop;
                                            }
                                            else
                                            {
                                                showPrice++;
                                            }
                                        } else if (chosenRoute.getQuantity() > 20) {
                                            JOptionPane.showMessageDialog(panelAddFreight, "You chose more than 20 ports, in long trip distance, you can choose 20 or less ports.");
                                        }
                                    }
                                } catch (Exception ex) {
                                    System.out.println(ex);
                                }

                            } else if (mediumTripRadioButton.isSelected()) {
                                try {
                                    from = Integer.parseInt(textFieldPortFrom.getText());
                                    to = Integer.parseInt(textFieldPortTo.getText());

                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(panelAddFreight, "Ports from and to must be numerical");
                                }
                                try {
                                    chosenRoute = getPorts(from, to);
                                    if(chosenRoute != null)
                                    {
                                        if (chosenRoute.getQuantity() <= 10) {
                                            ship = IDReaderShips(idShip);
                                            price = priceGenerator(chosenRoute.getPort1(), chosenRoute.getPort2());
                                            if(comboBoxTypeOfFreight.getSelectedItem().toString().equals(LTLF.getName()))
                                            {
                                                price = (int) (price + (ship.getPricePerYear()*LTLF.getPercent()));
                                            }
                                            else if(comboBoxTypeOfFreight.getSelectedItem().toString().equals(TLF.getName()))
                                            {
                                                price = (int) (price + (ship.getPricePerYear()*TLF.getPercent()));
                                            }
                                            if (price == 0) {
                                                break firstLoop;
                                            }
                                            else
                                            {
                                                showPrice++;
                                            }
                                        } else if (chosenRoute.getQuantity() > 10) {
                                            JOptionPane.showMessageDialog(panelAddFreight, "You chose more than 10 ports, in medium trip distance, you can choose 10 or less ports.");
                                        }
                                    }
                                } catch (Exception ex) {

                                }
                            } else if (shortTripRadioButton.isSelected()) {
                                try {
                                    from = Integer.parseInt(textFieldPortFrom.getText());
                                    to = Integer.parseInt(textFieldPortTo.getText());

                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(panelAddFreight, "Ports from and to must be numerical");
                                }
                                try {
                                    chosenRoute = getPorts(from, to);
                                    if(chosenRoute != null)
                                    {
                                        if (chosenRoute.getQuantity() <= 3) {
                                            ship = IDReaderShips(idShip);
                                            price = priceGenerator(chosenRoute.getPort1(), chosenRoute.getPort2());
                                            if(comboBoxTypeOfFreight.getSelectedItem().toString().equals(LTLF.getName()))
                                            {
                                                price = (int) (price + (ship.getPricePerYear()*LTLF.getPercent()));
                                            }
                                            else if(comboBoxTypeOfFreight.getSelectedItem().toString().equals(TLF.getName()))
                                            {
                                                price = (int) (price + (ship.getPricePerYear()*TLF.getPercent()));
                                            }
                                            if (price == 0) {
                                                break firstLoop;
                                            }
                                            else
                                            {
                                                showPrice++;
                                            }
                                        } else if (chosenRoute.getQuantity() > 3) {
                                            JOptionPane.showMessageDialog(panelAddFreight, "You chose more than 3 ports, in long trip distance, you can choose 3 or less ports.");
                                        }
                                    }
                                } catch (Exception ex) {

                                }
                            }

                            if (showPrice > 0) {
                                longTripRadioButton.setEnabled(false);
                                mediumTripRadioButton.setEnabled(false);
                                shortTripRadioButton.setEnabled(false);
                                chooseShipButton.setEnabled(false);
                                PortFrom.setEnabled(false);
                                textFieldPortFrom.setEnabled(false);
                                PortTo.setEnabled(false);
                                textFieldPortTo.setEnabled(false);
                                typeOfFreight.setEnabled(false);
                                comboBoxTypeOfFreight.setEnabled(false);
                                labelPrice.setEnabled(true);
                                textFieldPrice.setEnabled(true);
                                textFieldPrice.setText(String.valueOf(price));
                                JOptionPane.showMessageDialog(panelAddFreight, "Ships  - " + ship.getShipID() + " found!");
                            }
                        } catch (NullPointerException ex) {
                            JOptionPane.showMessageDialog(panelAddFreight, "There's no such ID, or you made a mistake!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(panelAddFreight, "Type ID of the ship into a field");
                    }
                }

            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                firstLoop:
                {
                    if(wholeRouteRadioButton.isSelected())
                    {
                        if(!textFieldWeightOfFreight.getText().equals(""))
                        {
                            try
                            {
                                typeFreight = comboBoxTypeOfFreight.getSelectedItem().toString();
                                weightFreight = Integer.parseInt(textFieldWeightOfFreight.getText());
                            }
                            catch(Exception ex)
                            {
                                JOptionPane.showMessageDialog(panelAddFreight, "Write type and weight(numerical) of the freight into a fields");
                                break firstLoop;
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(panelAddFreight, "Write type and weight(numerical) of the freight into a fields");
                            break firstLoop;
                        }

                        if(weightFreight>ship.getWeightMax())
                        {
                            JOptionPane.showMessageDialog(panelAddFreight, "Weight of your freight is too heavy, rewrite weight!");
                        }
                        else if(comboBoxTypeOfFreight.getSelectedItem().toString().equals("LTL") && weightFreight>7000)
                        {
                            JOptionPane.showMessageDialog(panelAddFreight, "Your weight of freight can not be more than 7000 while your type of freight is LTL. Change weight or cancel operation");
                        }
                        else if(weightFreight<=ship.getWeightMax() && !(comboBoxTypeOfFreight.getSelectedItem().toString().equals("LTL") && weightFreight>7000))
                        {
                            int dialogButton = JOptionPane.YES_NO_OPTION;
                            int dialogResult = JOptionPane.showConfirmDialog (null, "Your price is " + price + ". Would you like to save data?","Warning",dialogButton);
                            if(dialogResult == JOptionPane.YES_OPTION)
                            {
                                idFreight = idCounterFreight();
                                freight = new Freight(real_Customer, idFreight, typeFreight, String.valueOf(weightFreight), ship, price, route, routeType, chosenRoute);
                                ArrayList<Freight> ListBackup = new ArrayList<Freight>();
                                ListBackup = fileWorker.existFileCheckerFreight(ListBackup, "dataFreight");
                                ListBackup.add(freight);
                                //write into a file
                                fileWorker.fileWriter(ListBackup, "dataFreight");
                                JOptionPane.showMessageDialog(panelAddFreight, "Information successfully saved!");
                                giveMeAddFreight(false);
                                giveMeFreightsMan(true);
                            }
                        }
                    }
                    else if(customRouteRadioButton.isSelected())
                    {
                        if(!textFieldWeightOfFreight.getText().equals(""))
                        {
                            try
                            {
                                typeFreight = comboBoxTypeOfFreight.getSelectedItem().toString();
                                weightFreight = Integer.parseInt(textFieldWeightOfFreight.getText());
                            }
                            catch(Exception ex)
                            {
                                JOptionPane.showMessageDialog(panelAddFreight, "Write type and weight(numerical) of the freight into a fields");
                                break firstLoop;
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(panelAddFreight, "Write type and weight(numerical) of the freight into a fields");
                            break firstLoop;
                        }

                        if(weightFreight>ship.getWeightMax())
                        {
                            JOptionPane.showMessageDialog(panelAddFreight, "Weight of your freight is too heavy, rewrite weight!");
                        }
                        else if(comboBoxTypeOfFreight.getSelectedItem().toString().equals("LTL") && weightFreight>7000)
                        {
                            JOptionPane.showMessageDialog(panelAddFreight, "Your weight of freight can not be more than 7000 while your type of freight is LTL. Change weight or cancel operation");
                        }
                        else if(weightFreight<=ship.getWeightMax() && !(comboBoxTypeOfFreight.getSelectedItem().toString().equals("LTL") && weightFreight>7000))
                        {
                            int dialogButton = JOptionPane.YES_NO_OPTION;
                            int dialogResult = JOptionPane.showConfirmDialog (null, "Your price is " + price + ". Would you like to save data?","Warning",dialogButton);
                            if(dialogResult == JOptionPane.YES_OPTION)
                            {
                                idFreight = idCounterFreight();
                                freight = new Freight(real_Customer, idFreight, typeFreight, String.valueOf(weightFreight), ship, price, route, routeType, chosenRoute);
                                ArrayList<Freight> ListBackup = new ArrayList<Freight>();
                                ListBackup = fileWorker.existFileCheckerFreight(ListBackup, "dataFreight");
                                ListBackup.add(freight);
                                //write into a file
                                fileWorker.fileWriter(ListBackup, "dataFreight");
                                JOptionPane.showMessageDialog(panelAddFreight, "Information successfully saved!");
                                giveMeAddFreight(false);
                                giveMeFreightsMan(true);
                            }
                        }
                    }
                }
            }
        });
        customRouteRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setEnabled(true);
                uploadTable();
                standardReport = textArea1.getText();

                wholeRouteRadioButton.setEnabled(false);
                customRouteRadioButton.setEnabled(false);

                longTripRadioButton.setEnabled(true);
                mediumTripRadioButton.setEnabled(true);
                shortTripRadioButton.setEnabled(true);
            }
        });
        longTripRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kindOfTrip = "l";
                textArea1.setText(fileReaderShips(kindOfTrip));
                kindOfTripQuant = 20;
                route = 2;
                routeType = routeType(route, kindOfTripQuant);
                LabelShipsID.setEnabled(true);
                textField2.setEnabled(true);
                chooseShipButton.setEnabled(true);
                PortFrom.setEnabled(true);
                PortTo.setEnabled(true);
                textFieldPortFrom.setEnabled(true);
                textFieldPortTo.setEnabled(true);
                textFieldPortFrom.setEditable(true);
                textFieldPortTo.setEditable(true);
                comboBoxTypeOfFreight.setEnabled(true);
            }
        });
        mediumTripRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kindOfTrip = "m";
                textArea1.setText(fileReaderShips(kindOfTrip));
                kindOfTripQuant = 10;
                route = 2;
                routeType = routeType(route, kindOfTripQuant);
                LabelShipsID.setEnabled(true);
                textField2.setEnabled(true);
                chooseShipButton.setEnabled(true);
                PortFrom.setEnabled(true);
                PortTo.setEnabled(true);
                textFieldPortFrom.setEnabled(true);
                textFieldPortTo.setEnabled(true);
                textFieldPortFrom.setEditable(true);
                textFieldPortTo.setEditable(true);
                comboBoxTypeOfFreight.setEnabled(true);
            }
        });
        shortTripRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kindOfTrip = "s";
                textArea1.setText(fileReaderShips(kindOfTrip));
                kindOfTripQuant = 3;
                route = 2;
                routeType = routeType(route, kindOfTripQuant);
                LabelShipsID.setEnabled(true);
                textField2.setEnabled(true);
                chooseShipButton.setEnabled(true);
                PortFrom.setEnabled(true);
                PortTo.setEnabled(true);
                textFieldPortFrom.setEnabled(true);
                textFieldPortTo.setEnabled(true);
                textFieldPortFrom.setEditable(true);
                textFieldPortTo.setEditable(true);
                comboBoxTypeOfFreight.setEnabled(true);
            }
        });
    }

    public static void setAddFreightVisible()
    {
        frameAddFreight = new JFrame("Freight Management");
        frameAddFreight.setContentPane(new CU_AddFreight().panelAddFreight);
        frameAddFreight.pack();
        frameAddFreight.setVisible(true);
        frameAddFreight.setLocation(420,200);
    }

    public static void giveMeAddFreight(boolean truth)
    {
        frameAddFreight.setVisible(truth);
    }

    public String fileReaderShips(String type)
    {
        String result = "";

        String saver = "";

        int noData=0;
        int invite=0;
        ArrayList<Company> ListBack = null;
        String path = "dataClient";
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ListBack = (ArrayList<Company>) in.readObject();
            in.close();
            fileIn.close();
        }catch (Exception e) {
            invite++;
            JOptionPane.showMessageDialog(panelAddFreight, "System does not have any data.");
        }
        if(invite==0)
        {
            saver = standardReport + "\n";
            for(Company x : ListBack)
            {
                if(type.equals("l"))
                {
                    if(x.getShip().getClass().getSimpleName().equals(Barge.class.getSimpleName()) || x.getShip().getClass().getSimpleName().equals(Container.class.getSimpleName()) || x.getShip().getClass().getSimpleName().equals(Tanker.class.getSimpleName()))
                    {
                        saver = saver + "Ships type: " + x.getShip().getClass().getSimpleName() + "\n";
                        saver = saver + "Ships ID: " + x.getShip().getShipID() + "\n";
                        saver = saver + "Ships name: " + x.getName() + "\n";
                        saver = saver + "Min of weight: " + x.getShip().getWeightMin() + "\n";
                        saver = saver + "Max of weight: " + x.getShip().getWeightMax() + "\n";
                        saver = saver + "-----------------------" + "\n";
                        noData++;
                    }
                }
                else if (type.equals("m"))
                {
                    if(x.getShip().getClass().getSimpleName().equals(Cargo.class.getSimpleName()) || x.getShip().getClass().getSimpleName().equals(Tugboat.class.getSimpleName()))
                    {
                        saver = saver + "Ships type: " + x.getShip().getClass().getSimpleName() + "\n";
                        saver = saver + "Ships ID: " + x.getShip().getShipID() + "\n";
                        saver = saver + "Ships name: " + x.getName() + "\n";
                        saver = saver + "Min of weight: " + x.getShip().getWeightMin() + "\n";
                        saver = saver + "Max of weight: " + x.getShip().getWeightMax() + "\n";
                        saver = saver + "-----------------------" + "\n";
                        noData++;
                    }
                }
                else if(type.equals("s"))
                {
                    if(x.getShip().getClass().getSimpleName().equals(Cruise.class.getSimpleName()))
                    {
                        saver = saver + "Ships type: " + x.getShip().getClass().getSimpleName() + "\n";
                        saver = saver + "Ships ID: " + x.getShip().getShipID() + "\n";
                        saver = saver + "Ships name: " + x.getName() + "\n";
                        saver = saver + "Min of weight: " + x.getShip().getWeightMin() + "\n";
                        saver = saver + "Max of weight: " + x.getShip().getWeightMax() + "\n";
                        saver = saver + "-----------------------" + "\n";
                        noData++;
                    }
                }
            }
            if(noData==0)
            {
                saver = standardReport + "\n" + "No ships are available!";
            }
            if(ListBack.size()==0)
            {
                saver = saver + textArea1.getText() + "\n" + "\n" + "System does not have any data!";
                JOptionPane.showMessageDialog(panelAddFreight, "System does not have any data");
            }
        }
        result = saver;
        return result;
    }

    public Ship IDReaderShips(String id)
    {
        Ship result = null;
        int noData=0;
        int invite=0;
        ArrayList<Company> ListBack = null;
        String path = "dataClient";
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ListBack = (ArrayList<Company>) in.readObject();
            in.close();
            fileIn.close();
        }catch (Exception e) {
            invite++;
            JOptionPane.showMessageDialog(panelAddFreight, "System does not have any data");
        }
        if(invite==0)
        {
            for(Company x : ListBack)
            {
                if(x.getShip().getShipID().equals(id))
                {
                    result = x.getShip();
                    a1RadioButton.setEnabled(false);
                    a2RadioButton.setEnabled(false);
                    a3RadioButton.setEnabled(false);
                    if(a1RadioButton.isSelected()||a2RadioButton.isSelected()||a3RadioButton.isSelected())
                    {
                        //chooseShipButton.setEnabled(false);
                        LabelShipsID.setEnabled(false);
                        textField2.setEnabled(false);
                    }
                    if(longTripRadioButton.isSelected()||mediumTripRadioButton.isSelected()||shortTripRadioButton.isSelected())
                    {
                        //chooseShipButton.setEnabled(false);
                        LabelShipsID.setEnabled(false);
                        textField2.setEnabled(false);
                    }
                }
                noData++;
            }
            if(noData==0)
            {
                JOptionPane.showMessageDialog(panelAddFreight, "No ships are available");
            }
            if(ListBack.size()==0)
            {
                JOptionPane.showMessageDialog(panelAddFreight, "System does not have any data");
            }
        }
        return result;
    }

    public static String idCounterFreight()
    {
        int invite=0;
        int idCountFile=0;
        try {
            FileInputStream fileIn = new FileInputStream("dataIDCheckFreight");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            idCountFile = in.read();
            in.close();
            fileIn.close();
        }catch (Exception e) {
            idCountFile = 1;
            invite++;
        }
        if(invite==0)
        {
            idCountFile++;
        }
        try {
            FileOutputStream fileOut =
                    new FileOutputStream("dataIDCheckFreight");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.write(idCountFile);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
        String ID="ID"+ idCountFile;
        return ID;
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
                JOptionPane.showMessageDialog(panelAddFreight, "System does not have any data, yet.");
                routeDownloader();
            }
            if (invite == 0) {
                for (Route x : ListBack) {
                    report = String.format(report) + String.format("%-10s\t",x.getPortNumber()+")") + String.format("%-10s\t","Port => " + x.getPortName()) + "Price => " + x.getPriceFrom() + "\n";
                }

                if (ListBack.size() == 0) {
                    JOptionPane.showMessageDialog(panelAddFreight, "System does not have any routes.");
                    break firstLoop;
                }



            }
            textArea1.setText(report);
        }
    }

    public Route getPorts(int port1, int port2) throws IOException
    {
        Route route = null;
        firstLoop:
        {
            ArrayList<Route> ListBack = null;
            try {
                FileInputStream fileIn = new FileInputStream("dataRoutes");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                ListBack = (ArrayList<Route>) in.readObject();
                in.close();
                fileIn.close();
            }catch (Exception e) {
                JOptionPane.showMessageDialog(panelAddFreight, "System did not have any data");
            }

            try
            {
                if((port1<=0 || port1>ListBack.size()) || (port2<=0 || port2>ListBack.size()))
                {
                    JOptionPane.showMessageDialog(panelAddFreight, "You made a mistake try again");
                }
                if(port1>port2)
                {
                    JOptionPane.showMessageDialog(panelAddFreight, "You can not go by this route, choose from first until last one");
                }
                int quantity = port2 - port1;
                route = new Route(port1, port2, quantity);
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(panelAddFreight, "You made a mistake try again");
            }

        }
        return route;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        LTLF = new LTL("LTL", 0.1);
        TLF = new TL("TL", 0.4);

        String dbData[] = {LTLF.getName(), TLF.getName()};

        comboBoxTypeOfFreight = new JComboBox(dbData);
    }
}
