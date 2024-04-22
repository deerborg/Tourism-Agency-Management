package dao;

import core.Database;
import entity.Pansion;
import entity.Reservation;
import entity.Room;

import java.sql.*;
import java.util.ArrayList;

public class ReservationDao {
    private Connection connection;
    private boolean isAdmin;
    public ReservationDao() {
        connection = Database.connector(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
    }

    // Method to find all reservations
    public ArrayList<Reservation> findByAll(){
        ArrayList<Reservation> reservationsList = new ArrayList<>();
        String query = "select * from public.reservation order by reservation_id asc";

        try {
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(query);
            while (data.next()){
                reservationsList.add(match(data));
            }
            return reservationsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to map ResultSet data to Reservation object
    public Reservation match(ResultSet data) throws SQLException {
        Reservation reservation = new Reservation();

        reservation.setReservation_id(data.getInt("reservation_id"));
        reservation.setReservation_hotel_id(data.getInt("reservation_hotel_id"));
        reservation.setReservation_room_id(data.getInt("reservation_room_id"));
        reservation.setReservation_guest_fullname(data.getString("reservation_guest_fullname"));
        reservation.setReservation_guest_id(data.getString("reservation_guest_id"));
        reservation.setReservation_guest_mpno(data.getString("reservation_guest_mpno"));
        reservation.setReservation_guest_email(data.getString("reservation_guest_email"));
        reservation.setReservation_checkin_date(data.getDate("reservation_checkin_date").toLocalDate());
        reservation.setReservation_checkout_date(data.getDate("reservation_checkout_date").toLocalDate());
        reservation.setReservation_child_count(data.getInt("reservation_child_count"));
        reservation.setReservation_adult_count(data.getInt("reservation_adult_count"));
        reservation.setReservation_totol_price(data.getInt("reservation_total_price"));
        reservation.setReservation_date(data.getDate("reservation_date").toLocalDate());

        return reservation;

    }

    // Method to delete a reservation by ID
    public boolean delete(int id){
        String query = "delete from public.reservation where reservation_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to save a new reservation
    public boolean save(Reservation reservation){
        String query = "insert into public.reservation " +
                "(reservation_hotel_id,reservation_room_id,reservation_guest_fullname," +
                "reservation_guest_id,reservation_guest_mpno,reservation_guest_email," +
                "reservation_checkin_date,reservation_checkout_date,reservation_child_count," +
                "reservation_adult_count,reservation_total_price,reservation_date)" +
                " values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,reservation.getReservation_hotel_id());
            preparedStatement.setInt(2,reservation.getReservation_room_id());
            preparedStatement.setString(3,reservation.getReservation_guest_fullname());
            preparedStatement.setString(4,reservation.getReservation_guest_id());
            preparedStatement.setString(5,reservation.getReservation_guest_mpno());
            preparedStatement.setString(6,reservation.getReservation_guest_email());
            preparedStatement.setDate(7,Date.valueOf(reservation.getReservation_checkin_date()));
            preparedStatement.setDate(8,Date.valueOf(reservation.getReservation_checkout_date()));
            preparedStatement.setInt(9,reservation.getReservation_child_count());
            preparedStatement.setInt(10,reservation.getReservation_adult_count());
            preparedStatement.setInt(11,reservation.getReservation_totol_price());
            preparedStatement.setDate(12,Date.valueOf(reservation.getReservation_date()));

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to update reservation information
    public boolean update(Reservation reservation){
        String query = "update public.reservation set " +
                "reservation_hotel_id = ? , reservation_room_id = ? ,reservation_guest_fullname = ?, reservation_guest_id = ?, " +
                "reservation_guest_mpno = ? , reservation_guest_email = ?, reservation_checkin_date = ? ,reservation_checkout_date = ? ," +
                "reservation_child_count = ?,reservation_adult_count = ?,reservation_total_price = ?,reservation_date = ? " +
                "where reservation_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,reservation.getReservation_hotel_id());
            preparedStatement.setInt(2,reservation.getReservation_room_id());
            preparedStatement.setString(3,reservation.getReservation_guest_fullname());
            preparedStatement.setString(4,reservation.getReservation_guest_id());
            preparedStatement.setString(5,reservation.getReservation_guest_mpno());
            preparedStatement.setString(6,reservation.getReservation_guest_email());
            preparedStatement.setDate(7,Date.valueOf(reservation.getReservation_checkin_date()));
            preparedStatement.setDate(8,Date.valueOf(reservation.getReservation_checkout_date()));
            preparedStatement.setInt(9,reservation.getReservation_child_count());
            preparedStatement.setInt(10,reservation.getReservation_adult_count());
            preparedStatement.setInt(11,reservation.getReservation_totol_price());
            preparedStatement.setDate(12,Date.valueOf(reservation.getReservation_date()));
            preparedStatement.setInt(13,reservation.getReservation_id());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get a reservation by ID
    public Reservation getById(int id){
        String query = "select * from public.reservation where reservation_id = ?";
        Reservation reservation = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet data = preparedStatement.executeQuery();
            if(data.next()){
                reservation = match(data);
            }
            return reservation;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get reservations by hotel ID
    public ArrayList<Reservation> getHotelId(int id){
        return selectByQuery("select * from public.reservation where reservation_hotel_id =" + id);
    }

    // Method to get reservations by room ID
    public ArrayList<Reservation> getRoomId(int id){
        return selectByQuery("select * from public.reservation where reservation_room_id =" + id);
    }

    // Method to select reservations by a custom query
    public ArrayList<Reservation> selectByQuery(String query){
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ResultSet data = connection.createStatement().executeQuery(query);
            while (data.next()){
                reservations.add(match(data));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservations;
    }
}
