package dao;

import core.Database;
import entity.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    private Connection connection;
    private boolean isAdmin;

    public UserDao() {
        connection = Database.connector(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
    }

    // Method to authenticate user login
    public User login(String username,String password){
        User user = null;
        String query = "select * from public.users where user_name = ?  and user_pass = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            ResultSet data = preparedStatement.executeQuery();
            if(data.next()){
                user = match(data);
                System.out.println("Information correct");
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to find all users
    public ArrayList<User> findByAll(){
        ArrayList<User> list = new ArrayList<>();
        String query = "select * from public.users order by user_id asc";

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

    // Method to map ResultSet data to User object
    public User match(ResultSet data) throws SQLException {
        User user = new User();

        user.setUser_id(data.getInt("user_id"));
        user.setUser_name(data.getString("user_name"));
        user.setUser_pass(data.getString("user_pass"));
        user.setPerm(User.Perm.valueOf(data.getString("user_perm")));

        return user;
    }

    // Method to delete a user by ID
    public boolean delete(int id){
        String query = "delete from public.users where user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to save a new user
    public boolean save(User user){
        String query = "insert into public.users (user_name,user_pass,user_perm) values (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user.getUser_name());
            preparedStatement.setString(2,user.getUser_pass());
            preparedStatement.setString(3,user.getPerm().toString());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to update an existing user
    public boolean update(User user){
        String query = "update public.users set user_name = ? , user_pass = ? , user_perm = ? where user_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,user.getUser_name());
            preparedStatement.setString(2,user.getUser_pass());
            preparedStatement.setString(3,user.getPerm().toString());
            preparedStatement.setInt(4,user.getUser_id());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get a user by ID
    public User getById(int id){
        String query = "select * from public.users where user_id = ?";
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet data = preparedStatement.executeQuery();
            if(data.next()){
                user = match(data);
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
