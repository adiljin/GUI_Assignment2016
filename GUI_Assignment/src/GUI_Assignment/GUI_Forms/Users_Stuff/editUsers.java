package GUI_Assignment.GUI_Forms.Users_Stuff;

import GUI_Assignment.fileWorker;
import users.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import static GUI_Assignment.GUI_Forms.For_Admin.main_admin_form.giveMeAdmin;
import static GUI_Assignment.GUI_Forms.Users_Stuff.UserManagingMenu.giveMeUserManagement;

/**
 * Created by adil on 24/01/17.
 */
public class editUsers {
    private JTextField textField1;
    private JButton searchButton;
    private JTextField textField2;
    private JRadioButton radioButton1;
    private JTextField textField3;
    private JRadioButton radioButton2;
    private JButton changeButton;
    private JButton cancelButton;
    private JPanel EditUserMainPanel;
    private static JFrame frameEditUser;

    public JPanel getMainPanelEditUsers() {
        return EditUserMainPanel;
    }
    public static void setEditUsersVisible()
    {
        frameEditUser = new JFrame("User's management");
        frameEditUser.setContentPane(new editUsers().getMainPanelEditUsers());
        frameEditUser.pack();
        frameEditUser.setVisible(true);
        frameEditUser.setLocation(520,200);
    }
    public static void giveEditUsers(boolean truth)
    {
        frameEditUser.setVisible(truth);
    }

    public editUsers() {
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> check = new ArrayList<String>();

                int invite=0;
                ArrayList<User> ListBack = null;
                try {
                    FileInputStream fileIn = new FileInputStream("logins");
                    ObjectInputStream in = new ObjectInputStream(fileIn);
                    ListBack = (ArrayList<User>) in.readObject();
                    in.close();
                    fileIn.close();
                }catch (Exception except) {

                }
                int found = 0;
                for(User x : ListBack)
                {
                    if(textField1.getText().equals(x.getLogin()))
                    {
                        found++;
                        textField1.setEditable(false);
                        textField2.setText(x.getLogin());
                        textField3.setText(x.getLogin());
                    }
                }
                if(found==0)
                {
                    JOptionPane.showMessageDialog(EditUserMainPanel, "System could not find such user in the system");
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveEditUsers(false);
                giveMeUserManagement(true);
            }
        });
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField2.setEditable(true);
                textField3.setEditable(false);
            }
        });
        radioButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField2.setEditable(false);
                textField3.setEditable(true);
            }
        });
        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField2.equals("")&&!textField3.equals(""))
                {
                    ArrayList<String> check = new ArrayList<String>();

                    int invite=0;
                    ArrayList<User> ListBack = null;
                    try {
                        FileInputStream fileIn = new FileInputStream("logins");
                        ObjectInputStream in = new ObjectInputStream(fileIn);
                        ListBack = (ArrayList<User>) in.readObject();
                        in.close();
                        fileIn.close();
                    }catch (Exception except) {

                    }
                    int found = 0;

                    String newLogin = textField2.getText().toString();
                    String newPassword = textField3.getText().toString();
                    for(User x : ListBack)
                    {
                        if(x.getLogin().equals(textField1.getText().toString()))
                        {
                            found++;
                            x.setLogin(newLogin);
                            x.setPassword(newPassword);
                        }
                    }
                    if(found>0)
                    {
                        fileWorker.fileWriter(ListBack, "logins");
                        JOptionPane.showMessageDialog(EditUserMainPanel, "User's information changed");
                        giveEditUsers(false);
                        giveMeUserManagement(true);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(EditUserMainPanel, "System could not update information");
                    }


                }
            }
        });
    }
}
