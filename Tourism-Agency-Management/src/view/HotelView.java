package view;

import business.*;
import core.ComboItem;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

public class HotelView extends Layout {
    private JPanel container;
    private JPanel w_top;
    private JPanel w_bottom;
    private JTable tbl_hotel;
    private JButton btn_edit;
    private JButton btn_new;
    private JButton btn_back;
    private JButton btn_delete;
    private JTabbedPane tabbedPane1;
    private JTable tbl_rooms;
    private JPanel rooms_container;
    private JPanel rooms_bottom;
    private JPanel pansions_bottom;
    private JTable tbl_pansions;
    private JPanel seassons_bottom;
    private JTable tbl_seasson;
    private JButton btn_back_room;
    private JButton btn_delete_room;
    private JButton btn_new_room;
    private JButton btn_edit_room;
    private JComboBox cmb_select_hotel;
    private JButton btn_select_hotel;
    private JButton btn_list_all;
    private JButton btn_back_pansion;
    private JButton btn_delete_pansion;
    private JButton btn_new_pansion;
    private JButton btn_edit_pansion;
    private JButton btn_edit_season;
    private JButton btn_new_season;
    private JButton btn_delete_season;
    private JButton btn_back_season;
    private JScrollPane tbl_season;
    private JComboBox cmb_theme_list;
    private JLabel lbl_welcome;
    private JLabel lbl_user_name;
    private HotelManager hotelManager;
    private DefaultTableModel model;
    private RoomManager roomManager;
    private PansionManager pansionManager;
    private SeasonManager seasonManager;
    private Object[] onlyHotelName;
    private UserManager userManager;

    public HotelView(User user) {
        pansionManager = new PansionManager();
        roomManager = new RoomManager();
        hotelManager = new HotelManager();
        seasonManager = new SeasonManager();
        userManager = new UserManager();
        add(container);
        pageArt(1500, 700, "Hotel Management");

        hotelTableLoad();//Create hotel table
        roomTableLoad(); // Room tablosu oluşturma
        hotelEditButtons(); // Creating a Room table
        pansionsTableLoad(); // Creating a hostel table
        pansionsEditButton(); // pansion için buton eventleri
        roomEditButtons(); // Button events for hostel
        filterForHotel(); // filter rooms by hotel
        seasonTableLoad(); // Creating the season table
        seasonEditButtons(); // Button events for seasons
        getTheme(); // User changing theme within the page
        filterRoom(); // Room filtering

        // Show authorized user name
        lbl_welcome.setText(lbl_welcome.getText() + " " + user.getUser_name());
    }


    // Create a hotel table
    public void hotelTableLoad() {
        model = (DefaultTableModel) tbl_hotel.getModel();

        model.setRowCount(0);

        // Adding data from the database to the hotel table
        Object[] columns = {"ID", "Name", "City", "Region", "Adress", "Email", "Phone", "Rating", "Parking", "WI-FI", "Pool", "Fitness Center", "Concierge", "SPA", "Services", "Pansion Type"};
        model.setColumnIdentifiers(columns);


        for (Hotel hotelList : hotelManager.findByAll()) {
            String pansionTypes = String.join(",", hotelList.getHotel_pansion_type());

            Object[] info = {hotelList.getHotel_id(), hotelList.getHotel_name(), hotelList.getHotel_city(), hotelList.getHotel_region(),
                    hotelList.getHotel_fulladres(), hotelList.getHotel_mail(), hotelList.getHotel_phone(), hotelList.getHotel_star(),
                    hotelList.isHotel_free_parking() ? "Yes" : "No", hotelList.isHotel_free_wifi() ? "Yes" : "No", hotelList.isHotel_swimming_pool() ? "Yes" : "No", hotelList.isHotel_fitness_center() ? "Yes" : "No",
                    hotelList.isHotel_concierge() ? "Yes" : "No", hotelList.isHotel_spa() ? "Yes" : "No", hotelList.isHotel_room_services() ? "Yes" : "No", pansionTypes};
            model.addRow(info);
        }
        //Table column width
        tbl_hotel.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbl_hotel.getColumnModel().getColumn(1).setPreferredWidth(180);
        tbl_hotel.getColumnModel().getColumn(2).setPreferredWidth(74);
        tbl_hotel.getColumnModel().getColumn(3).setPreferredWidth(74);
        tbl_hotel.getColumnModel().getColumn(4).setPreferredWidth(150);
        tbl_hotel.getColumnModel().getColumn(5).setPreferredWidth(150);
        tbl_hotel.getColumnModel().getColumn(6).setPreferredWidth(90);
        tbl_hotel.getColumnModel().getColumn(7).setPreferredWidth(50);
        tbl_hotel.getColumnModel().getColumn(8).setPreferredWidth(60);
        tbl_hotel.getColumnModel().getColumn(9).setPreferredWidth(50);
        tbl_hotel.getColumnModel().getColumn(10).setPreferredWidth(50);
        tbl_hotel.getColumnModel().getColumn(11).setPreferredWidth(100);
        tbl_hotel.getColumnModel().getColumn(12).setPreferredWidth(80);
        tbl_hotel.getColumnModel().getColumn(13).setPreferredWidth(80);
        tbl_hotel.getColumnModel().getColumn(14).setPreferredWidth(80);
        tbl_hotel.getColumnModel().getColumn(15).setPreferredWidth(180);

        //Makes the table uneditable and resizable
        tbl_hotel.setEnabled(false);
        tbl_hotel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_hotel.getTableHeader().setResizingAllowed(false);
        tbl_hotel.getTableHeader().setReorderingAllowed(false);
    }

    // Getting row information during mouse click
    public void hotelEditButtons() {
        tbl_hotel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectRow = tbl_hotel.rowAtPoint(e.getPoint());
                tbl_hotel.setRowSelectionInterval(selectRow, selectRow);
            }
        });

        // Hotel update
        btn_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tbl_hotel.getSelectedRow() < 0) { //If it does not select a value from the table
                    Helper.getMessage("Please select a Hotel", "Error");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                } else {
                    int selectId = (int) tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(), 0);
                    HotelEditView a = new HotelEditView(hotelManager.getById(selectId));
                    a.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            //Refreshes the tables when the update process is complete
                            hotelTableLoad();
                            filterRoom();
                            filterForHotel();
                            seasonTableLoad();
                            roomTableLoad();
                        }
                    });
                }
            }
        });
        //Adding a hotel
        btn_new.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HotelEditView hotelEditView = new HotelEditView(new Hotel());
                hotelEditView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        //Refreshes tables when save is complete
                        hotelTableLoad();
                        filterRoom();
                        filterForHotel();
                        seasonTableLoad();
                        roomTableLoad();
                    }
                });
            }
        });
        //  Cancel button
        btn_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        // Hotel deletion process
        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectId = (int) tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(), 0);
                if (JOptionPane.showConfirmDialog(null, "Deleted a Hotel", "Confirm", JOptionPane.YES_NO_OPTION) == 0) { // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                    hotelManager.delete(selectId);
                    // Refreshes tables when deletion is complete
                    seasonTableLoad();
                    roomTableLoad();
                    hotelTableLoad();
                    filterRoom();
                    filterForHotel();
                } else {
                    Helper.getMessage("Canceled", "Information"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                }
            }
        });
    }

    //Creating a Room table
    public void roomTableLoad() {

        model = (DefaultTableModel) tbl_rooms.getModel();

        model.setRowCount(0);

        // Adding data from the database to the Room table
        Object[] columns = {"Hotel ID", "ID", "Seasson", "Pansion Type", "Room Type", "Room Number", "Capacity", "Adult Price", "Child Price", "Stock", "Beds", "M2", "TV", "Mini Bar", "XBOX", "Safe Box", "Projector"};
        model.setColumnIdentifiers(columns);

        for (Room roomList : roomManager.findByAll()) {
            Object[] info = {roomList.getRoom_hotel_id(), roomList.getRoom_id(), seasonManager.getById(roomList.getRoom_seasson_id()).getSeasonName(), roomList.getRoom_pansion_type(), roomList.getRoomType(), roomList.getRoom_number(), roomList.getRoom_capacity(),
                    roomList.getRoom_adult_price(), roomList.getRoom_child_price(),
                    roomList.getRoom_stock_quantity(), roomList.getRoom_bed_count(), roomList.getRoom_square_meters(), roomList.isRoom_has_tv() ? "Yes" : "No", roomList.isRoom_has_mini_bar() ? "Yes" : "No",
                    roomList.isRoom_has_gaming_console() ? "Yes" : "No", roomList.isRoom_has_safe_box() ? "Yes" : "No", roomList.isRoom_has_projector() ? "Yes" : "No"};
            model.addRow(info);
        }
        //Table column width
        tbl_rooms.getColumnModel().getColumn(0).setPreferredWidth(50);
        tbl_rooms.getColumnModel().getColumn(1).setPreferredWidth(30);
        tbl_rooms.getColumnModel().getColumn(2).setPreferredWidth(74);
        tbl_rooms.getColumnModel().getColumn(3).setPreferredWidth(150);
        tbl_rooms.getColumnModel().getColumn(4).setPreferredWidth(150);
        tbl_rooms.getColumnModel().getColumn(5).setPreferredWidth(150);
        tbl_rooms.getColumnModel().getColumn(6).setPreferredWidth(90);
        tbl_rooms.getColumnModel().getColumn(7).setPreferredWidth(50);
        tbl_rooms.getColumnModel().getColumn(8).setPreferredWidth(60);
        tbl_rooms.getColumnModel().getColumn(9).setPreferredWidth(50);
        tbl_rooms.getColumnModel().getColumn(10).setPreferredWidth(50);
        tbl_rooms.getColumnModel().getColumn(11).setPreferredWidth(100);
        tbl_rooms.getColumnModel().getColumn(12).setPreferredWidth(80);
        tbl_rooms.getColumnModel().getColumn(13).setPreferredWidth(80);
        tbl_rooms.getColumnModel().getColumn(14).setPreferredWidth(80);
        tbl_rooms.getColumnModel().getColumn(15).setPreferredWidth(80);
        tbl_rooms.getColumnModel().getColumn(16).setPreferredWidth(80);


        // Makes the table uneditable and resizable
        tbl_rooms.setEnabled(false);
        tbl_rooms.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_rooms.getTableHeader().setResizingAllowed(false);
        tbl_rooms.getTableHeader().setReorderingAllowed(false);
    }

    // Room row selection
    public void roomEditButtons() {
        tbl_rooms.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectRow = tbl_rooms.rowAtPoint(e.getPoint());
                tbl_rooms.setRowSelectionInterval(selectRow, selectRow);
            }
        });

        //Room update
        btn_edit_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tbl_rooms.getSelectedRow() < 0) { // If it does not select a value from the table
                    Helper.getMessage("Please select a Room", "Error"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                } else {
                    int selectId = (int) tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 1);
                    System.out.println(selectId);
                    RoomEditView a = new RoomEditView(roomManager.getById(selectId));
                    a.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            roomTableLoad();
                        }
                    });
                }
            }
        });
        // Room ekleme
        btn_new_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomEditView a = new RoomEditView(new Room());
                a.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        roomTableLoad();
                    }
                });
            }
        });
        //  Cancel işlemi
        btn_back_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        // Room silme işlemi
        btn_delete_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectId = (int) tbl_rooms.getValueAt(tbl_rooms.getSelectedRow(), 1);
                if (tbl_rooms.getSelectedRow() < 0) { // If it does not select a value from the table
                    Helper.getMessage("Please select a Room", "Error"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                }
                if (JOptionPane.showConfirmDialog(null, "Deleted a Room", "Confirm", JOptionPane.YES_NO_OPTION) == 0) { // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                    roomManager.delete(selectId);
                    roomTableLoad();
                } else {
                    Helper.getMessage("Canceled", "Information"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                }
            }
        });
    }

    //Hotel search
    public void filterRoom() {
        // Select button
        btn_select_hotel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                model.setRowCount(0);

                ComboItem selectItem = (ComboItem) cmb_select_hotel.getSelectedItem();

                int hotelId = 0;
                if (selectItem != null) {
                    hotelId = selectItem.getKey();
                }
                ArrayList<Room> roomListTableSearch = roomManager.filterTable(hotelId);
                for (Room roomList : roomListTableSearch) {
                    Object[] info = {roomList.getRoom_hotel_id(), roomList.getRoom_id(), roomList.getSeason(), roomList.getRoom_pansion_type(), roomList.getRoomType(), roomList.getRoom_number(), roomList.getRoom_capacity(),
                            roomList.getRoom_adult_price(), roomList.getRoom_child_price(),
                            roomList.getRoom_stock_quantity(), roomList.getRoom_bed_count(), roomList.getRoom_square_meters(), roomList.isRoom_has_tv() ? "Yes" : "No", roomList.isRoom_has_mini_bar() ? "Yes" : "No",
                            roomList.isRoom_has_gaming_console() ? "Yes" : "No", roomList.isRoom_has_safe_box() ? "Yes" : "No", roomList.isRoom_has_projector() ? "Yes" : "No"};
                    model.addRow(info);
                }
            }
        });

        //List All button
        btn_list_all.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomTableLoad(); // Returns all room data
            }
        });

        //Starts the refresh process as soon as the tab is pressed
        tabbedPane1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                roomTableLoad();
            }
        });
    }

    //Adding combobox items for filtering operations - Hotel
    public void filterForHotel() {
        cmb_select_hotel.removeAllItems();
        for (Hotel hotel : hotelManager.findByAll()) {
            cmb_select_hotel.addItem(new ComboItem(hotel.getHotel_id(), hotel.getHotel_name()));
        }
    }

    //Creating a hostel table
    public void pansionsTableLoad() {
        model = (DefaultTableModel) tbl_pansions.getModel();

        model.setRowCount(0);

        Object[] columns = {"Pansion ID", "Pansion Type"};
        model.setColumnIdentifiers(columns);

        for (Pansion pansionList : pansionManager.findByAll()) {
            Object[] info = {pansionList.getPansion_id(), pansionList.getPansion_type()};
            model.addRow(info);
        }
        //Table column width
        tbl_rooms.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbl_rooms.getColumnModel().getColumn(1).setPreferredWidth(180);


        //Makes the table uneditable and resizable
        tbl_rooms.setEnabled(false);
        tbl_rooms.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_rooms.getTableHeader().setResizingAllowed(false);
        tbl_rooms.getTableHeader().setReorderingAllowed(false);
    }

    public void pansionsEditButton() {
        tbl_pansions.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectRow = tbl_pansions.rowAtPoint(e.getPoint());
                tbl_pansions.setRowSelectionInterval(selectRow, selectRow);
            }
        });

        //hostel update
        btn_edit_pansion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tbl_pansions.getSelectedRow() < 0) { // If it does not select a value from the table
                    Helper.getMessage("Please select a Pansion", "Error"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                } else {
                    int selectId = (int) tbl_pansions.getValueAt(tbl_pansions.getSelectedRow(), 0);
                    PansionEditView a = new PansionEditView(pansionManager.getById(selectId));
                    a.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            pansionsTableLoad();
                        }
                    });
                }
            }
        });
        // Pansion ekleme
        btn_new_pansion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PansionEditView a = new PansionEditView(new Pansion());
                a.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        pansionsTableLoad();
                    }
                });
            }
        });
        //  Cancel işlemi
        btn_back_pansion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        // Pansion silme işlemi
        btn_delete_pansion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectId = (int) tbl_pansions.getValueAt(tbl_pansions.getSelectedRow(), 0);
                if (JOptionPane.showConfirmDialog(null, "Deleted a Pansion", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                    pansionManager.delete(selectId);
                    pansionsTableLoad();
                } else {
                    Helper.getMessage("Canceled", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                }
            }
        });
    }

    // Creating a season table
    public void seasonTableLoad() {
        model = (DefaultTableModel) tbl_seasson.getModel();

        model.setRowCount(0);

        Object[] columns = {"ID", "Hotel Name", "Start-Date", "End-Date", "Season Name"};
        model.setColumnIdentifiers(columns);

        for (Season seasonList : seasonManager.findByAll()) {
            Object[] info = {seasonList.getSeason_id(), hotelManager.getById(seasonList.getSeason_hotel_id()).getHotel_name(), seasonList.getSeason_start_date(), seasonList.getSeason_end_date(), seasonList.getSeasonName()};
            model.addRow(info);
        }
        //Table column width
        tbl_seasson.getColumnModel().getColumn(0).setPreferredWidth(30);
        tbl_seasson.getColumnModel().getColumn(1).setPreferredWidth(100);
        tbl_seasson.getColumnModel().getColumn(2).setPreferredWidth(100);
        tbl_seasson.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl_seasson.getColumnModel().getColumn(4).setPreferredWidth(100);

        // Make table non-editable and non-resizable
        tbl_hotel.setEnabled(false);
        tbl_hotel.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tbl_hotel.getTableHeader().setResizingAllowed(false);
        tbl_hotel.getTableHeader().setReorderingAllowed(false);
    }

    public void seasonEditButtons() {
        tbl_seasson.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectRow = tbl_seasson.rowAtPoint(e.getPoint());
                tbl_seasson.setRowSelectionInterval(selectRow, selectRow);
            }
        });

        // Season Guncelleme
        btn_edit_season.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tbl_seasson.getSelectedRow() < 0) { // If it does not select a value from the table
                    Helper.getMessage("Please select a Hotel", "Error"); // Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                } else {
                    int selectId = (int) tbl_seasson.getValueAt(tbl_seasson.getSelectedRow(), 0);
                    SeasonEditView a = new SeasonEditView(seasonManager.getById(selectId));
                    a.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            seasonTableLoad();
                        }
                    });
                }
            }
        });
        // Season ekleme
        btn_new_season.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SeasonEditView a = new SeasonEditView(new Season());
                a.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        seasonTableLoad();
                    }
                });
            }
        });
        //  Cancel işlemi
        btn_back_season.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        // Season silme işlemi
        btn_delete_season.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectId = (int) tbl_seasson.getValueAt(tbl_seasson.getSelectedRow(), 0);
                if (JOptionPane.showConfirmDialog(null, "Deleted a Season", "Confirm", JOptionPane.YES_NO_OPTION) == 0) {// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                    seasonManager.delete(selectId);
                    seasonTableLoad();
                } else {
                    Helper.getMessage("Canceled", "Information");// Seciton 24-25 : The user is given appropriate pop up messages for successful transactions. Appropriate error messages are given to the user for incorrect operations.
                }
            }
        });
    }

    //Changing theme within the app
    public void getTheme() {
        // Tema isimlerini ComboBox'a ekleme
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            cmb_theme_list.addItem(info.getName());
        }

        //Changing theme when theme is selected from ComboBox
        cmb_theme_list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String selectedThemeName = cmb_theme_list.getSelectedItem().toString();
                    //Changing the theme based on the theme name selected from ComboBox
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if (selectedThemeName.equals(info.getName())) {
                            UIManager.setLookAndFeel(info.getClassName());
                            SwingUtilities.updateComponentTreeUI(container); // UI update
                            break;
                        }
                    }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}

