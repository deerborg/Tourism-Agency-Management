package business;

import dao.ReservationDao;
import entity.Reservation;

import java.util.ArrayList;

public class ReservationManager {

    private final ReservationDao reservationDao;

    // Constructor
    public ReservationManager() {
        this.reservationDao = new ReservationDao(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
    }

    // Method to retrieve reservations by room ID
    public ArrayList<Reservation> getRoomId(int id){
        return reservationDao.getRoomId(id);
    }

    // Method to retrieve all reservations
    public ArrayList<Reservation> findByAll(){
        return reservationDao.findByAll();
    }

    // Method to delete a reservation by ID
    public boolean delete(int id){
        return reservationDao.delete(id);
    }

    // Method to save a new reservation
    public boolean save(Reservation reservation){
        return reservationDao.save(reservation);
    }

    // Method to update an existing reservation
    public boolean update(Reservation reservation){
        return reservationDao.update(reservation);
    }

    // Method to get a reservation by ID
    public Reservation getById(int id){
        return reservationDao.getById(id);
    }

    // Method to retrieve reservations by hotel ID
    public ArrayList<Reservation> getHotelId(int id){
        return reservationDao.getHotelId(id);
    }
}
