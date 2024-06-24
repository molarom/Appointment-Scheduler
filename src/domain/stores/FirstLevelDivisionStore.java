package domain.stores;


import domain.Country;
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
                "*" +
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

    /**
     * getById queries the database for the FirstLevelDivision by its Id.
     *
     * @param id the id to query.
     */
    public FirstLevelDivision getDivisionById(int id) {
        String query = "SELECT " +
                "* " +
                "FROM " +
                "first_level_divisions " +
                "WHERE " +
                "division_id = ?";
        Rows rows = db.PreparedQuery(query, id);
        FirstLevelDivision firstDivision = new FirstLevelDivision();
        return rows.get(0).Scan(firstDivision);
    }

    /**
     * getAllByCountryId returns all divisions associated with a given country.
     * Uses a lambda function to populate the returned list.
     *
     * @param country the country to search for divisions
     * @return the list of FirstLevelDivisions
     */
    public List<FirstLevelDivision> getAllByCountryId(Country country) {
        String query = "SELECT " +
                "*" +
                "FROM " +
                "first_level_divisions " +
                "WHERE country_id = ?";

        Rows rows = db.PreparedQuery(query, country.getCountryId());
        List<FirstLevelDivision> divisions = new ArrayList<>();
        rows.forEach(row -> {
            FirstLevelDivision division = new FirstLevelDivision();

            divisions.add(row.Scan(division));
        });
        return divisions;
    }
}
