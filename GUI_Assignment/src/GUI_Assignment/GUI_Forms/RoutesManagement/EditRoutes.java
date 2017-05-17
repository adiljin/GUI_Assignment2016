package GUI_Assignment.GUI_Forms.RoutesManagement;

import GUI_Assignment.Routes_Management.Route;
import GUI_Assignment.fileWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by adil on 27/11/16.
 */
public class EditRoutes {
    private JPanel panelEditRoutes;
    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton radioButtonName;
    private JRadioButton radioButtonAddress;
    private JTextField textField3;
    private JButton searchButton;
    private JButton changeButton;
    private JButton cancelButton;
    private static JFrame frameEditRoutes;

    public EditRoutes() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeEditRoutes(false);
            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<Route> ListBack = null;
                try {
                    FileInputStream fileIn = new FileInputStream("dataRoutes");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    ListBack = (ArrayList<Route>) in.readObject();
                    in.close();
                    fileIn.close();
                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelEditRoutes, "System does not have any data.");
                }

                int ID = 0;

                try
                {
                    ID = Integer.parseInt(textField1.getText());
                }
                catch(NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(panelEditRoutes, "Perhaps, you wrote wrong information into ID field.");
                }

                if(ID == 0)
                {
                    JOptionPane.showMessageDialog(panelEditRoutes, "You must write ID of particular Route.");
                }
                else
                {

                    for(Route x : ListBack)
                    {
                        if(x.getPortNumber()==(ID))
                        {
                            radioButtonName.setEnabled(true);
                            radioButtonAddress.setEnabled(true);

                            textField2.setText(x.getPortName());
                            textField3.setText(String.valueOf(x.getPriceFrom()));
                        }
                    }

                    if(ListBack.size()==0)
                    {
                        JOptionPane.showMessageDialog(panelEditRoutes, "There's no such Route");
                    }

                }
            }
        });
        radioButtonName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonName.isSelected())
                {
                    textField2.setEditable(true);
                    textField3.setEditable(false);
                }
            }
        });
        radioButtonAddress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(radioButtonAddress.isSelected())
                {
                    textField2.setEditable(false);
                    textField3.setEditable(true);
                }
            }
        });
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                firstLoop:
                {
                    int changeChecker = 0;
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
                        JOptionPane.showMessageDialog(panelEditRoutes, "System does not have any data.");
                    }

                    int ID = 0;
                    int exitsID = 0;

                    try
                    {
                        ID = Integer.parseInt(textField1.getText());
                    }
                    catch(Exception ex)
                    {
                        JOptionPane.showMessageDialog(panelEditRoutes, "You must write ID of particular Route.");
                        break firstLoop;
                    }
                    if(ID == 0)
                    {
                        JOptionPane.showMessageDialog(panelEditRoutes, "You must write ID of particular Route.");
                        break firstLoop;
                    }
                    else
                    {
                        if(invite==0)
                        {

                            for(Route x : ListBack)
                            {
                                if(x.getPortNumber()==ID)
                                {
                                    if(textField2.getText().equals("") || textField3.getText().equals(""))
                                    {
                                        JOptionPane.showMessageDialog(panelEditRoutes, "One of the sections is empty, rewrite or cancel operation.");
                                        break firstLoop;
                                    }
                                    else
                                    {
                                        try
                                        {
                                            if(!textField2.getText().equals(x.getPortName()))
                                            {
                                                x.setPortName(textField2.getText());
                                                changeChecker++;
                                                exitsID++;
                                            }
                                            if(!textField3.getText().equals(x.getPriceFrom()))
                                            {
                                                x.setPriceFrom(Integer.parseInt(textField3.getText()));
                                                changeChecker++;
                                                exitsID++;
                                            }
                                        }
                                        catch(Exception ex)
                                        {

                                        }
                                        if(changeChecker==0)
                                        {
                                            JOptionPane.showMessageDialog(panelEditRoutes, "Each section must be filled, price must be more than zero");
                                            break firstLoop;
                                        }
                                    }
                                }
                            }

                            if(exitsID==0)
                            {
                                JOptionPane.showMessageDialog(panelEditRoutes, "There's no such Route");
                            }
                            else
                            {
                                try
                                {
                                    if(Integer.parseInt(textField3.getText())>0 && !textField2.getText().equals(""))
                                    {
                                        fileWorker.fileWriter(ListBack, "dataRoutes");
                                        JOptionPane.showMessageDialog(panelEditRoutes, "Information successfully saved.");

                                        giveMeEditRoutes(false);
                                    }
                                    else
                                    {
                                        JOptionPane.showMessageDialog(panelEditRoutes, "Each section must be filled, price must be more than zero");
                                    }
                                }
                                catch(Exception ex)
                                {

                                }
                            }
                        }
                    }
                }
            }
        });
    }

    public static void setEditRoutesVisible() {
        frameEditRoutes = new JFrame("Edit route");
        frameEditRoutes.setContentPane(new EditRoutes().panelEditRoutes);
        frameEditRoutes.pack();
        frameEditRoutes.setVisible(true);
        frameEditRoutes.setLocation(450, 80);
    }

    public static void giveMeEditRoutes(boolean truth) {
        frameEditRoutes.setVisible(truth);
    }
}
