package view;

import business.UserManager;
import core.Helper;
import dao.UserDao;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout {
    private JPanel container;               // Main container panel
    private JPanel panel_top;               // Top panel containing page title
    private JPanel panel_bottom;            // Bottom panel containing form fields and buttons
    private JLabel lbl_page_title;          // Label for the page title
    private JLabel lbl_username;            // Label for the username field
    private JTextField fld_username;       // Text field for entering username
    private JButton btn_login;              // Button for login action
    private JButton btn_exit;               // Button for exit action
    private JPasswordField fld_password;   // Password field for entering password
    private JFormattedTextField fld_password3;  // Password field for entering password (why two password fields?)
    private UserDao userDao;                // DAO object for user data access
    private UserManager userManager;        // Manager object for user-related operations

    // Constructor for the LoginView class
    public LoginView() {
        userDao = new UserDao();            // Initialize user data access object
        userManager = new UserManager();    // Initialize user manager object
        add(container);                     // Add the main container panel to the layout
        pageArt(500, 500, "Tourism Agency Management");  // Set the page dimensions and title
        setTheme("Nimbus");                 // Set the look and feel theme to Nimbus

        // ActionListener for the login button
        btn_login.addActionListener(e -> { // Section 9 : User registration control

            // Check if username or password fields are empty
            if (Helper.isFieldEmpty(fld_username) || Helper.isFieldEmpty(fld_password)) {
                // Section : 9 Informational message
                Helper.getMessage("Fields are not empty!", "Error"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
            } else {
                // Attempt to login with the provided username and password
                User user = userManager.login(fld_username.getText(), fld_password.getText());
                if (user == null) {
                    // Section : 9 Informational message
                    Helper.getMessage("Username or password is wrong!", "Error"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                } else {
                    // If login is successful, redirect to the menu view
                    MenuView menuView = new MenuView(user);
                    dispose();  // Close the current login view
                }
            }
        });

        // ActionListener for the exit button
        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application when the exit button is clicked
            }
        });
    }
}
