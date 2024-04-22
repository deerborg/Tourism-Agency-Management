package dao;

import core.Database;
import entity.Pansion;
import entity.Reservation;
import entity.Room;

import java.sql.*;
import java.util.ArrayList;

public class RoomDao {
    private Connection connection;
    private boolean isAdmin;
    public RoomDao() {
        connection = Database.connector(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
    }

    // Method to find all rooms
    public ArrayList<Room> findByAll(){
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "select * from public.room order by room_id asc";

        try {
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(query);
            while (data.next()){
                roomList.add(match(data));
            }
            return roomList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to map ResultSet data to Room object
    public Room match(ResultSet data) throws SQLException {
        Room room = new Room();

        room.setRoom_id(data.getInt("room_id"));
        room.setRoom_hotel_id(data.getInt("room_hotel_id"));
        room.setRoom_seasson_id(data.getInt("room_season_id"));
        room.setRoom_pansion_type(data.getString("room_pansion_type"));
        room.setRoomType(Room.Type.valueOf(data.getString("room_type")));
        room.setRoom_number(data.getString("room_number"));
        room.setRoom_capacity(data.getInt("room_capacity"));
        room.setRoom_adult_price(data.getInt("room_adult_price"));
        room.setRoom_child_price(data.getInt("room_child_price"));
        room.setRoom_stock_quantity(data.getInt("room_stock_quantity"));
        room.setRoom_bed_count(data.getInt("room_bed_count"));
        room.setRoom_square_meters(data.getInt("room_square_meters"));
        room.setRoom_has_tv(data.getBoolean("room_has_tv"));
        room.setRoom_has_mini_bar(data.getBoolean("room_has_mini_bar"));
        room.setRoom_has_gaming_console(data.getBoolean("room_has_gaming_console"));
        room.setRoom_has_safe_box(data.getBoolean("room_has_safe_box"));
        room.setRoom_has_projector(data.getBoolean("room_has_projector"));
        room.setRoom_season_name(data.getString("room_season_name"));
        room.setRoom_season_start(data.getDate("room_season_start").toLocalDate());
        room.setRoom_season_end(data.getDate("room_season_end").toLocalDate());
        room.setRoom_hotel_name(data.getString("room_hotel_name"));
        room.setRoom_hotel_city(data.getString("room_hotel_city"));

        return room;

    }

    // Method to delete a room by ID
    public boolean delete(int id){
        String query = "delete from public.room where room_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to save a new room
    public boolean save(Room room){
        String query = "insert into public.room " +
                "(room_hotel_id,room_season_id," +
                "room_pansion_type,room_type,room_number," +
                "room_capacity,room_adult_price,room_child_price," +
                "room_stock_quantity,room_bed_count,room_square_meters" +
                ",room_has_tv,room_has_mini_bar," +
                "room_has_gaming_console,room_has_safe_box,room_has_projector,room_hotel_name,room_hotel_city,room_season_start,room_season_end,room_season_name)" +
                " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,room.getRoom_hotel_id());
            preparedStatement.setInt(2,room.getRoom_seasson_id());
            preparedStatement.setString(3,room.getRoom_pansion_type());
            preparedStatement.setString(4, String.valueOf(room.getRoomType()));
            preparedStatement.setString(5,room.getRoom_number());
            preparedStatement.setInt(6,room.getRoom_capacity());
            preparedStatement.setInt(7,room.getRoom_adult_price());
            preparedStatement.setInt(8,room.getRoom_child_price());
            preparedStatement.setInt(9,room.getRoom_stock_quantity());
            preparedStatement.setInt(10,room.getRoom_bed_count());
            preparedStatement.setInt(11,room.getRoom_square_meters());
            preparedStatement.setBoolean(12,room.isRoom_has_tv());
            preparedStatement.setBoolean(13,room.isRoom_has_mini_bar());
            preparedStatement.setBoolean(14,room.isRoom_has_gaming_console());
            preparedStatement.setBoolean(15,room.isRoom_has_safe_box());
            preparedStatement.setBoolean(16,room.isRoom_has_projector());
            preparedStatement.setString(17,room.getRoom_hotel_name());
            preparedStatement.setString(18,room.getRoom_hotel_city());
            preparedStatement.setDate(19, Date.valueOf(room.getRoom_season_start()));
            preparedStatement.setDate(20, Date.valueOf(room.getRoom_season_end()));
            preparedStatement.setString(21,room.getRoom_season_name());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to save a reservation for a room
    public boolean saveReservation(Room room){
        String query = "update public.room set room_reservation_id = ? where room_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,room.getRoom_reservation_id());
            preparedStatement.setInt(2,room.getRoom_id());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to update room information
    public boolean update(Room room){
        String query = "update public.room set " +
                "room_hotel_id = ? , room_season_id = ? ,room_pansion_type = ?, room_type = ?, " +
                "room_number = ? , room_capacity = ?, room_adult_price = ? ,room_child_price = ? ," +
                "room_stock_quantity = ?,room_bed_count = ?,room_square_meters = ?,room_has_tv = ?," +
                " room_has_mini_bar = ?,room_has_gaming_console = ?,room_has_safe_box = ?,room_has_projector = ? , room_hotel_city = ? , room_hotel_name = ? , room_season_start = ? , room_season_end = ?,room_season_name = ? " +
                "where room_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,room.getRoom_hotel_id());
            preparedStatement.setInt(2,room.getRoom_seasson_id());
            preparedStatement.setString(3,room.getRoom_pansion_type());
            preparedStatement.setString(4, String.valueOf(room.getRoomType()));
            preparedStatement.setString(5,room.getRoom_number());
            preparedStatement.setInt(6,room.getRoom_capacity());
            preparedStatement.setInt(7,room.getRoom_adult_price());
            preparedStatement.setInt(8,room.getRoom_child_price());
            preparedStatement.setInt(9,room.getRoom_stock_quantity());
            preparedStatement.setInt(10,room.getRoom_bed_count());
            preparedStatement.setInt(11,room.getRoom_square_meters());
            preparedStatement.setBoolean(12,room.isRoom_has_tv());
            preparedStatement.setBoolean(13,room.isRoom_has_mini_bar());
            preparedStatement.setBoolean(14,room.isRoom_has_gaming_console());
            preparedStatement.setBoolean(15,room.isRoom_has_safe_box());
            preparedStatement.setBoolean(16,room.isRoom_has_projector());
            preparedStatement.setString(18,room.getRoom_hotel_name());
            preparedStatement.setString(17,room.getRoom_hotel_city());
            preparedStatement.setDate(19, Date.valueOf(room.getRoom_season_start()));
            preparedStatement.setDate(20, Date.valueOf(room.getRoom_season_end()));
            preparedStatement.setString(21,room.getRoom_season_name());
            preparedStatement.setInt(22,room.getRoom_id());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get a room by ID
    public Room getById(int id){
        String query = "select * from public.room where room_id = ?";
        Room room = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet data = preparedStatement.executeQuery();
            if(data.next()){
                room = match(data);
            }
            return room;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to select rooms by a custom query
    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet data = connection.createStatement().executeQuery(query);
            while (data.next()){
                rooms.add(match(data));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rooms;
    }

    // Method to get rooms by hotel ID
    public ArrayList<Room> getHotelId(int id){
        return selectByQuery("select * from public.room where room_hotel_id =" + id);
    }

    // Method to get rooms by season ID
    public ArrayList<Room> getSeasonId(int id){
        return selectByQuery("select * from public.room where room_season_id =" + id);
    }

    // Method to check room stock quantity
    public boolean stockCheck(Room room){
        String query = "update public.room set room_stock_quantity = ? where room_id = ?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,room.getRoom_stock_quantity());
            preparedStatement.setInt(2,room.getRoom_id());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
