package app.controllers;

import domain.database.SQL;
import domain.stores.Country.Country;
import domain.stores.Country.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * CountryController contains methods for the UI layer to interact with the database.
 *
 * @author Brandon Epperson
 */
public class CountryController {
    private static Logger log = null;
    private static Store store = null;

    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db     the SQL instance to interact with.
     * @param logger the logger to use
     */
    static void Configure(SQL db, Logger logger) {
        store = new Store(db);
        log = logger;
    }

    /**
     * @return an ObservableList of Country
     */
    public static ObservableList<Country> getAllCountries() {
        List<Country> countries = store.getAll();
        if (!countries.isEmpty()) {
            log.info("Total countries returned from getAll(): " + countries.size());
            return FXCollections.observableList(countries);
        }
        log.warning("No countries returned from getAll()");
        return FXCollections.emptyObservableList();
    }

    /**
     * getById attempt to fetch a Country from the store
     * @param id the id to search for
     * @return the Country if found
     */
    public static Country getById(int id) {
        Country country = store.getById(id);
        if (!Objects.equals(country.getCountryName(), "")) {
            log.info("Fetched country: " + country.getCountryName());
        }
        log.warning("Unable to find country with id: " + id);
        return country;
    }

}
