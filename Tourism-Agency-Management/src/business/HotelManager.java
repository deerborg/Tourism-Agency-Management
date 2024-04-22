package business;

import dao.HotelDao;
import entity.Hotel;
import entity.Reservation;
import entity.Room;
import entity.Season;

import java.util.ArrayList;

public class HotelManager {

    private final HotelDao hotelDao;
    private RoomManager roomManager;
    private SeasonManager seasonManager;
    private ReservationManager reservationManager;

    // Constructor
    public HotelManager() {
        this.hotelDao = new HotelDao(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
        reservationManager = new ReservationManager();
        roomManager = new RoomManager();
        seasonManager = new SeasonManager();
    }

    // Method to retrieve hotel data for table display
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> rowList = new ArrayList<>();
        for(Hotel hotel : findByAll()){ // Iterate over all hotels
            Object[] rows = new Object[size];
            int i = 0;
            rows[i++] = hotel.getHotel_id();
            rows[i++] = hotel.getHotel_name();
            rows[i++] = hotel.getHotel_city();
            rows[i++] = hotel.getHotel_region();
            rows[i++] = hotel.getHotel_fulladres();
            rows[i++] = hotel.getHotel_mail();
            rows[i++] = hotel.getHotel_phone();
            rows[i++] = hotel.getHotel_star();
            rows[i++] = hotel.isHotel_free_parking();
            rows[i++] = hotel.isHotel_free_wifi();
            rows[i++] = hotel.isHotel_swimming_pool();
            rows[i++] = hotel.isHotel_fitness_center();
            rows[i++] = hotel.isHotel_concierge();
            rows[i++] = hotel.isHotel_spa();
            rows[i++] = hotel.isHotel_room_services();
            rows[i++] = hotel.getHotel_pansion_type();
            rowList.add(rows);
        }
        return rowList;
    }


    // Method to retrieve all hotels
    public ArrayList<Hotel> findByAll(){
        return hotelDao.findByAll();
    }

    // Method to delete a hotel by ID
    public boolean delete(int id){
        // Delete associated rooms, seasons, and reservations before deleting the hotel
        for(Room room : roomManager.getHotelId(id)){
            roomManager.delete(room.getRoom_id());
        }
        for(Season season : seasonManager.getHotelId(id)){
            seasonManager.delete(season.getSeason_id());
        }
        for(Reservation reservation : reservationManager.getHotelId(id)){
            reservationManager.delete(reservation.getReservation_id());
        }
        return hotelDao.delete(id);
    }

    // Method to save a new hotel
    public boolean save(Hotel hotel){
        return hotelDao.save(hotel);
    }

    // Method to update an existing hotel
    public boolean update(Hotel hotel){
        return hotelDao.update(hotel);
    }

    // Method to get a hotel by ID
    public Hotel getById(int id){
        return hotelDao.getById(id);
    }

    // Method to get the ID of a hotel by its name
    public int getHotelIdByName(String hotelName){
        return hotelDao.getHotelIdByName(hotelName);
    }
}
