package dao;

import core.Database;
import entity.Pansion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PansionDao {
    private Connection connection;
    private boolean isAdmin;
    public PansionDao() {
        connection = Database.connector(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
    }

    // Method to find all pansions
    public ArrayList<Pansion> findByAll(){
        ArrayList<Pansion> pansionList = new ArrayList<>();
        String query = "select * from public.pansion order by pansion_id asc";

        try {
            Statement statement = connection.createStatement();
            ResultSet data = statement.executeQuery(query);
            while (data.next()){
                pansionList.add(match(data));
            }
            return pansionList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to map ResultSet data to Pansion object
    public Pansion match(ResultSet data) throws SQLException {
        Pansion pansion = new Pansion();

        pansion.setPansion_id(data.getInt("pansion_id"));
        pansion.setPansion_type(data.getString("pansion_type"));

        return pansion;

    }

    // Method to find pansions by hotel ID
    public Pansion[] findByHotelId(int hotelId) {
        List<Pansion> pansionList = new ArrayList<>();
        String query = "SELECT * FROM public.pansion WHERE pansion_hotel_id = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, hotelId);
            ResultSet data = statement.executeQuery();
            while (data.next()) {
                pansionList.add(match(data));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pansionList.toArray(new Pansion[0]);
    }


    // Method to delete a pansion by ID
    public boolean delete(int id){
        String query = "delete from public.pansion where pansion_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to save a new pansion
    public boolean save(Pansion pansion){
        String query = "insert into public.pansion (pansion_type) values (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,pansion.getPansion_type());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to update pansion information
    public boolean update(Pansion pansion){
        String query = "update public.pansion set pansion_type = ?  where pansion_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,pansion.getPansion_type());
            preparedStatement.setInt(2,pansion.getPansion_id());
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get a pansion by ID
    public Pansion getById(int id){
        String query = "select * from public.pansion where pansion_id = ?";
        Pansion pansion = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet data = preparedStatement.executeQuery();
            if(data.next()){
                pansion = match(data);
            }
            return pansion;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to select pansions by a custom query
    public ArrayList<Pansion> selectByQuery(String query){
        ArrayList<Pansion> pansions = new ArrayList<>();

        try {
            ResultSet data = connection.createStatement().executeQuery(query);
            while (data.next()){
                pansions.add(match(data));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pansions;
    }
}
