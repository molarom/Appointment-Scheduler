package app.controllers;

import domain.database.SQL;
import domain.stores.Country.Country;
import domain.stores.FirstLevelDivision.FirstLevelDivision;
import domain.stores.FirstLevelDivision.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * DivisionsController contains methods for the UI to interact with the database.
 *
 * @author Brandon Epperson
 */
public class DivisionsController {
    private static Logger log = null;
    private static Store divisionStore;

    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db     the SQL instance to interact with.
     * @param logger the logger to use
     */
    static void Configure(SQL db, Logger logger) {
        divisionStore = new Store(db);
        log = logger;
    }

    /**
     * getAllDivisions fetches all divisions from the store
     * @return Observable list of FirstLevelDivision
     */
    public static ObservableList<FirstLevelDivision> GetAllDivisions() {
        List<FirstLevelDivision> divisions = divisionStore.getAllDivisions();
        if (!divisions.isEmpty()) {
            log.info("Total divisions found from getAllDivisions(): " + divisions.size());
            return FXCollections.observableArrayList(divisions);
        }
        log.warning("No divisions found from getAllDivisions()");
        return FXCollections.observableArrayList();
    }

    /**
     * fetches divisions by country from the store.
     * @param country the country to search for
     * @return observable list of country
     */
    public static ObservableList<FirstLevelDivision> getAllByCountry(Country country) {
        List<FirstLevelDivision> divisionList = divisionStore.getAllByCountryId(country);
        if (!divisionList.isEmpty()) {
            log.info("Total divisions found from getAllByCountryId(): " + divisionList.size());
            return FXCollections.observableList(divisionList);
        }
        log.warning("No divisions found from getAllByCountryId(); Country name: " + country);
        return FXCollections.emptyObservableList();
    }

    /**
     * attempts to fetch a division by id from the store
     * @param id the id to search for
     * @return the division if found
     */
    public static FirstLevelDivision getDivisionById(int id) {
        FirstLevelDivision division = divisionStore.getDivisionById(id);
        if (!Objects.equals(division.getDivisionName(), "")) {
            log.info("Found division from getDivisionById(): " + division.getDivisionName());
            return division;
        }
        log.warning("No division found from getDivisionById(); Division Id: " + id);
        return division;
    }
}
