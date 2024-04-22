package view;

import business.ReservationManager;
import business.RoomManager;
import business.SearchManager;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReservationEditView extends Layout {
    private JPanel container;               // Main container panel
    private JTextField fld_fullname;        // Text field for guest's full name
    private JTextField fld_id;              // Text field for guest's ID
    private JTextField fld_mpno;            // Text field for guest's mobile phone number
    private JTextField fld_email;           // Text field for guest's email
    private JTextField fld_check_in;        // Text field for check-in date
    private JTextField fld_chekc_out;       // Text field for check-out date
    private JTextField fld_child_count;     // Text field for child count
    private JTextField fld_adult_count;     // Text field for adult count
    private JButton btn_search_save;        // Button for saving reservation details
    private JButton btn_search_cancel;      // Button for canceling reservation details
    private JLabel lbl_fullname;            // Label for guest's full name
    private JLabel lbl_id;                  // Label for guest's ID
    private JLabel lbl_mpno;                // Label for guest's mobile phone number
    private JLabel lbl_email;               // Label for guest's email
    private JLabel lbl_check_in;            // Label for check-in date
    private JLabel lbl_check_out;           // Label for check-out date
    private JLabel lbl_child_count;         // Label for child count
    private JLabel lbl_adult_count;         // Label for adult count
    private JLabel lbl_search_title;        // Label for search title
    private JPanel w_bottom;                // Panel for bottom section
    private JPanel w_top;                   // Panel for top section
    private JLabel lbl_total_price;         // Label for displaying total price
    private ReservationManager reservationManager;  // Manager object for reservation-related operations
    private Reservation reservation;        // Object for managing reservation data
    private RoomManager roomManager;        // Manager object for room-related operations
    private SearchManager searchManager;    // Manager object for search-related operations
    private Room room;                      // Object for managing room data
    private Season season;                  // Object for managing season data
    private Hotel hotel;                    // Object for managing hotel data

    // Constructor for the ReservationEditView class
    public ReservationEditView(Reservation reservation, Room room, Season season, Hotel hotel) {
        this.hotel = hotel;                         // Set the hotel object
        searchManager = new SearchManager();        // Initialize search manager object
        roomManager = new RoomManager();            // Initialize room manager object
        reservationManager = new ReservationManager();  // Initialize reservation manager object
        this.reservation = reservation;             // Set the reservation object
        this.room = room;                           // Set the room object
        this.season = season;                       // Set the season object
        add(container);                             // Add the main container panel to the layout
        pageArt(640, 480, "Guest Information");     // Set the page dimensions and title

        fld_chekc_out.setText("yyyy-mm-dd");        // Provide a hint for date format to the user
        fld_check_in.setText("yyyy-mm-dd");         // Provide a hint for date format to the user

        // If editing existing reservation data, fill the fields
        if (reservation.getReservation_id() != 0) {
            loadReservation();                      // Fill fields for editing
        }

        // ActionListener for the save button
        btn_search_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveOrUpdateReservation();           // Save or update reservation data based on conditions
            }
        });

        // ActionListener for the cancel button
        btn_search_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();                           // Close the current view
            }
        });
    }

    // Load existing reservation data into the fields for editing
    public void loadReservation() {
        fld_chekc_out.setText(String.valueOf(reservation.getReservation_checkout_date()));
        fld_check_in.setText(String.valueOf(reservation.getReservation_checkin_date()));
        fld_adult_count.setText(String.valueOf(reservation.getReservation_adult_count()));
        fld_child_count.setText(String.valueOf(reservation.getReservation_child_count()));
        fld_email.setText(reservation.getReservation_guest_email());
        fld_fullname.setText(reservation.getReservation_guest_fullname());
        fld_id.setText(reservation.getReservation_guest_id());
        fld_mpno.setText(reservation.getReservation_guest_mpno());
        lbl_total_price.setText(String.valueOf(reservation.getReservation_totol_price() + "₺"));
    }

    // Save or update reservation data based on conditions
    private void saveOrUpdateReservation() {
        if (Helper.isFieldListEmpty(new JTextField[]{fld_mpno, fld_id, fld_fullname, fld_email, fld_check_in, fld_child_count, fld_chekc_out, fld_adult_count})) {
            Helper.getMessage("Not Null", "Error"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
        } else {
            if (reservation.getReservation_id() != 0) {
                updateReservation();                // Update reservation data
            } else {
                saveReservation();                  // Save new reservation data
            }
        }
    }

    // Update existing reservation data
    private void updateReservation() { // Section 17-18 : The price of the accommodation is successfully calculated based on guest information, number of nights to stay and room price.
        int stock = room.getRoom_stock_quantity();

        reservation.setReservation_guest_fullname(fld_fullname.getText());
        reservation.setReservation_guest_id(fld_id.getText());
        reservation.setReservation_guest_mpno(fld_mpno.getText());
        reservation.setReservation_guest_email(fld_email.getText());
        reservation.setReservation_checkin_date(LocalDate.parse(fld_check_in.getText()));
        reservation.setReservation_checkout_date(LocalDate.parse(fld_chekc_out.getText()));
        reservation.setReservation_child_count(Integer.parseInt(fld_child_count.getText()));
        reservation.setReservation_adult_count(Integer.parseInt(fld_adult_count.getText()));

        // Calculate total price based on room prices and duration of stay
        LocalDate in = reservation.getReservation_checkin_date();
        LocalDate out = reservation.getReservation_checkout_date();
        int adult = room.getRoom_adult_price() * reservation.getReservation_adult_count();
        int child = room.getRoom_child_price() * reservation.getReservation_child_count();
        long daysBetween = ChronoUnit.DAYS.between(in, out);
        double totalPrice = (adult + child) * daysBetween;

        // Check if the stay exceeds the season duration
        LocalDate start = season.getSeason_start_date();
        LocalDate end = season.getSeason_end_date();

        int seasonStart = start.compareTo(in);
        int seasonEnd = end.compareTo(out);

        // If the duration exceeds the season limit, show an information message
        if (seasonStart > 0 || seasonEnd < 0) {
            Helper.getMessage("Exceed Season", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
        } else {
            // If the duration is negative, show an information message
            if (daysBetween < 0) {
                Helper.getMessage("Please enter correct date", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
            } else if (daysBetween == 0) {
                // If the stay is on the same day, calculate for 1 night
                if (stock > 0) {
                    room.setRoom_stock_quantity(stock);
                    daysBetween = 1;
                    totalPrice = (adult + child) * daysBetween;
                    reservation.setReservation_totol_price((int) totalPrice);
                    reservationManager.update(reservation);
                    Helper.getMessage("Saved", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                    lbl_total_price.setText(String.valueOf(totalPrice) + "₺");
                    btn_search_cancel.setText("Back");
                    roomManager.stockCheck(room);
                    roomManager.saveReservation(room);
                } else {
                    // If there are no available rooms, show an information message
                    Helper.getMessage("Out of Room stock", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                }
            } else {
                // Otherwise, save the reservation data
                if (stock > 0) {
                    room.setRoom_stock_quantity(stock);
                    reservation.setReservation_totol_price((int) totalPrice);
                    reservationManager.update(reservation);
                    Helper.getMessage("Saved", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                    btn_search_cancel.setText("Back");
                    lbl_total_price.setText(String.valueOf(totalPrice));
                    roomManager.stockCheck(room);
                } else {
                    // If there are no available rooms, show an information message
                    Helper.getMessage("Out of Room stock", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                }
            }
        }
    }

    // Save new reservation data
    private void saveReservation() { // Section 17-18-19 : The price of the accommodation is successfully calculated based on guest information, number of nights to stay and room price. Inventory of booking changes is running low.
        int stock = room.getRoom_stock_quantity();
        String currentDate = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        // Set reservation details
        reservation.setReservation_room_id(room.getRoom_id());
        reservation.setReservation_hotel_id(hotel.getHotel_id());
        reservation.setReservation_guest_fullname(fld_fullname.getText());
        reservation.setReservation_guest_id(fld_id.getText());
        reservation.setReservation_guest_mpno(fld_mpno.getText());
        reservation.setReservation_guest_email(fld_email.getText());
        reservation.setReservation_checkin_date(LocalDate.parse((fld_check_in.getText())));
        reservation.setReservation_checkout_date(LocalDate.parse(fld_chekc_out.getText()));
        reservation.setReservation_child_count(Integer.parseInt(fld_child_count.getText()));
        reservation.setReservation_adult_count(Integer.parseInt(fld_adult_count.getText()));
        reservation.setReservation_date(LocalDate.parse(currentDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Calculate total price based on room prices and duration of stay
        LocalDate in = reservation.getReservation_checkin_date();
        LocalDate out = reservation.getReservation_checkout_date();
        int adult = room.getRoom_adult_price() * reservation.getReservation_adult_count();
        int child = room.getRoom_child_price() * reservation.getReservation_child_count();
        long daysBetween = ChronoUnit.DAYS.between(in, out);
        double totalPrice = (adult + child) * daysBetween;

        // Check if the stay exceeds the season duration
        LocalDate start = season.getSeason_start_date();
        LocalDate end = season.getSeason_end_date();

        int seasonStart = start.compareTo(in);
        int seasonEnd = end.compareTo(out);

        // If the duration exceeds the season limit, show an information message
        if (seasonStart > 0 || seasonEnd < 0) {
            Helper.getMessage("Exceed Season", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
        } else {
            // If the duration is negative, show an information message
            if (daysBetween < 0) {
                Helper.getMessage("Please enter correct date", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
            } else if (daysBetween == 0) {
                // If the stay is on the same day, calculate for 1 night
                if (stock > 0) {
                    stock--;
                    room.setRoom_stock_quantity(stock);
                    daysBetween = 1;
                    totalPrice = (adult + child) * daysBetween;
                    reservation.setReservation_totol_price((int) totalPrice);
                    reservationManager.save(reservation);
                    Helper.getMessage("Saved", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                    lbl_total_price.setText(String.valueOf(totalPrice) + "₺");
                    btn_search_cancel.setText("Back");
                    roomManager.stockCheck(room);
                    roomManager.saveReservation(room);
                } else {
                    // If there are no available rooms, show an information message
                    Helper.getMessage("Out of Room stock", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                }
            } else {
                // Otherwise, save the reservation data
                if (stock > 0) {
                    stock--;
                    room.setRoom_stock_quantity(stock);
                    reservation.setReservation_totol_price((int) totalPrice);
                    reservationManager.save(reservation);
                    Helper.getMessage("Saved", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                    btn_search_cancel.setText("Back");
                    lbl_total_price.setText(String.valueOf(totalPrice));
                    roomManager.stockCheck(room);
                } else {
                    // If there are no available rooms, show an information message
                    Helper.getMessage("Out of Room stock", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                }
            }
        }
    }
}
