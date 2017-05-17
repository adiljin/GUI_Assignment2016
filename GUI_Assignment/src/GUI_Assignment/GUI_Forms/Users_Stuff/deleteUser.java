package GUI_Assignment.GUI_Forms.Users_Stuff;

import GUI_Assignment.Essential_Classes.Company;
import GUI_Assignment.fileWorker;
import users.Admin;
import users.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import static GUI_Assignment.GUI_Forms.Users_Stuff.UserManagingMenu.giveMeUserManagement;

/**
 * Created by adil on 24/01/17.
 */
public class deleteUser {
    private JPanel panelDeleteShipping;
    private JTextField textField1;
    private JButton searchButton;
    private JButton deleteButton;
    private JButton cancelButton;
    private JTextField textField2;
    private JTextField textField3;
    private static JFrame frameDeleteUser;

    public JPanel getPanelDeleteShipping() {
        return panelDeleteShipping;
    }

    public static void setDeleteUsersVisible()
    {
        frameDeleteUser = new JFrame("User's management");
        frameDeleteUser.setContentPane(new deleteUser().getPanelDeleteShipping());
        frameDeleteUser.pack();
        frameDeleteUser.setVisible(true);
        frameDeleteUser.setLocation(520,200);
    }
    public static void giveDeleteUsers(boolean truth)
    {
        frameDeleteUser.setVisible(truth);
    }

    public deleteUser() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int invite=0;
                ArrayList<User> ListBack = null;
                try {
                    FileInputStream fileIn = new FileInputStream("logins");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    ListBack = (ArrayList<User>) in.readObject();
                    in.close();
                    fileIn.close();
                }catch (Exception ex) {
                    invite++;
                    JOptionPane.showMessageDialog(panelDeleteShipping, "System does not have any data.");
                }

                int exist = 0;
                if(invite==0)
                {
                    for(User x : ListBack)
                    {
                        if(x.getLogin().equals(textField1.getText()))
                        {
                            exist++;
                            textField1.setEditable(false);
                            textField2.setText("Login: " + x.getLogin());
                            textField3.setText("Pasword: " + x.getPassword());
                        }
                    }
                }
                if(exist==0)
                {
                    JOptionPane.showMessageDialog(panelDeleteShipping, "System does not have such login.");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeUserManagement(true);
                giveDeleteUsers(false);
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int found = 0;
                int invite= 0;
                ArrayList<User> ListBack = null;
                try {
                    FileInputStream fileIn = new FileInputStream("logins");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    ListBack = (ArrayList<User>) in.readObject();
                    in.close();
                    fileIn.close();
                }catch (Exception ex) {
                    invite++;
                    JOptionPane.showMessageDialog(panelDeleteShipping, "System does not have any data.");
                }
                if(!(textField1.getText().equals("")&&textField2.getText().equals("")&&textField3.getText().equals("")))
                {
                    /*for(User x : ListBack)
                    {
                        if(x.getLogin().equals(textField1.getText()))
                        {
                            if(x instanceof Admin)
                            {
                                JOptionPane.showMessageDialog(panelDeleteShipping, "You cannot delete Administrator of this system");
                            }
                            else
                            {
                                try
                                {
                                    ListBack.remove(x);
                                    found++;
                                }
                                catch (Exception ec)
                                {
                                    System.out.println(ec);
                                }
                            }
                        }
                    }*/

                    Iterator<User> iter = ListBack.iterator();

                    while (iter.hasNext()) {
                        User value = iter.next();
                        if(value.getLogin().equals(textField1.getText()))
                        {
                            if(value instanceof Admin)
                            {
                                JOptionPane.showMessageDialog(panelDeleteShipping, "You cannot delete Administrator of this system");
                            }
                            else
                            {
                                try
                                {
                                    iter.remove();
                                    found++;
                                }
                                catch (Exception ec)
                                {
                                    System.out.println(ec);
                                }
                            }
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(panelDeleteShipping, "Find appropriate login, only then delete it");
                }
                if(found>0)
                {
                    fileWorker.fileWriter(ListBack, "logins");
                    JOptionPane.showMessageDialog(panelDeleteShipping, "User's information deleted");
                    giveDeleteUsers(false);
                    giveMeUserManagement(true);
                }
            }
        });
    }
}
