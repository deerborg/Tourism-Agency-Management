package dao;

import core.Database;
import entity.Season;

import java.sql.*;
import java.util.ArrayList;

public class SeasonDao {
    private Connection connection;

    public SeasonDao() {
        connection = Database.connector(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
    }

    // Method to find all seasons
    public ArrayList<Season> findByAll(){
        ArrayList<Season> list = new ArrayList<>();
        String query = "select * from public.season order by season_id asc";

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

    // Method to map ResultSet data to Season object
    public Season match(ResultSet data) throws SQLException {
        Season season = new Season();

        season.setSeason_id(data.getInt("season_id"));
        season.setSeason_hotel_id(data.getInt("season_hotel_id"));
        season.setSeason_start_date(data.getDate("season_start_date").toLocalDate());
        season.setSeason_end_date(data.getDate("season_end_date").toLocalDate());
        season.setSeasonName(Season.SeasonName.valueOf(data.getString("season_name")));
        season.setHotelName(data.getString("season_hotel_name"));
        return season;
    }

    // Method to delete a season by ID
    public boolean delete(int id){
        String query = "delete from public.season where season_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to save a new season
    public boolean save(Season season){
        String query = "insert into public.season (season_hotel_id,season_start_date,season_end_date," +
                "season_name,season_hotel_name) values (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,season.getSeason_hotel_id());
            preparedStatement.setDate(2,Date.valueOf(season.getSeason_start_date()));
            preparedStatement.setDate(3,Date.valueOf(season.getSeason_end_date()));
            preparedStatement.setString(4, String.valueOf(season.getSeasonName()));
            preparedStatement.setString(5, season.getHotelName());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to update an existing season
    public boolean update(Season season){
        String query = "update public.season set season_hotel_id = ? , season_start_date = ? , " +
                "season_end_date = ?, season_name = ?,season_hotel_name = ? where season_id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,season.getSeason_hotel_id());
            preparedStatement.setDate(2,Date.valueOf(season.getSeason_start_date()));
            preparedStatement.setDate(3,Date.valueOf(season.getSeason_end_date()));
            preparedStatement.setString(4, String.valueOf(season.getSeasonName()));
            preparedStatement.setString(5, season.getHotelName());
            preparedStatement.setInt(6,season.getSeason_id());

            return preparedStatement.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get a season by ID
    public Season getById(int id){
        String query = "select * from public.season where season_id = ?";
        Season season = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet data = preparedStatement.executeQuery();
            if(data.next()){
                season = match(data);
            }
            return season;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get the hotel name by its ID
    public Season getByHotel(int id){
        String query = "select public.hotel_name from public.hotel where hotel_id = ?";
        Season season = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet data = preparedStatement.executeQuery();
            if(data.next()){
                season = getHotelName(data);
            }
            return season;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to get only the hotel name
    public Season getHotelName(ResultSet data) throws SQLException {
        Season season = new Season();

        season.setHotelName(data.getString("hotel_name"));
        return season;
    }

    // Method to get the season ID by its name
    public int getSeasonIdBySeasonName (String seasonName){
        String query = "select season_id from public.season where season_name = ?";
        int season =0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,seasonName);
            ResultSet data = preparedStatement.executeQuery();
            if(data.next()){
                season = data.getInt("season_id");
            }
            return season;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    // Method to select seasons by a custom query
    public ArrayList<Season> selectByQuery(String query){
        ArrayList<Season> seasons = new ArrayList<>();
        try {
            ResultSet data = connection.createStatement().executeQuery(query);
            while (data.next()){
                seasons.add(match(data));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seasons;
    }

    // Method to get seasons by hotel ID
    public ArrayList<Season> getHotelId(int id){
        return selectByQuery("select * from public.season where season_hotel_id =" + id);
    }
}
