package app.controllers.CountryController;

import domain.Country;
import domain.database.SQL;
import domain.stores.CountryStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class CountryController {
    private static Logger log = null;
    private static CountryStore countryStore = null;

    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db     the SQL instance to interact with.
     * @param logger the logger to use
     */
    public static void Configure(SQL db, Logger logger) {
        countryStore = new CountryStore(db);
        log = logger;
    }

    public static ObservableList<Country> GetAllCountries() {
        List<Country> countries = countryStore.getAll();
        if (!countries.isEmpty()) {
            log.info("Total countries returned from getAll(): " + countries.size());
            return FXCollections.observableList(countries);
        }
        log.warning("No countries returned from getAll()");
        return FXCollections.emptyObservableList();
    }

    public static Country getById(int id) {
        Country country = countryStore.getById(id);
        if (!Objects.equals(country.getCountryName(), "")) {
            log.info("Fetched country: " + country.getCountryName());
        }
        log.warning("Unable to find country with id: " + id);
        return country;
    }

}
