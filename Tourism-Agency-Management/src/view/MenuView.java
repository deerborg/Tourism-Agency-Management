package view;

import business.UserManager;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuView extends Layout {
    private JPanel container;               // Main container panel
    private JLabel lbl_title;               // Label for the menu title
    private JButton btn_user_management;    // Button for user management
    private JButton btn_hotel_management;   // Button for hotel management
    private JButton btn_reservations;       // Button for reservations
    private JButton btn_logout;             // Button for logout
    private UserManager userManager;        // Manager object for user-related operations

    // Constructor for the MenuView class
    public MenuView(User user) {
        userManager = new UserManager();    // Initialize user manager object
        add(container);                     // Add the main container panel to the layout
        pageArt(500, 500, "Menu");          // Set the page dimensions and title

        // Hide the user management button if the user is not an admin
        if (!user.getPerm().equals(User.Perm.ADMIN)) {
            btn_user_management.setVisible(false);
        }

        // ActionListener for the user management button
        btn_user_management.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirect to the user management page if the user has admin privilege
                UserView userView = new UserView(new User());
                dispose();  // Close the current menu view
                userView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                    }
                });
            }
        });

        // ActionListener for the logout button
        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirect to the login view when the user clicks the logout button
                LoginView loginView = new LoginView();
                dispose();  // Close the current menu view
            }
        });

        // ActionListener for the hotel management button
        btn_hotel_management.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectId = user.getUser_id();
                HotelView hotelView = new HotelView(userManager.getById(selectId));
                dispose();  // Close the current menu view
                hotelView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        setVisible(true);
                    }
                });
            }
        });

        // ActionListener for the reservations button
        btn_reservations.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Redirect to the reservations view
                ReservationView reservationView = new ReservationView(user);
                dispose();  // Close the current menu view
                reservationView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        setVisible(true);
                    }
                });
            }
        });
    }
}
