package business;

import dao.SeasonDao;
import entity.Room;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {

    private final SeasonDao seasonDao;
    private final RoomManager roomManager;

    // Constructor
    public SeasonManager() {
        this.seasonDao = new SeasonDao(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
        this.roomManager = new RoomManager();
    }

    // Method to get season data for table display
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> list){
        ArrayList<Object[]> rowList = new ArrayList<>();
        for(Season obj : list){ // Iterate over all seasons
            Object[] rows = new Object[size];
            int i = 0;
            // Add season details to the row list
            rows[i++] = obj.getSeason_id();
            rows[i++] = obj.getSeason_hotel_id();
            rows[i++] = obj.getSeason_start_date();
            rows[i++] = obj.getSeason_end_date();
            rows[i++] = obj.getSeasonName();
            rowList.add(rows);
        }
        return rowList;
    }

    // Method to retrieve all seasons
    public ArrayList<Season> findByAll(){
        return seasonDao.findByAll();
    }

    // Method to delete a season by ID
    public boolean delete(int id){
        // Delete associated rooms
        for (Room room : roomManager.getSeasonId(id)){
            roomManager.delete(room.getRoom_id());
        }
        return seasonDao.delete(id);
    }

    // Method to save a season
    public boolean save(Season season){
        return seasonDao.save(season);
    }

    // Method to update a season
    public boolean update(Season season){
        return seasonDao.update(season);
    }

    // Method to get a season by ID
    public Season getById(int id){
        return seasonDao.getById(id);
    }

    // Method to get the season ID by season name
    public int getSeasonIdBySeasonName(String seasonName){
        return seasonDao.getSeasonIdBySeasonName(seasonName);
    }

    // Method to get seasons by hotel ID
    public ArrayList<Season> getHotelId(int id){
        return seasonDao.getHotelId(id);
    }
}
