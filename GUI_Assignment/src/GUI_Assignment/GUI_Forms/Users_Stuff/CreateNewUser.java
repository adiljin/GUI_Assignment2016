package GUI_Assignment.GUI_Forms.Users_Stuff;

import GUI_Assignment.fileWorker;
import users.Client;
import users.Cust;
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
public class CreateNewUser {
    private JTextField textField1;
    private JTextField textField2;
    private JButton saveButton;
    private JButton cancelButton;
    private JPanel panelMain;
    private JRadioButton clientRadioButton;
    private JRadioButton customerRadioButton;
    private static JFrame frameNewLogin;

    public JPanel getPanelMain() {
        return panelMain;
    }

    public static void setNewLoginVisible()
    {
        frameNewLogin = new JFrame("Create new user");
        frameNewLogin.setContentPane(new CreateNewUser().getPanelMain());
        frameNewLogin.pack();
        frameNewLogin.setVisible(true);
        frameNewLogin.setLocation(520,200);
    }
    public static void giveMeNewLogin(boolean truth)
    {
        frameNewLogin.setVisible(truth);
    }

    public CreateNewUser() {
        saveButton.addActionListener(new ActionListener() {
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
                String newLogin = textField1.getText().toString();
                String newPassword = textField2.getText().toString();

                int userCheck=0;
                for(User x : ListBack)
                {
                    if(x.getLogin().equals(newLogin))
                    {
                        userCheck++;
                    }
                }
                if(userCheck>0)
                {
                    JOptionPane.showMessageDialog(panelMain, "That user's name already exist, write another one");
                }
                else {
                    if(!textField1.getText().equals("")&&!textField2.getText().equals("")&&clientRadioButton.isSelected())
                    {
                        Client newClient = new Client(newLogin, newPassword);
                        ListBack.add(newClient);
                    }
                    else if(!textField1.getText().equals("")&&!textField2.getText().equals("")&&customerRadioButton.isSelected())
                    {
                        Cust newCust = new Cust(newLogin, newPassword);
                        ListBack.add(newCust);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(panelMain, "Choose which type of user do you want to create, Please do not forget to fill Login and Password fields");
                        userCheck++;
                    }
                }

                if(userCheck==0) {
                    fileWorker.fileWriter(ListBack, "logins");
                    JOptionPane.showMessageDialog(panelMain, "New user is saved");
                    giveMeNewLogin(false);
                    giveMeAdmin(true);
                }


            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeNewLogin(false);
                giveMeUserManagement(true);
            }
        });
    }
}
