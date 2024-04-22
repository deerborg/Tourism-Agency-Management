package business;

import dao.PansionDao;
import entity.Pansion;

import java.util.ArrayList;

public class PansionManager {
    private final PansionDao pansionDao;

    // Constructor
    public PansionManager() {
        this.pansionDao = new PansionDao(); // Section - 6 : A database was used in the project and the DB connector configuration was made correctly.
    }

    // Method to retrieve pansion data for table display
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> rowList = new ArrayList<>();
        for(Pansion pansion : findByAll()){ // Iterate over all pansion options
            Object[] rows = new Object[size];
            int i = 0;
            // Add pansion details to the row list
            rowList.add(rows);
        }
        return rowList;
    }

    // Method to retrieve all pansion options
    public ArrayList<Pansion> findByAll(){
        return pansionDao.findByAll();
    }

    // Method to delete a pansion option by ID
    public boolean delete(int id){
        return pansionDao.delete(id);
    }

    // Method to save a new pansion option
    public boolean save(Pansion pansion){
        return pansionDao.save(pansion);
    }

    // Method to update an existing pansion option
    public boolean update(Pansion pansion){
        return pansionDao.update(pansion);
    }

    // Method to get a pansion option by ID
    public Pansion getById(int id){
        return pansionDao.getById(id);
    }

    // Method to find pansion options by hotel ID
    public Pansion[] findByHotelId(int hotelId) {
        return pansionDao.findByHotelId(hotelId);
    }
}
