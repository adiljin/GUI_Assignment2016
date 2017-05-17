package GUI_Assignment.GUI_Forms.Users_Stuff;

import users.Admin;
import users.Client;
import users.Cust;
import users.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static GUI_Assignment.GUI_Forms.Users_Stuff.UserManagingMenu.giveMeUserManagement;

/**
 * Created by adil on 24/01/17.
 */
public class UsersView {
    private JPanel panelViewCompanies;
    private JButton loadInformationButton;
    private JButton cancelButton;
    private JTextArea textAreaCurrentCompanies;
    private static JFrame frameUsersView;

    public UsersView() {
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeUsersView(false);
                giveMeUserManagement(true);
            }
        });
        loadInformationButton.addActionListener(new ActionListener() {
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
                    JOptionPane.showMessageDialog(panelViewCompanies, "System does not have any data.");
                }

                String report="";

                if(invite==0)
                {
                    for(User x : ListBack)
                    {
                        String userType = null;
                        if(x instanceof Admin)
                        {
                            userType = "Administrator";
                        }
                        else if(x instanceof Cust)
                        {
                            userType = "Customer";
                        }
                        else if(x instanceof Client)
                        {
                            userType = "Client";
                        }
                        report = report + "Login: " + x.getLogin() + "\n";
                        report = report + "Password: " + x.getPassword()+ "\n";
                        report = report + "User's type: " + userType + "\n";
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

    public JPanel getPanelViewCompanies() {
        return panelViewCompanies;
    }

    public static void setUsersViewVisible()
    {
        frameUsersView = new JFrame("User's management");
        frameUsersView.setContentPane(new UsersView().getPanelViewCompanies());
        frameUsersView.pack();
        frameUsersView.setVisible(true);
        frameUsersView.setLocation(520,200);
    }
    public static void giveMeUsersView(boolean truth)
    {
        frameUsersView.setVisible(truth);
    }


}
