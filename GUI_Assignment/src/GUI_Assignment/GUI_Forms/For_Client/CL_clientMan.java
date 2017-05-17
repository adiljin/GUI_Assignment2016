package GUI_Assignment.GUI_Forms.For_Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI_Assignment.GUI_Forms.For_Client.CL_RegisterCompany.setRegisterCompanyVisible;
import static GUI_Assignment.GUI_Forms.For_Client.CL_ViewCompanies.setViewCompaniesVisible;
import static GUI_Assignment.GUI_Forms.For_Client.CL_editShippingCompany.setClientShippingCompany;
import static GUI_Assignment.GUI_Forms.For_Client.main_client_form.giveMeMainClientForm;

/**
 * Created by adil on 28/11/16.
 */
public class CL_clientMan {
    private JPanel panelClientMan;
    private JButton registerShippingCompanyButton;
    private JButton editShippingCompanyButton;
    private JButton deleteShippingCompanyButton;
    private JButton viewShippingCompanyButton;
    private JButton returnButton;
    private static JFrame frameCL_clientMan;

    public CL_clientMan() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeMainClientForm(true);
                giveMeCL_clientMan(false);
            }
        });
        registerShippingCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setRegisterCompanyVisible();
                giveMeCL_clientMan(false);
            }
        });
        editShippingCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setClientShippingCompany();
                giveMeCL_clientMan(false);
            }
        });
        deleteShippingCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CL_deleteShippingCompany.setFrameDeleteShipping();
                giveMeCL_clientMan(false);
            }
        });
        viewShippingCompanyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setViewCompaniesVisible();
                giveMeCL_clientMan(false);
            }
        });
    }

    public static void setCL_clientManVisible()
    {
        frameCL_clientMan = new JFrame("Client's Main Menu");
        frameCL_clientMan.setContentPane(new CL_clientMan().panelClientMan);
        frameCL_clientMan.pack();
        frameCL_clientMan.setVisible(true);
        frameCL_clientMan.setLocation(520,200);
    }

    public static void giveMeCL_clientMan(boolean truth)
    {
        frameCL_clientMan.setVisible(truth);
    }
}
