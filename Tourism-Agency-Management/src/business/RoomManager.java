package business;

import dao.ReservationDao;
import dao.RoomDao;
import entity.Reservation;
import entity.Room;
import entity.Search;

import java.util.ArrayList;

public class RoomManager {

    private final RoomDao roomDao;
    private final ReservationManager reservationManager;

    // Constructor
    public RoomManager() {
        this.reservationManager = new ReservationManager();
        this.roomDao = new RoomDao(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
    }

    // Method to save a reservation for a room
    public boolean saveReservation(Room room){
        return roomDao.saveReservation(room);
    }

    // Method to retrieve room data for table display
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> list){
        ArrayList<Object[]> rowList = new ArrayList<>();
        for(Room obj : list){ // Iterate over all rooms
            Object[] rows = new Object[size];
            int i = 0;
            // Add room details to the row list
            rowList.add(rows);
        }
        return rowList;
    }

    // Method to filter rooms for table display based on hotel ID
    public ArrayList<Room> filterTable(int id){
        ArrayList<String> whereList = new ArrayList<>();
        String select = "select * from public.room";

        if(id != 0){
            whereList.add("room_hotel_id = " + id);
        }
        String whereStr = String.join(" and ",whereList);
        String query = select;
        if(whereStr.length() > 0){
            query += " where " + whereStr;
        }
        return roomDao.selectByQuery(query);
    }

    // Method to retrieve all rooms
    public ArrayList<Room> findByAll(){
        return roomDao.findByAll();
    }

    // Method to delete a room by ID
    public boolean delete(int id){
        // Delete associated reservations
        for(Reservation reservation : reservationManager.getRoomId(id)){
            reservationManager.delete(reservation.getReservation_id());
        }
        return roomDao.delete(id);
    }

    // Method to save a new room
    public boolean save(Room room){
        return roomDao.save(room);
    }

    // Method to update an existing room
    public boolean update(Room room){
        return roomDao.update(room);
    }

    // Method to get a room by ID
    public Room getById(int id){
        return roomDao.getById(id);
    }

    // Method to retrieve rooms by hotel ID
    public ArrayList<Room> getHotelId(int id){
        return roomDao.getHotelId(id);
    }

    // Method to retrieve rooms by season ID
    public ArrayList<Room> getSeasonId(int id){
        return roomDao.getSeasonId(id);
    }

    // Method to check room stock
    public boolean stockCheck(Room room){
        return roomDao.stockCheck(room);
    }

    // Method to filter search results based on various criteria
    public ArrayList<Room> filterTable(String search_hotel_city, String search_hotel_name, String selectedValueForInDateYear,
                                         String selectedValueForInDateMonth, String selectedValueForInDateDay,
                                         String selectedValueForOutDateYear, String selectedValueForOutDateMonth,
                                         String selectedValueForOutDateDay)  {
        ArrayList<Search> filteredResults = new ArrayList<>();

        // SQL query builder
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM public.room WHERE ");

        String checkInDate = selectedValueForInDateYear + "-" + selectedValueForInDateMonth + "-" + selectedValueForInDateDay;
        String checkOutDate =selectedValueForOutDateYear + "-" + selectedValueForOutDateMonth + "-" + selectedValueForOutDateDay;

        if (!search_hotel_city.equals("NULL")) {
            queryBuilder.append("room_hotel_city = '").append(search_hotel_city).append("' AND ");
        }

        if (!search_hotel_name.equals("NULL")) {
            queryBuilder.append("room_hotel_name = '").append(search_hotel_name).append("' AND ");
        }

        if (!selectedValueForInDateYear.equals("0") && !selectedValueForInDateMonth.equals("0") && !selectedValueForInDateDay.equals("0")) {
            queryBuilder.append("room_season_start <= '").append(checkInDate).append("' AND ");
        }

        if (!selectedValueForOutDateYear.equals("0") && !selectedValueForOutDateMonth.equals("0") && !selectedValueForOutDateDay.equals("0")) {
            queryBuilder.append("room_season_end >= '").append(checkOutDate).append("' AND ");
        }
        if(search_hotel_city.equals("NULL") &&
                search_hotel_name.equals("NULL") &&
                (selectedValueForInDateYear.equals("0") &&
                        (selectedValueForInDateMonth.equals("0") || selectedValueForInDateMonth.equals("00")) &&
                        (selectedValueForInDateDay.equals("0") || selectedValueForInDateDay.equals("00"))&&
                        selectedValueForOutDateYear.equals("0") &&
                        ( selectedValueForOutDateMonth.equals("0") || selectedValueForOutDateMonth.equals("00") )&&
                        (selectedValueForOutDateDay.equals("0") || selectedValueForOutDateDay.equals("00")))){
            return roomDao.selectByQuery("SELECT * FROM public.room");
        }

        // Remove the trailing "AND" statement
        String query = queryBuilder.toString();
        if (query.endsWith(" AND ")) {
            query = query.substring(0, query.length() - 5);
        }

        return roomDao.selectByQuery(query);
    }



}
