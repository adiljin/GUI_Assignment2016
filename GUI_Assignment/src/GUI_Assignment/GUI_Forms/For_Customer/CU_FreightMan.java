package GUI_Assignment.GUI_Forms.For_Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI_Assignment.GUI_Forms.For_Customer.CU_AddFreight.setAddFreightVisible;
import static GUI_Assignment.GUI_Forms.For_Customer.CU_DeleteFreight.setDeleteFreightVisible;
import static GUI_Assignment.GUI_Forms.For_Customer.CU_EditFreight.setEditFreightVisible;
import static GUI_Assignment.GUI_Forms.For_Customer.CU_ViewFreight.setFrameViewFreightVisible;
import static GUI_Assignment.GUI_Forms.For_Customer.main_customer_form.giveMeMainCustomerForm;

/**
 * Created by adil on 28/11/16.
 */
public class CU_FreightMan {
    private JPanel panelFreightMan;
    private JButton addFreightButton;
    private JButton editFreightsButton;
    private JButton deleteFreightsButton;
    private JButton viewBookingFreightsButton;
    private JButton returnButton;
    private static JFrame frameCU_FreightMan;

    public CU_FreightMan() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeFreightsMan(false);
                giveMeMainCustomerForm(true);
            }
        });
        addFreightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setAddFreightVisible();
                giveMeFreightsMan(false);
            }
        });
        editFreightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEditFreightVisible();
                giveMeFreightsMan(false);
            }
        });
        viewBookingFreightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFrameViewFreightVisible();
                giveMeFreightsMan(false);
            }
        });
        deleteFreightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDeleteFreightVisible();
                giveMeFreightsMan(false);
            }
        });
    }

    public static void setFreightsManVisible()
    {
        frameCU_FreightMan = new JFrame("Freight Management");
        frameCU_FreightMan.setContentPane(new CU_FreightMan().panelFreightMan);
        frameCU_FreightMan.pack();
        frameCU_FreightMan.setVisible(true);
        frameCU_FreightMan.setLocation(520,200);
    }

    public static void giveMeFreightsMan(boolean truth)
    {
        frameCU_FreightMan.setVisible(truth);
    }
}
