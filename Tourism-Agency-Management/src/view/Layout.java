package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

// Base class for defining common UI layouts and functionalities
public class Layout extends JFrame {

    // Constructor
    public Layout() {}

    // Method to set up window properties
    public void pageArt(int width, int height, String title) {
        setSize(width, height); // Set window size
        setTitle(title); // Set window title
        setResizable(false); // Disable window resizing
        setVisible(true); // Set window visibility
        // Center the window on the screen
        int x = (Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 2;
        int y = (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 2;
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
    }

    // Method to set the look and feel of the UI
    public void setTheme(String themeName) {
        // Iterate through available look and feels to find the desired theme
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if (info.getName().equals(themeName)) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    // Method to create a table with given data
    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model = (DefaultTableModel) table.getModel(); // Get table model
        model.setColumnIdentifiers(columns); // Set column headers
        table.getTableHeader().setReorderingAllowed(false); // Disable column reordering

        model.setRowCount(0); // Clear existing table data

        if (rows == null) {
            rows = new ArrayList<>(); // Initialize row data if not provided
        }

        // Add rows to the table
        for (Object[] row : rows) {
            model.addRow(row);
        }
    }

    // Method to get the ID of the selected row in a table
    public int getSelectedRow(JTable table, int columnIndex) {
        // Get the ID from the selected row and return it
        return (int) table.getValueAt(table.getSelectedRow(), columnIndex);
    }

    // Method to enable selecting rows in a table by mouse click
    public void tableMouseSelect(JTable table) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = table.rowAtPoint(e.getPoint()); // Get the selected row
                table.setRowSelectionInterval(selectedRow, selectedRow); // Select the row
            }
        });
    }
}
