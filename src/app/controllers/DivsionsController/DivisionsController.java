package app.controllers.DivsionsController;

import domain.Country;
import domain.FirstLevelDivision;
import domain.database.SQL;
import domain.stores.FirstLevelDivisionStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class DivisionsController {
    private static Logger log = null;
    private static FirstLevelDivisionStore divisionStore;

    public static void Configure(SQL db, Logger logger) {
        divisionStore = new FirstLevelDivisionStore(db);
        log = logger;
    }

    public static ObservableList<FirstLevelDivision> GetAllDivisions() {
        List<FirstLevelDivision> divisions = divisionStore.getAllDivisions();
        if (!divisions.isEmpty()) {
            log.info("Total divisions found from getAllDivisions(): " + divisions.size());
            return FXCollections.observableArrayList(divisions);
        }
        log.warning("No divisions found from getAllDivisions()");
        return FXCollections.observableArrayList();
    }

    public static ObservableList<FirstLevelDivision> getAllByCountry(Country country) {
        List<FirstLevelDivision> divisionList = divisionStore.getAllByCountryId(country);
        if (!divisionList.isEmpty()) {
            log.info("Total divisions found from getAllByCountryId(): " + divisionList.size());
            return FXCollections.observableList(divisionList);
        }
        log.warning("No divisions found from getAllByCountryId(); Country name: " + country);
        return FXCollections.emptyObservableList();
    }

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
