package GUI_Assignment.GUI_Forms.FreightsManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI_Assignment.GUI_Forms.For_Admin.main_admin_form.giveMeAdmin;

/**
 * Created by adil on 27/11/16.
 */
public class FreightsMan {
    private JButton addFreightButton;
    private JButton editFreightsButton;
    private JButton deleteFreightsButton;
    private JButton viewBookingFreightsButton;
    private JButton returnButton;
    private JPanel panelFreightMan;
    private static JFrame frameFreightMan;

    public FreightsMan() {
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeFreightsMan(false);
                giveMeAdmin(true);
            }
        });
        addFreightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                giveMeFreightsMan(false);
                AddFreight.setAddFreightVisible();
            }
        });
        viewBookingFreightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewFreights.setFrameViewFreightVisible();
                giveMeFreightsMan(false);
            }
        });
        editFreightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditFreight.setEditFreightVisible();
                giveMeFreightsMan(false);
            }
        });
        deleteFreightsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteFreight.setDeleteFreightVisible();
                giveMeFreightsMan(false);
            }
        });
    }

    public static void setFreightsManVisible()
    {
        frameFreightMan = new JFrame("Freight Management");
        frameFreightMan.setContentPane(new FreightsMan().panelFreightMan);
        frameFreightMan.pack();
        frameFreightMan.setVisible(true);
        frameFreightMan.setLocation(520,200);
    }

    public static void giveMeFreightsMan(boolean truth)
    {
        frameFreightMan.setVisible(truth);
    }
}
