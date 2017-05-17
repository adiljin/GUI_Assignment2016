package GUI_Assignment;

import GUI_Assignment.convenient_interfaces.buff_reader;

import java.io.*;

/**
 * Created by adil on 03/11/16.
 */
public class IDCounter implements buff_reader
{
    public static String idCounter()
    {
        int invite=0;
        int idCountFile=0;
        try {
            FileInputStream fileIn = new FileInputStream("dataIDCheckClient");
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
                    new FileOutputStream("dataIDCheckClient");
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
    public static void idCounterChanger() throws IOException
    {
        int idCountFile = Integer.parseInt(reader.readLine());

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("dataIDCheckClient");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.write(idCountFile);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static String idCounterCustomer()
    {
        int invite=0;
        int idCountFile=0;
        try {
            FileInputStream fileIn = new FileInputStream("dataIDCheckCustomer");
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
                    new FileOutputStream("dataIDCheckCustomer");
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

    public static void idCounterCustChanger() throws IOException
    {
        int idCountFile = Integer.parseInt(reader.readLine());

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("dataIDCheckCustomer");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.write(idCountFile);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void idCounterFreightChanger() throws IOException
    {
        int idCountFile = Integer.parseInt(reader.readLine());

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

    public static int RoutesIDCounter()
    {
        int invite=0;
        int idCountFile=0;
        try {
            FileInputStream fileIn = new FileInputStream("dataIDCheckRoutes");
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
                    new FileOutputStream("dataIDCheckRoutes");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.write(idCountFile);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
        return idCountFile;
    }
}
