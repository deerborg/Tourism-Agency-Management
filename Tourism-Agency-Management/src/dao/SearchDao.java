package dao;

import core.Database;
import entity.Search;

import java.sql.*;
import java.util.ArrayList;

public class SearchDao {
    private Connection connection;

    public SearchDao() {
        connection = Database.connector(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
    }

    // Method to find all search results
    public ArrayList<Search> findByAll(){
        ArrayList<Search> list = new ArrayList<>();
        String query = "SELECT *\n" +
                "FROM public.hotel_room_season_table AS t1\n" +
                "WHERE room_id = (\n" +
                "    SELECT MIN(room_id)\n" +
                "    FROM public.hotel_room_season_table AS t2\n" +
                "    WHERE t1.season_id = t2.season_id\n" +
                ");";

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

    // Method to map ResultSet data to Search object
    public Search match(ResultSet data) throws SQLException {
        Search search = new Search();

        search.setRoom_seasson_id(data.getInt("room_season_id"));
        search.setRoom_id(data.getInt("room_id"));
        search.setHotel_id(data.getInt("hotel_id"));
        search.setHotel_name(data.getString("hotel_name"));
        search.setHotel_city(data.getString("hotel_city"));
        search.setSeason_start_date(data.getDate("season_start_date").toLocalDate());
        search.setSeason_end_date(data.getDate("season_end_date").toLocalDate());
        search.setRoom_pansion_type(data.getString("room_pansion_type"));
        search.setRoom_stock_quantity(data.getInt("room_stock_quantity"));
        search.setRoom_adult_price(data.getInt("room_adult_price"));
        search.setRoom_child_price(data.getInt("room_child_price"));
        return search;
    }

    // Method to select search results by a custom query
    public ArrayList<Search> selectByQuery(String query){
        ArrayList<Search> searches = new ArrayList<>();
        try {
            ResultSet data = connection.createStatement().executeQuery(query);
            while (data.next()){
                searches.add(match(data));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return searches;
    }

    // Method to get a search result by ID
    public Search getById(int id){
        String query = "select * from public.hotel_room_season_table where season_id = ?";
        Search search = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet data = preparedStatement.executeQuery();
            if(data.next()){
                search = match(data);
            }
            return search;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
