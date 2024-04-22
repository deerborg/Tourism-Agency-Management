package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserEditView extends Layout {

    // GUI components
    private JPanel container;
    private JPanel w_top;
    private JPanel w_bottom;
    private JTextField fld_user_name;
    private JTextField fld_password;
    private JComboBox cmb_perm;
    private JButton btn_save;
    private JButton btn_clear;
    private JButton btn_cancel;
    // UserManager instance for managing user operations
    private UserManager userManager;

    // Constructor
    public UserEditView(User user) {
        userManager = new UserManager();
        add(container);
        pageArt(500, 500, "Add User");

        // If user ID is not 0, it means it's an update operation, so populate fields with user data
        if (user.getUser_id() != 0) {
            fld_user_name.setText(user.getUser_name());
            fld_password.setText(user.getUser_pass());
            cmb_perm.addItem(user.getPerm());
        }

        // Populate combo box with permission values
        for (User.Perm perm : User.Perm.values()) {
            cmb_perm.addItem(perm.toString());
        }

        // Cancel button action listener to dispose the window
        btn_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Save button action listener for adding or updating a user
        btn_save.addActionListener(new ActionListener() { // Seciton - 7 : The admin user type has been coded so that it can perform operations such as employee listing, adding, updating and deleting mentioned in the project.
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if any field is empty
                if (Helper.isFieldEmpty(fld_user_name) && Helper.isFieldEmpty(fld_password) && Helper.isFieldEmpty((JTextField) cmb_perm.getSelectedItem())) {
                    // Show error message if any field is empty
                    Helper.getMessage("Not Null", "Error");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.

                } else {
                    // If user ID is not 0, it's an update operation
                    if (user.getUser_id() != 0) {
                        // Update user data
                        user.setUser_name(fld_user_name.getText());
                        user.setUser_pass(fld_password.getText());
                        user.setPerm(User.Perm.valueOf(cmb_perm.getSelectedItem().toString()));

                        if(!userManager.update(user)){
                            Helper.getMessage("User Already Exist","Information");
                        }else{
                            // Update user in the database
                            userManager.update(user);
                            // Show update confirmation message
                            Helper.getMessage("Update a User", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.

                            // Change button text to "Back" for the cancel button
                            btn_cancel.setText("Back");
                        }
                    } else { // If user ID is 0, it's an add operation
                        // Set user data

                        user.setUser_name(fld_user_name.getText());
                        user.setUser_pass(fld_password.getText());
                        user.setPerm(User.Perm.valueOf(cmb_perm.getSelectedItem().toString()));

                        if(!userManager.save(user)){
                            Helper.getMessage("User Already Exist","Information");
                        }else{
                            // Save user in the database
                            userManager.save(user);

                            // Show save confirmation message
                            Helper.getMessage("Saved", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.

                            // Change button text to "Back" for the cancel button
                            btn_cancel.setText("Back");
                        }

                    }
                }
            }
        });

        // Clear button action listener to clear all fields
        btn_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fld_user_name.setText("");
                fld_password.setText("");
                cmb_perm.setSelectedItem(null);
            }
        });
    }
}
