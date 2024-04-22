package view;

import business.HotelManager;
import business.PansionManager;
import business.SeasonManager;
import core.Helper;
import entity.Hotel;
import entity.Pansion;
import entity.Season;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HotelEditView extends Layout {
    private JPanel container;
    private JButton btn_save;
    private JButton btn_back;
    private JTextField fld_hotel_name;
    private JComboBox<Boolean> cmbx_hotel_freepark;
    private JComboBox<Boolean> cmbx_hotel_free_wifi;
    private JComboBox<Boolean> cmbx_hotel_swimming_pool;
    private JComboBox<Boolean> cmbx_hotel_fitness_center;
    private JComboBox<Boolean> cmbx_hotel_concierge;
    private JComboBox<Boolean> cmbx_hotel_spa;
    private JComboBox<Boolean> cmbx_hotel_room_services;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_region;
    private JTextField fld_hotel_adress;
    private JTextField fld_hotel_email;
    private JTextField fld_hotel_phone;
    private JComboBox<String> cmbx_hotel_rating;
    private JTable tbl_pansion_type;
    private JLabel lbl_hotel_name;
    private JLabel lbl_hotel_city;
    private JLabel lbl_hotel_region;
    private JLabel lbl_hotel_adress;
    private JLabel lbl_hotel_email;
    private JLabel lbl_hotel_phone;
    private JLabel lbl_hotel_rating;
    private JLabel lbl_hotel_free_parkin;
    private JLabel lbl_hotel_free_wifi;
    private JLabel lbl_hotel_swimming_pool;
    private JLabel lbl_hotel_fitness_center;
    private JLabel lbl_hotel_concierge;
    private JLabel lbl_hotel_spa;
    private JLabel lbl_hotel_room_services;
    private JFormattedTextField fld_start_seasson;
    private JFormattedTextField fld_end_seasson;
    private Hotel hotel = null;
    private HotelManager hotelManager;
    private DefaultTableModel model;
    private PansionManager pansionManager;
    private SeasonManager seasonManager;

    public HotelEditView(Hotel hotel) {
        seasonManager = new SeasonManager();
        hotelManager = new HotelManager();
        pansionManager = new PansionManager();
        this.hotel = hotel;
        add(container);
        pageArt(700, 1080, "Edit");

        loadPansionTypes();

        // If it's an update operation, populate the fields with existing data
        if (hotel.getHotel_id() != 0) {
            loadHotelDetails();
        }

        createComboBox(); // Fill the comboboxes with options

        // Save button action
        btn_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOrUpdateHotel(); // Check conditions inside for saving or updating
            }
        });

        // Back button action
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    // Load the pansion types into the table
    private void loadPansionTypes() {
        model = (DefaultTableModel) tbl_pansion_type.getModel();
        model.setRowCount(0);
        model.addColumn("ID");
        model.addColumn("Pansion Type");

        // Get all pansion types using PansionManager
        for (Pansion pansion : pansionManager.findByAll()) {
            model.addRow(new Object[]{pansion.getPansion_id(), pansion.getPansion_type()});

            // Allow multiple selection in the table
            tbl_pansion_type.setSelectionModel(new DefaultListSelectionModel() {
                @Override
                public void setSelectionInterval(int index0, int index1) {
                    if (super.isSelectedIndex(index0)) {
                        super.removeSelectionInterval(index0, index1);
                    } else {
                        super.addSelectionInterval(index0, index1);
                    }
                }
            });
        }
        // Set default values for season dates
        fld_start_seasson.setText("--/--/----");
        fld_end_seasson.setText("--/--/----");
    }

    // Populate the fields with existing hotel details for update operation
    private void loadHotelDetails() {
        fld_hotel_name.setText(hotel.getHotel_name());
        fld_hotel_city.setText(hotel.getHotel_city());
        fld_hotel_region.setText(hotel.getHotel_region());
        fld_hotel_adress.setText(hotel.getHotel_fulladres());
        fld_hotel_email.setText(hotel.getHotel_mail());
        fld_hotel_phone.setText(hotel.getHotel_phone());
        cmbx_hotel_rating.setSelectedItem(hotel.getHotel_star());
        cmbx_hotel_freepark.setSelectedItem(hotel.isHotel_free_parking());
        cmbx_hotel_swimming_pool.setSelectedItem(hotel.isHotel_swimming_pool());
        cmbx_hotel_fitness_center.setSelectedItem(hotel.isHotel_fitness_center());
        cmbx_hotel_concierge.setSelectedItem(hotel.isHotel_concierge());
        cmbx_hotel_spa.setSelectedItem(hotel.isHotel_spa());
        cmbx_hotel_room_services.setSelectedItem(hotel.isHotel_room_services());
        cmbx_hotel_free_wifi.setSelectedItem(hotel.isHotel_free_wifi());
        // Fetch season dates
        for (Season season : seasonManager.findByAll()) {
            fld_start_seasson.setText(String.valueOf(season.getSeason_start_date()));
            fld_end_seasson.setText(String.valueOf(season.getSeason_end_date()));
        }
    }

    // Create comboboxes
    private void createComboBox() {
        cmbx_hotel_rating.addItem("1");
        cmbx_hotel_rating.addItem("2");
        cmbx_hotel_rating.addItem("3");
        cmbx_hotel_rating.addItem("4");
        cmbx_hotel_rating.addItem("5");

        cmbx_hotel_freepark.addItem(true);
        cmbx_hotel_freepark.addItem(false);

        cmbx_hotel_swimming_pool.addItem(true);
        cmbx_hotel_swimming_pool.addItem(false);

        cmbx_hotel_fitness_center.addItem(true);
        cmbx_hotel_fitness_center.addItem(false);

        cmbx_hotel_concierge.addItem(true);
        cmbx_hotel_concierge.addItem(false);

        cmbx_hotel_spa.addItem(true);
        cmbx_hotel_spa.addItem(false);

        cmbx_hotel_room_services.addItem(true);
        cmbx_hotel_room_services.addItem(false);

        cmbx_hotel_free_wifi.addItem(true);
        cmbx_hotel_free_wifi.addItem(false);
    }

    // Check conditions for saving or updating hotel information
    private void saveOrUpdateHotel() {
        if (Helper.isFieldListEmpty(new JTextField[]{fld_hotel_name, fld_hotel_city, fld_hotel_region, fld_hotel_adress, fld_hotel_email, fld_hotel_phone})) {
            Helper.getMessage("Not Null", "Error"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
        } else {
            if (hotel.getHotel_id() != 0) {
                updateHotel(); // Perform update operation
            } else {
                saveHotel(); // Perform save operation
            }
        }
    }

    // Update hotel information
    private void updateHotel() {
        // Section  10: Agency personnel can register contracted hotels into the system by entering hotel name, address, e-mail, phone number, stars and facility features information.

        // Set hotel details from input fields
        hotel.setHotel_name(fld_hotel_name.getText());
        hotel.setHotel_city(fld_hotel_city.getText());
        hotel.setHotel_region(fld_hotel_region.getText());
        hotel.setHotel_fulladres(fld_hotel_adress.getText());
        hotel.setHotel_mail(fld_hotel_email.getText());
        hotel.setHotel_phone(fld_hotel_phone.getText());
        hotel.setHotel_star(String.valueOf(cmbx_hotel_rating.getSelectedItem()));
        hotel.setHotel_free_parking((Boolean) cmbx_hotel_freepark.getSelectedItem());
        hotel.setHotel_free_wifi((Boolean) cmbx_hotel_free_wifi.getSelectedItem());
        hotel.setHotel_swimming_pool((Boolean) cmbx_hotel_swimming_pool.getSelectedItem());
        hotel.setHotel_fitness_center((Boolean) cmbx_hotel_fitness_center.getSelectedItem());
        hotel.setHotel_concierge((Boolean) cmbx_hotel_concierge.getSelectedItem());
        hotel.setHotel_spa((Boolean) cmbx_hotel_spa.getSelectedItem());
        hotel.setHotel_room_services((Boolean) cmbx_hotel_room_services.getSelectedItem());

        // Get season dates and set them
        for (Season season : seasonManager.findByAll()) {
            fld_start_seasson.setText(String.valueOf(season.getSeason_start_date()));
            fld_end_seasson.setText(String.valueOf(season.getSeason_end_date()));
        }

        // Get selected pansion types
        int[] selectedRows = tbl_pansion_type.getSelectedRows();
        String[] selectedPansionTypes = new String[selectedRows.length];

        for (int i = 0; i < selectedRows.length; i++) {
            selectedPansionTypes[i] = (String) tbl_pansion_type.getValueAt(selectedRows[i], 1);
        }

        // Set selected pansion types to the hotel object
        hotel.setHotel_pansion_type(selectedPansionTypes);

        // Update hotel information
        hotelManager.update(hotel);

        // Inform the user that the update operation is successful
        Helper.getMessage("Update", "Information");  // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
        btn_back.setText("Back");
    }

    // Save hotel information
    private void saveHotel() {

        // Section  10: Agency personnel can register contracted hotels into the system by entering hotel name, address, e-mail, phone number, stars and facility features information.

        // Set hotel details from input fields
        hotel.setHotel_name(fld_hotel_name.getText());
        hotel.setHotel_city(fld_hotel_city.getText());
        hotel.setHotel_region(fld_hotel_region.getText());
        hotel.setHotel_fulladres(fld_hotel_adress.getText());
        hotel.setHotel_mail(fld_hotel_email.getText());
        hotel.setHotel_phone(fld_hotel_phone.getText());
        hotel.setHotel_star(String.valueOf(cmbx_hotel_rating.getSelectedItem()));
        hotel.setHotel_free_parking((Boolean) cmbx_hotel_freepark.getSelectedItem());
        hotel.setHotel_free_wifi((Boolean) cmbx_hotel_free_wifi.getSelectedItem());
        hotel.setHotel_swimming_pool((Boolean) cmbx_hotel_swimming_pool.getSelectedItem());
        hotel.setHotel_fitness_center((Boolean) cmbx_hotel_fitness_center.getSelectedItem());
        hotel.setHotel_concierge((Boolean) cmbx_hotel_concierge.getSelectedItem());
        hotel.setHotel_spa((Boolean) cmbx_hotel_spa.getSelectedItem());
        hotel.setHotel_room_services((Boolean) cmbx_hotel_room_services.getSelectedItem());

        // Get season dates and set them
        for (Season season : seasonManager.findByAll()) {
            if (hotel.getHotel_id() == season.getSeason_hotel_id()) {
                fld_start_seasson.setText(String.valueOf(season.getSeason_start_date()));
                fld_end_seasson.setText(String.valueOf(season.getSeason_end_date()));
            }
            // Section 11 : Adds Full season by default. Detailed additions are made via the SeasonEditView class.
            if (hotel.getHotel_id() == 0) {
                fld_start_seasson.setText("2024-01-01");
                fld_end_seasson.setText("2024-12-31");
            }
        }

        // Get selected pansion types
        int[] selectedRows = tbl_pansion_type.getSelectedRows();
        String[] selectedPansionTypes = new String[selectedRows.length];

        for (int i = 0; i < selectedRows.length; i++) { // Section 12 : Hostel type management was done when hotels were added to the system.
            selectedPansionTypes[i] = tbl_pansion_type.getValueAt(selectedRows[i], 1).toString();
        }

        // Set selected pansion types to the hotel object
        hotel.setHotel_pansion_type(selectedPansionTypes);

        // Save hotel information
        hotelManager.save(hotel);

        // Inform the user that the save operation is successful
        Helper.getMessage("Saved" + "\n" + "Please set a Seasson!", "Information"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
        btn_back.setText("Back");
    }
}
