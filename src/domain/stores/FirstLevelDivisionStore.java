package domain.stores;


import domain.FirstLevelDivision;
import domain.database.SQL;
import domain.database.models.Rows;

import java.util.Collections;
import java.util.List;

/**
 * FirstLevelDivsionStore represents a data store for FirstLevelDivision objects.
 *
 * @author Brandon Epperson
 */
public class FirstLevelDivisionStore {
    private final SQL db;

    /**
     * Creates a new FirstLevelDivisionStore to retrieve FirstLevelDivision documents from the database.
     * @param db the SQL instance to use.
     */
    public FirstLevelDivisionStore(SQL db) {
        this.db = db;
    }

    /**
     * getAllDivisions retrieves all FirstLevelDivisions stored in the database
     * @return the list of FirstLevelDivisions
     */
    public List<FirstLevelDivision> getAllDivisions() {
       String query = "SELECT " +
               "division_id, " +
               "division " +
               "FROM " +
               "first_level_divisions";
       Rows rows = db.PreparedQuery(query);
       try {
           @SuppressWarnings("unchecked")
           List<FirstLevelDivision> scan = (List<FirstLevelDivision>) rows.Scan(FirstLevelDivision.class);
           return scan;
       }catch (Exception e) {
           e.printStackTrace();
           return Collections.emptyList();
       }
    }
}
