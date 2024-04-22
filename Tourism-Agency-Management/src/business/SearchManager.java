package business;

import dao.SearchDao;
import entity.Search;

import java.util.ArrayList;

public class SearchManager {

    private final SearchDao searchDao;

    // Constructor
    public SearchManager() {
        this.searchDao = new SearchDao(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
    }

    // Method to filter search results based on various criteria
    public ArrayList<Search> filterTable(String search_hotel_city, String search_hotel_name, String selectedValueForInDateYear,
                                         String selectedValueForInDateMonth, String selectedValueForInDateDay,
                                         String selectedValueForOutDateYear,String selectedValueForOutDateMonth,
                                         String selectedValueForOutDateDay)  {
        ArrayList<Search> filteredResults = new ArrayList<>();

        // SQL query builder
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM public.hotel_room_season_table WHERE ");

        String checkInDate = selectedValueForInDateYear + "-" + selectedValueForInDateMonth + "-" + selectedValueForInDateDay;
        String checkOutDate =selectedValueForOutDateYear + "-" + selectedValueForOutDateMonth + "-" + selectedValueForOutDateDay;

        if (!search_hotel_city.equals("NULL")) {
            queryBuilder.append("hotel_city = '").append(search_hotel_city).append("' AND ");
        }

        if (!search_hotel_name.equals("NULL")) {
            queryBuilder.append("hotel_name = '").append(search_hotel_name).append("' AND ");
        }

        if (!selectedValueForInDateYear.equals("0") && !selectedValueForInDateMonth.equals("0") && !selectedValueForInDateDay.equals("0")) {
            queryBuilder.append("season_start_date <= '").append(checkInDate).append("' AND ");
        }

        if (!selectedValueForOutDateYear.equals("0") && !selectedValueForOutDateMonth.equals("0") && !selectedValueForOutDateDay.equals("0")) {
            queryBuilder.append("season_end_date >= '").append(checkOutDate).append("' AND ");
        }
        if(search_hotel_city.equals("NULL") &&
                search_hotel_name.equals("NULL") &&
                (selectedValueForInDateYear.equals("0") &&
                        (selectedValueForInDateMonth.equals("0") || selectedValueForInDateMonth.equals("00")) &&
                        (selectedValueForInDateDay.equals("0") || selectedValueForInDateDay.equals("00"))&&
                        selectedValueForOutDateYear.equals("0") &&
                        ( selectedValueForOutDateMonth.equals("0") || selectedValueForOutDateMonth.equals("00") )&&
                        (selectedValueForOutDateDay.equals("0") || selectedValueForOutDateDay.equals("00")))){
            return searchDao.selectByQuery("SELECT * FROM public.hotel_room_season_table");
        }

        // Remove the trailing "AND" statement
        String query = queryBuilder.toString();
        if (query.endsWith(" AND ")) {
            query = query.substring(0, query.length() - 5);
        }

        return searchDao.selectByQuery(query);
    }

    // Method to retrieve search results for table display
    public ArrayList<Object[]> getForTable(int size, ArrayList<Search> list){
        ArrayList<Object[]> rowList = new ArrayList<>();
        for(Search obj : list){ // Iterate over all search results
            Object[] rows = new Object[size];
            int i = 0;
            // Add search result details to the row list
            rowList.add(rows);
        }
        return rowList;
    }

    // Method to retrieve all search results
    public ArrayList<Search> findByAll(){
        return searchDao.findByAll();
    }

    // Method to get a search result by ID
    public Search getById(int id){
        return searchDao.getById(id);
    }

}
