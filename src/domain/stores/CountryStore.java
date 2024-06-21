package domain.stores;

import domain.Country;
import domain.database.SQL;
import domain.database.models.Rows;

import java.util.ArrayList;
import java.util.List;

public class CountryStore {
    private final SQL db;

    /**
     * Creates a new CountryStore to retrieve Country documents from the database.
     *
     * @param db the SQL instance to use.
     */
    public CountryStore(SQL db) {
        this.db = db;
    }

    /**
     * @param id the country_id to search for
     * @return the populated Country object, empty if not found.
     */
    public Country getById(int id) {
        String query = "SELECT " +
                "* " +
                "FROM countries " +
                "WHERE country_id = ? " +
                "LIMIT 1";

        Rows rows = db.PreparedQuery(query, id);
        Country country = new Country();
        return rows.get(0).Scan(country);
    }

    /**
     * @param name the country to search for
     * @return the populated Country object, empty if not found.
     */
    public Country getByName(String name) {
        String query = "SELECT " +
                "* " +
                "FROM countries " +
                "WHERE country = ? " +
                "LIMIT 1";

        Rows rows = db.PreparedQuery(query, name);
        Country country = new Country();
        return rows.get(0).Scan(country);
    }

    /**
     * getAll attempts to fetch all Countries stored in the database.
     * Uses a lambda to scan each row and populate the list.
     *
     * @return a list of all countries
     */
    public List<Country> getAll() {
        String query = "SELECT " +
                "* " +
                "FROM countries " +
                "ORDER BY country_id";

        Rows rows = db.PreparedQuery(query);
        List<Country> countries = new ArrayList<>();
        rows.forEach(row -> {
            Country country = new Country();
            countries.add(row.Scan(country));
        });
        return countries;
    }
}
