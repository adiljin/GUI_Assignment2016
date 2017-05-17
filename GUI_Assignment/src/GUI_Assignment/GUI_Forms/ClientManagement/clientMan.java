package GUI_Assignment.GUI_Forms.ClientManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI_Assignment.GUI_Forms.ClientManagement.RegisterCompany.setRegisterCompanyVisible;
import static GUI_Assignment.GUI_Forms.ClientManagement.ViewCompanies.setViewCompaniesVisible;
import static GUI_Assignment.GUI_Forms.ClientManagement.deleteShippingCompany.setFrameDeleteShipping;
import static GUI_Assignment.GUI_Forms.ClientManagement.editShippingCompany.setClientShippingCompany;
import static GUI_Assignment.GUI_Forms.For_Admin.main_admin_form.giveMeAdmin;

/**
 * Created by adil on 26/11/16.
 */
public class clientMan{
    private JButton registerShippingCompanyButton;
    private JButton editShippingCompanyButton;
    private JButton deleteShippingCompanyButton;
    private JButton viewShippingCompanyButton;
    private JButton returnButton;
    private JPanel panelClientMan;
    private static JFrame frameClientMan;

    public clientMan() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeClientMan(false);
                giveMeAdmin(true);
            }
        });
        registerShippingCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeClientMan(false);
                setRegisterCompanyVisible();
            }
        });
        viewShippingCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeClientMan(false);
                setViewCompaniesVisible();
            }
        });
        editShippingCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeClientMan(false);
                setClientShippingCompany();
            }
        });
        deleteShippingCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeClientMan(false);
                setFrameDeleteShipping();
            }
        });
    }

    public static void setClientManVisible()
    {
        frameClientMan = new JFrame("Admin's Main Menu");
        frameClientMan.setContentPane(new clientMan().panelClientMan);
        frameClientMan.pack();
        frameClientMan.setVisible(true);
        frameClientMan.setLocation(520,200);
    }

    public static void giveMeClientMan(boolean truth)
    {
        frameClientMan.setVisible(truth);
    }
}
