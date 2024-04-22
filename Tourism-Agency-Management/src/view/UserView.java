package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;

public class UserView extends Layout {
    // GUI components
    private JPanel container;
    private JPanel w_top;
    private JPanel w_bottom;
    private JTable tbl_user_list;
    private JButton btn_prev_menu;
    private JButton btn_new;
    private JButton btn_saveUpdate;
    private JButton btn_delete;
    private JTextField fld_user_pass;
    private JTextField fld_user_name;
    private JTextField fld_password;
    private JComboBox cmbx_user_perm;
    private JButton btn_edit;
    private JComboBox cmb_search_perm;
    private JButton btn_search;
    private JButton btn_list_all;
    // Table model for user list
    private DefaultTableModel model;
    // UserManager instance for managing user operations
    private UserManager userManager;
    // User instance
    private User user;
    // Popup menu for table
    private JPopupMenu jPopupMenu;

    // Constructor
    public UserView(User user) {
        this.user = user;
        userManager = new UserManager();
        add(container);
        pageArt(500, 500, "User Management");

        // Load user data into the table
        userTableLoad();

        // Add roles to search combo box
        cmb_search_perm.addItem(User.Perm.ADMIN);
        cmb_search_perm.addItem(User.Perm.EMPLOYEE);

        // Mouse listener for selecting rows in the table
        tbl_user_list.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectRow = tbl_user_list.rowAtPoint(e.getPoint());
                tbl_user_list.setRowSelectionInterval(selectRow, selectRow);
            }
        });

        // Button to add new user
        btn_new.addActionListener(new ActionListener() { // Section - 7,8 : The admin user type has been coded so that it can perform operations such as employee listing, adding, updating and deleting mentioned in the project.
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the UserEditView for adding a new user
                UserEditView add = new UserEditView(new User());
                // Add a listener to refresh the user table when the edit view is closed
                add.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        userTableLoad();
                    }
                });
            }
        });

        // Button to return to the previous menu
        btn_prev_menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Delete button action listener
        btn_delete.addActionListener(new ActionListener() { // Section - 7,8 : The admin user type has been coded so that it can perform operations such as employee listing, adding, updating and deleting mentioned in the project.
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the ID of the selected user
                int selectId = (int) tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0);
                // Show confirmation dialog before deleting the user
                if (JOptionPane.showConfirmDialog(null, "Delete user?", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                    // Delete the user from the database
                    userManager.delete(selectId);
                    // Reload the user table
                    userTableLoad();
                } else {
                    Helper.getMessage("Canceled", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                }
            }
        });

        // Edit button action listener
        btn_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { // Section - 7,8 : The admin user type has been coded so that it can perform operations such as employee listing, adding, updating and deleting mentioned in the project.
                // Check if a row is selected in the table
                if (tbl_user_list.getSelectedRow() <= 0) {
                    // Show error message if no user is selected
                    Helper.getMessage("Please select a user", "Error");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                } else {
                    // Get the ID of the selected user
                    int selectId = (int) tbl_user_list.getValueAt(tbl_user_list.getSelectedRow(), 0);
                    // Open the UserEditView for editing the selected user
                    UserEditView editView = new UserEditView(userManager.getById(selectId));
                    // Add a listener to refresh the user table when the edit view is closed
                    editView.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            userTableLoad();
                        }
                    });
                }
            }
        });

        // Search button action listener
        btn_search.addActionListener(new ActionListener() { // Section - 7,8 : The admin user type has been coded so that it can perform operations such as employee listing, adding, updating and deleting mentioned in the project.
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected role from the combo box
                User.Perm selectedRole = (User.Perm) cmb_search_perm.getSelectedItem();
                if (selectedRole != null) {
                    // Load users based on the selected role
                    loadUserFilter(selectedRole);
                } else {
                    // If no role is selected, load all users
                    userTableLoad();
                }
            }
        });

        // Button to list all users
        btn_list_all.addActionListener(new ActionListener() { // Section - 7,8 : The admin user type has been coded so that it can perform operations such as employee listing, adding, updating and deleting mentioned in the project.
            @Override
            public void actionPerformed(ActionEvent e) {
                userTableLoad();
            }
        });
    }

    // Method to filter users based on role
    public void loadUserFilter(User.Perm roleFilter) { // Section - 7,8: The admin user type has been coded so that it can perform operations such as employee listing, adding, updating and deleting mentioned in the project.
        // Get the table model
        model = (DefaultTableModel) tbl_user_list.getModel();
        // Reset table selection
        tableMouseSelect(tbl_user_list);
        // Set column headers
        Object[] col = {"ID", "Name", "PASSWORD", "PERM"};
        model.setColumnIdentifiers(col);
        model.setRowCount(0);
        // Iterate over users and add them to the table if their role matches the filter
        for (User i : userManager.findByAll()) {
            if (i.getPerm().equals(roleFilter)) {
                Object[] list = {i.getUser_id(), i.getUser_name(), i.getUser_pass(), i.getPerm()};
                model.addRow(list);
            }
        }
        // Set table properties
        setTableProperties();
    }

    // Method to set table properties
    public void setTableProperties() {
        // Set preferred width for table columns
        tbl_user_list.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl_user_list.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbl_user_list.getColumnModel().getColumn(3).setPreferredWidth(100);
        // Disable table editing and resizing
        tbl_user_list.setEnabled(false);
        tbl_user_list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_user_list.getTableHeader().setResizingAllowed(false);
        tbl_user_list.getTableHeader().setReorderingAllowed(false);
    }

    // Method to load user data into the table
    public void userTableLoad() {
        // Get the table model
        model = (DefaultTableModel) tbl_user_list.getModel();
        model.setRowCount(0); // Clear existing data
        Object[] columns = {"ID", "Name", "Password", "Role"};
        model.setColumnIdentifiers(columns);
        // Iterate over users and add them to the table
        for (User userList : userManager.findByAll()) {
            Object[] info = {userList.getUser_id(), userList.getUser_name(), userList.getUser_pass(), userList.getPerm()};
            model.addRow(info);
        }
        // Set table properties
        setTableProperties();
    }
}
