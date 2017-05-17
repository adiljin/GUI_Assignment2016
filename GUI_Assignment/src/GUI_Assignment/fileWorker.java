package GUI_Assignment;

import GUI_Assignment.Essential_Classes.Company;
import GUI_Assignment.Essential_Classes.Customer;
import GUI_Assignment.Essential_Classes.Freight;
import Ships.Ship;
import Ships.ports_10.Cargo;
import Ships.ports_10.Tugboat;
import Ships.ports_20.Barge;
import Ships.ports_20.Container;
import Ships.ports_20.Tanker;
import Ships.ports_3.Cruise;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by adil on 05/11/16.
 */
public class fileWorker {
    public static void fileWriter(ArrayList list, String outputStream)
    {
        //write into a file
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(outputStream);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
        }catch(IOException i) {
            i.printStackTrace();
        }
    }
    public static ArrayList existFileChecker(ArrayList list, String outputStream)
    {
        //checking about existing files
        try {
            FileInputStream fileIn = new FileInputStream(outputStream);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (ArrayList<Company>) in.readObject();
            in.close();
            fileIn.close();
        }
        catch (EOFException e)
        {
            list = new ArrayList<Company>();
        }
        catch(Exception i) {

        }
        return list;
    }

    public static ArrayList existFileCheckerCustomer(ArrayList list, String outputStream)
    {
        //checking about existing files
        try {
            FileInputStream fileIn = new FileInputStream(outputStream);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (ArrayList<Customer>) in.readObject();
            in.close();
            fileIn.close();
        }
        catch (EOFException e)
        {
            list = new ArrayList<Customer>();
        }
        catch(Exception i) {

        }
        return list;
    }
    public static ArrayList existFileCheckerFreight(ArrayList list, String outputStream)
    {
        //checking about existing files
        try {
            FileInputStream fileIn = new FileInputStream(outputStream);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            list = (ArrayList<Freight>) in.readObject();
            in.close();
            fileIn.close();
        }
        catch (EOFException e)
        {
            list = new ArrayList<Freight>();
        }
        catch(Exception i) {

        }
        return list;
    }

    public static void fileReader(String path)
    {
        int invite=0;
        ArrayList<Company> ListBack = null;
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ListBack = (ArrayList<Company>) in.readObject();
            in.close();
            fileIn.close();
        }catch (Exception e) {
            invite++;
            System.out.println("System does not have any data");
        }
        if(invite==0)
        {
            for(Company x : ListBack)
            {
                System.out.println("ID: " + x.getId());
                System.out.println("Name of organization: " + x.getName());
                System.out.println("Address: " + x.getAddress());
                System.out.println("Phone number: " + x.getNumber());
                System.out.println("E-mail: " + x.getE_mail());
                System.out.println();
                System.out.println("Ships type: " + x.getShip().getClass().getSimpleName());
                System.out.println("Ships ID: " + x.getShip().getShipID());
                System.out.println("Name of the ship: " + x.getShip().getShipName());
                System.out.println("Address of the port: " + x.getShip().getShipAddress());
                System.out.println("Min of weight: " + x.getShip().getWeightMin());
                System.out.println("Max of weight: " + x.getShip().getWeightMax());
                System.out.println("");
                System.out.println("Type of Leasing: " + x.getShip().getTypeLease());
                System.out.println("Years of leasing: " + x.getShip().getYears());
                System.out.println("Price per year(without percent): " + x.getShip().getPricePerYear());
                System.out.println("Total price: " + x.getShip().getPrice());
                System.out.println("-----------------------");
                System.out.println();
            }
            if(ListBack.size()==0)
            {
                System.out.println("System does not have any data");
            }
        }
    }
    public static void fileReaderCustomer(String path)
    {
        int invite=0;
        ArrayList<Customer> ListBack = null;
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ListBack = (ArrayList<Customer>) in.readObject();
            in.close();
            fileIn.close();
        }catch (Exception e) {
            invite++;
            System.out.println("System does not have any data");
        }
        if(invite==0)
        {
            for(Customer x : ListBack)
            {
                System.out.println("ID: " + x.getId());
                System.out.println("Name of organization: " + x.getName());
                System.out.println("Address: " + x.getAddress());
                System.out.println("Phone number: " + x.getNumber());
                System.out.println("E-mail: " + x.getE_mail());
                System.out.println();
            }
            if(ListBack.size()==0)
            {
                System.out.println("System does not have any data");
            }
        }
    }

    public static Customer searchCustomer(String id)
    {
        Customer result = null;
        int invite=0;
        ArrayList<Customer> ListBack = null;
        try {
            FileInputStream fileIn = new FileInputStream("dataCustomer");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ListBack = (ArrayList<Customer>) in.readObject();
            in.close();
            fileIn.close();
        }catch (Exception e) {
            invite++;
            System.out.println("System does not have any data");
        }
        if(invite==0)
        {
            for(Customer x : ListBack)
            {
                if(id.equals(x.getId()))
                {
                    result = x;
                }
            }
            if(ListBack.size()==0)
            {
                System.out.println("System does not have any data");
            }
        }
        return result;
    }

    public static void fileReaderFreight(String path)
    {
        int invite=0;
        ArrayList<Freight> ListBack = null;
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ListBack = (ArrayList<Freight>) in.readObject();
            in.close();
            fileIn.close();
        }catch (Exception e) {
            invite++;
            System.out.println("System does not have any data");
        }
        if(invite==0)
        {
            for(Freight x : ListBack)
            {
                System.out.println("_____________________________");
                System.out.println();
                System.out.println("ID: " + x.getId());
                System.out.println("Type of freight: " + x.getType());
                System.out.println("Weight: " + x.getWeight());
                System.out.println("Price: " + x.getPrice());
                System.out.println("-----------------------------");
                System.out.println("Customer's ID " + x.getCustomer().getId());
                System.out.println("Customer's name " + x.getCustomer().getName());
                System.out.println("-----------------------------");
                System.out.println("Ships's ID: " + x.getShip().getShipID());
                System.out.println("Ships's type: " + x.getShip().getClass().getSimpleName());
                System.out.println("Routes type: " + x.getRouteType() + " ports");
                System.out.println();
            }
            if(ListBack.size()==0)
            {
                System.out.println("System does not have any data");
            }
        }
    }

    public static int fileReaderShips(String type)
    {
        int result = 0;

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
            System.out.println("System does not have any data");
        }
        if(invite==0)
        {
            for(Company x : ListBack)
            {
                if(type.equals("l"))
                {
                    if(x.getShip().getClass().getSimpleName().equals(Barge.class.getSimpleName()) || x.getShip().getClass().getSimpleName().equals(Container.class.getSimpleName()) || x.getShip().getClass().getSimpleName().equals(Tanker.class.getSimpleName()))
                    {
                        System.out.println("Ships type: " + x.getShip().getClass().getSimpleName());
                        System.out.println("Ships ID: " + x.getShip().getShipID());
                        System.out.println("Min of weight: " + x.getShip().getWeightMin());
                        System.out.println("Max of weight: " + x.getShip().getWeightMax());
                        System.out.println("");
                        System.out.println("-----------------------");
                        System.out.println();
                        noData++;
                        result++;
                    }
                }
                else if (type.equals("m"))
                {
                    if(x.getShip().getClass().getSimpleName().equals(Cargo.class.getSimpleName()) || x.getShip().getClass().getSimpleName().equals(Tugboat.class.getSimpleName()))
                    {
                        System.out.println("Ships type: " + x.getShip().getClass().getSimpleName());
                        System.out.println("Ships ID: " + x.getShip().getShipID());
                        System.out.println("Min of weight: " + x.getShip().getWeightMin());
                        System.out.println("Max of weight: " + x.getShip().getWeightMax());
                        System.out.println("");
                        System.out.println("-----------------------");
                        System.out.println();
                        noData++;
                        result++;
                    }
                }
                else if(type.equals("s"))
                {
                    if(x.getShip().getClass().getSimpleName().equals(Cruise.class.getSimpleName()))
                    {
                        System.out.println("Ships type: " + x.getShip().getClass().getSimpleName());
                        System.out.println("Ships ID: " + x.getShip().getShipID());
                        System.out.println("Min of weight: " + x.getShip().getWeightMin());
                        System.out.println("Max of weight: " + x.getShip().getWeightMax());
                        System.out.println("");
                        System.out.println("-----------------------");
                        System.out.println();
                        noData++;
                        result++;
                    }
                }
            }
            if(noData==0)
            {
                System.out.println("No ships are available");
            }
            if(ListBack.size()==0)
            {
                System.out.println("System does not have any data");
            }
        }
        return result;
    }

    public static Ship IDReaderShips(String id)
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
            System.out.println("System does not have any data");
        }
        if(invite==0)
        {
            for(Company x : ListBack)
            {
                if(x.getShip().getShipID().equals(id))
                {
                    result = x.getShip();
                }
                noData++;
            }
            if(noData==0)
            {
                System.out.println("No ships are available");
            }
            if(ListBack.size()==0)
            {
                System.out.println("System does not have any data");
            }
        }
        return result;
    }
}
