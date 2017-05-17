package GUI_Assignment.GUI_Forms.Users_Stuff;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI_Assignment.GUI_Forms.For_Admin.main_admin_form.giveMeAdmin;
import static GUI_Assignment.GUI_Forms.Users_Stuff.CreateNewUser.setNewLoginVisible;
import static GUI_Assignment.GUI_Forms.Users_Stuff.UsersView.setUsersViewVisible;
import static GUI_Assignment.GUI_Forms.Users_Stuff.deleteUser.setDeleteUsersVisible;
import static GUI_Assignment.GUI_Forms.Users_Stuff.editUsers.setEditUsersVisible;

/**
 * Created by adil on 24/01/17.
 */
public class UserManagingMenu {
    private JButton createNewUserButton;
    private JButton editUserButton;
    private JButton deleteUserButton;
    private JButton showAllUserButton;
    private JButton cancelButton;
    private JPanel MainPanelManagingUsers;
    private static JFrame frameUserManagement;

    public JPanel getMainPanelManagingUsers() {
        return MainPanelManagingUsers;
    }
    public static void setUserManagementVisible()
    {
        frameUserManagement = new JFrame("User's management");
        frameUserManagement.setContentPane(new UserManagingMenu().getMainPanelManagingUsers());
        frameUserManagement.pack();
        frameUserManagement.setVisible(true);
        frameUserManagement.setLocation(520,200);
    }
    public static void giveMeUserManagement(boolean truth)
    {
        frameUserManagement.setVisible(truth);
    }

    public UserManagingMenu() {
        createNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNewLoginVisible();
                giveMeUserManagement(false);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeUserManagement(false);
                giveMeAdmin(true);
            }
        });
        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEditUsersVisible();
                giveMeUserManagement(false);
            }
        });
        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDeleteUsersVisible();
                giveMeUserManagement(false);
            }
        });
        showAllUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUsersViewVisible();
                giveMeUserManagement(false);
            }
        });
    }
}
