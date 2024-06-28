package app.controllers;

import domain.User;
import domain.database.SQL;
import domain.stores.UserStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;
import java.util.logging.*;

public class UserController {
    private static Logger log = null;
    private static UserStore userStore = null;

    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db the SQL instance to interact with.
     */
    public static void Configure(SQL db, Logger logger) {
        userStore = new UserStore(db);
        log = logger;
    }

    public static ObservableList<User> getAll() {
        List<User> users = userStore.getAll();
        if (!users.isEmpty()) {
            log.info("Total countries returned from getAll(): " + users.size());
            return FXCollections.observableList(users);
        }
        log.info("No users returned from getAll()");
        return FXCollections.emptyObservableList();
    }
}
