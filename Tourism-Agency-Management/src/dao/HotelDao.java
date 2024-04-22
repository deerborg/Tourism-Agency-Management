package dao;

import core.Database;
import entity.Hotel;

import java.sql.*;
import java.util.ArrayList;

public class HotelDao {


    private Connection connection;
    private boolean isAdmin;
    public HotelDao() {
        connection = Database.connector(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
    }


    // Method to find all hotels
    public ArrayList<Hotel> findByAll(){
        ArrayList<Hotel> list = new ArrayList<>();
        String query = "select * from public.hotel order by hotel_id asc";

        try {
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(query);
            while (data.next()){
                list.add(match(data));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to map ResultSet data to Hotel object
    public Hotel match(ResultSet data) throws SQLException {
        Hotel hotel = new Hotel();

        hotel.setHotel_id(data.getInt("hotel_id"));
        hotel.setHotel_name(data.getString("hotel_name"));
        hotel.setHotel_city(data.getString("hotel_city"));
        hotel.setHotel_region(data.getString("hotel_region"));
        hotel.setHotel_fulladres(data.getString("hotel_fulladress"));
        hotel.setHotel_mail(data.getString("hotel_email"));
        hotel.setHotel_phone(data.getString("hotel_phone"));
        hotel.setHotel_star(data.getString("hotel_star"));
        hotel.setHotel_free_parking(data.getBoolean("hotel_free_parking"));
        hotel.setHotel_free_wifi(data.getBoolean("hotel_free_wifi"));
        hotel.setHotel_swimming_pool(data.getBoolean("hotel_swimming_pool"));
        hotel.setHotel_fitness_center(data.getBoolean("hotel_fitness_center"));
        hotel.setHotel_concierge(data.getBoolean("hotel_concierge"));
        hotel.setHotel_spa(data.getBoolean("hotel_spa"));
        hotel.setHotel_room_services(data.getBoolean("hotel_room_services"));

        // Convert hotel_pansion_type data
        Array pansionTypeArray = data.getArray("hotel_pansion_type");
        if (pansionTypeArray != null) {
            Object[] pansionTypeObjects = (Object[]) pansionTypeArray.getArray();
            String[] pansionTypes = new String[pansionTypeObjects.length];
            for (int i = 0; i < pansionTypeObjects.length; i++) {
                pansionTypes[i] = String.valueOf(pansionTypeObjects[i]);
            }
            hotel.setHotel_pansion_type(pansionTypes);
        }

        return hotel;

    }

    // Method to delete a hotel by ID
    public boolean delete(int id){
        String query = "delete from public.hotel where hotel_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to save a new hotel
    public boolean save(Hotel hotel){
        String query = "insert into public.hotel (hotel_name,hotel_city,hotel_region,hotel_fulladress," +
                "hotel_email,hotel_phone,hotel_star,hotel_free_parking,hotel_free_wifi,hotel_swimming_pool," +
                "hotel_fitness_center,hotel_concierge,hotel_spa,hotel_room_services,hotel_pansion_type) " +
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,hotel.getHotel_name());
            preparedStatement.setString(2,hotel.getHotel_city());
            preparedStatement.setString(3,hotel.getHotel_region());
            preparedStatement.setString(4,hotel.getHotel_fulladres());
            preparedStatement.setString(5,hotel.getHotel_mail());
            preparedStatement.setString(6,hotel.getHotel_phone());
            preparedStatement.setString(7,hotel.getHotel_star());
            preparedStatement.setBoolean(8,hotel.isHotel_free_parking());
            preparedStatement.setBoolean(9,hotel.isHotel_free_wifi());
            preparedStatement.setBoolean(10,hotel.isHotel_swimming_pool());
            preparedStatement.setBoolean(11,hotel.isHotel_fitness_center());
            preparedStatement.setBoolean(12,hotel.isHotel_concierge());
            preparedStatement.setBoolean(13,hotel.isHotel_spa());
            preparedStatement.setBoolean(14,hotel.isHotel_room_services());


            Array pansionTypeArray = connection.createArrayOf("text", hotel.getHotel_pansion_type());
            preparedStatement.setArray(15, pansionTypeArray);

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Method to update hotel information
    public boolean update(Hotel hotel) {
        String query = "update public.hotel set hotel_name = ?, hotel_city = ?, hotel_region = ?, " +
                "hotel_fulladress = ?, hotel_email = ?, hotel_phone = ?, hotel_star = ?, hotel_free_parking = ?," +
                "hotel_free_wifi = ?, hotel_swimming_pool = ?, hotel_fitness_center = ?, hotel_concierge = ?, " +
                "hotel_spa = ?, hotel_room_services = ?, hotel_pansion_type = ? where hotel_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, hotel.getHotel_name());
            preparedStatement.setString(2, hotel.getHotel_city());
            preparedStatement.setString(3, hotel.getHotel_region());
            preparedStatement.setString(4, hotel.getHotel_fulladres());
            preparedStatement.setString(5, hotel.getHotel_mail());
            preparedStatement.setString(6, hotel.getHotel_phone());
            preparedStatement.setString(7, hotel.getHotel_star());
            preparedStatement.setBoolean(8, hotel.isHotel_free_parking());
            preparedStatement.setBoolean(9, hotel.isHotel_free_wifi());
            preparedStatement.setBoolean(10, hotel.isHotel_swimming_pool());
            preparedStatement.setBoolean(11, hotel.isHotel_fitness_center());
            preparedStatement.setBoolean(12, hotel.isHotel_concierge());
            preparedStatement.setBoolean(13, hotel.isHotel_spa());
            preparedStatement.setBoolean(14, hotel.isHotel_room_services());
            // Convert hotel_pansion_type to appropriate format
            Array pansionTypeArray = connection.createArrayOf("text", hotel.getHotel_pansion_type());
            preparedStatement.setArray(15, pansionTypeArray);

            preparedStatement.setInt(16, hotel.getHotel_id());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    // Method to get a hotel by ID
    public Hotel getById(int id){
        String query = "select * from public.hotel where hotel_id = ?";
        Hotel hotel = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet data = preparedStatement.executeQuery();
            if(data.next()){
                hotel = match(data);
            }
            return hotel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get hotel ID by name
    public int getHotelIdByName (String hotelName){
        String query = "select hotel_id from public.hotel where hotel_name = ?";
        int hotel =0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,hotelName);
            ResultSet data = preparedStatement.executeQuery();
            if(data.next()){
                hotel = data.getInt("hotel_id");
            }
            return hotel;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
