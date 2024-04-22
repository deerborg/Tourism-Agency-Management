package view;

import business.PansionManager;
import core.Helper;
import entity.Pansion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PansionEditView extends Layout {
    private JPanel container;               // Main container panel
    private JPanel w_top;                   // Top panel containing title
    private JPanel w_bottom;                // Bottom panel containing form fields and buttons
    private JButton btn_back;               // Button for navigating back
    private JTextField fld_pansion_type;    // Text field for entering pansion type
    private JButton btn_save;               // Button for saving changes
    private Pansion pansion = null;         // Object for managing pansion data
    private PansionManager pansionManager;  // Manager object for pansion-related operations

    // Constructor for the PansionEditView class
    public PansionEditView(Pansion pansion) {
        pansionManager = new PansionManager();   // Initialize pansion manager object
        this.pansion = pansion;                   // Set the pansion object
        add(container);                          // Add the main container panel to the layout
        pageArt(700, 500, "Edit");                // Set the page dimensions and title
        setTheme("Nimbus");                       // Set the look and feel theme to Nimbus

        // If editing existing pansion data, fill the fields
        if (pansion.getPansion_id() != 0) {
            loadPansion(); // Fill fields for editing
        }

        // ActionListener for the save button
        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOrUpdatePansion(); // Save or update pansion data based on conditions
            }
        });

        // ActionListener for the back button
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current view
            }
        });
    }

    // Save new pansion data
    private void savePansions() {
        pansion.setPansion_type(fld_pansion_type.getText());
        pansionManager.save(pansion);
        Helper.getMessage("Saved", "Information"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
        btn_back.setText("Back");
    }

    // Perform save or update operations based on conditions
    private void saveOrUpdatePansion() {
        if (Helper.isFieldListEmpty(new JTextField[]{fld_pansion_type})) {
            Helper.getMessage("Not Null", "Error"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
        } else {
            if (pansion.getPansion_id() != 0) {
                updatePansion(); // Perform update operations
            } else {
                savePansions(); // Perform save operations for new data
            }
        }
    }

    // Update existing pansion data
    private void updatePansion() {
        pansion.setPansion_type(fld_pansion_type.getText());
        pansionManager.update(pansion);
        Helper.getMessage("Update", "Information"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
        btn_back.setText("Back");
    }

    // Fill fields for editing existing pansion data
    private void loadPansion() {
        fld_pansion_type.setText(pansion.getPansion_type());
    }
}
