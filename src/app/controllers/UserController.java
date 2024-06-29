package app.controllers;

import domain.database.SQL;
import domain.stores.User.Store;
import domain.stores.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.logging.Logger;

/**
 * UserController contains methods for the UI to interact with the database.
 *
 * @author Brandon Epperson
 */
public class UserController {
    private static Logger log = null;
    private static Store store = null;

    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db the SQL instance to interact with.
     */
    public static void Configure(SQL db, Logger logger) {
        store = new Store(db);
        log = logger;
    }

    /**
     * getAll attempts to fetch all users in the store
     *
     * @return ObservableList of user
     */
    public static ObservableList<User> getAll() {
        List<User> users = store.getAll();
        if (!users.isEmpty()) {
            log.info("Total countries returned from getAll(): " + users.size());
            return FXCollections.observableList(users);
        }
        log.info("No users returned from getAll()");
        return FXCollections.emptyObservableList();
    }
}
