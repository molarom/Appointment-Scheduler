package domain.stores;


import domain.FirstLevelDivision;
import domain.database.SQL;
import domain.database.models.Rows;

import java.util.ArrayList;
import java.util.List;

/**
 * FirstLevelDivisionStore represents a data store for FirstLevelDivision objects.
 *
 * @author Brandon Epperson
 */
public class FirstLevelDivisionStore {
    private final SQL db;

    /**
     * Creates a new FirstLevelDivisionStore to retrieve FirstLevelDivision documents from the database.
     *
     * @param db the SQL instance to use.
     */
    public FirstLevelDivisionStore(SQL db) {
        this.db = db;
    }

    /**
     * getAllDivisions retrieves all FirstLevelDivisions stored in the database.
     * Uses a lambda function to populate the returned list.
     *
     * @return the list of FirstLevelDivisions
     */
    public List<FirstLevelDivision> getAllDivisions() {
        String query = "SELECT " +
                "division_id, " +
                "division " +
                "FROM " +
                "first_level_divisions";
        Rows rows = db.PreparedQuery(query);
        List<FirstLevelDivision> divisions = new ArrayList<>();
        rows.forEach(row -> {
            FirstLevelDivision division = new FirstLevelDivision();
            divisions.add(row.Scan(division));
        });
        return divisions;
    }
}
