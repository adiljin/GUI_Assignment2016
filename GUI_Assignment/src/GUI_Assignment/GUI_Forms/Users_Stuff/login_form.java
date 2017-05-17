package GUI_Assignment.GUI_Forms.Users_Stuff;

import GUI_Assignment.GUI_Forms.For_Customer.main_customer_form;
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

import static GUI_Assignment.GUI_Forms.For_Admin.main_admin_form.setAdminVisible;
import static GUI_Assignment.GUI_Forms.For_Client.main_client_form.setClientVisible;
import static GUI_Assignment.fileWorker.fileWriter;

/**
 * Created by adil on 25/11/16.
 */
public class login_form
{
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton logInButton;
    private JButton exitButton;
    private JPanel panelMain;
    private JComboBox comboBox1;
    private static JFrame frameLogin;

    public JPanel getPanelMain() {
        return panelMain;
    }

    public static void setLoginVisible()
    {
        frameLogin = new JFrame("Login's Menu");
        frameLogin.setContentPane(new login_form().getPanelMain());
        frameLogin.pack();
        frameLogin.setVisible(true);
        frameLogin.setLocation(520,200);
    }

    public static void giveMeLogin(boolean truth)
    {
        frameLogin.setVisible(truth);
    }


    public login_form() {

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to exit?","Warning",dialogButton);
                if(dialogResult == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        logInButton.addActionListener(new ActionListener() {
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
                    downloadLogins();
                }

                String password = passwordField1.getText().toString();

                int mes = 0;

                for(User x : ListBack)
                {
                    if((x instanceof Admin) && (x.getLogin().equals(comboBox1.getSelectedItem().toString())) && (x.getPassword().equals(password)))
                    {
                        mes++;
                        passwordField1.setText("");
                        giveMeLogin(false);
                        setAdminVisible();

                    }
                    else if((x instanceof Client) && (x.getLogin().equals(comboBox1.getSelectedItem().toString())) && (x.getPassword().equals(password)))
                    {
                        mes++;
                        passwordField1.setText("");
                        giveMeLogin(false);
                        setClientVisible();
                    }
                    else if((x instanceof Cust) && (x.getLogin().equals(comboBox1.getSelectedItem().toString())) && (x.getPassword().equals(password)))
                    {
                        mes++;
                        passwordField1.setText("");
                        giveMeLogin(false);
                        main_customer_form.setCustomerVisible();
                    }
                }
                if(mes==0)
                {
                    JOptionPane.showMessageDialog(panelMain, "Login or password were wrong, try again");
                }
            }
        });
    }

    public static void downloadLogins()
    {
        ArrayList<User> list = new ArrayList<User>();
        Admin admin = new Admin("admin","admin");

        Client client1 = new Client("client1", "client1");
        Client client2 = new Client("client2", "client2");

        Cust cust1 = new Cust("cust1", "cust1");
        Cust cust2 = new Cust("cust2", "cust2");

        list.add(admin);
        list.add(client1);
        list.add(client2);
        list.add(cust1);
        list.add(cust2);

        fileWriter(list, "logins");
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
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
            downloadLogins();
        }

        String[] dbData = new String[ListBack.size()];

        for(int i=0;i<ListBack.size();i++)
        {
            dbData[i] = ListBack.get(i).getLogin();
        }

        comboBox1 = new JComboBox(dbData);
    }
}
