package app.controllers;

import domain.database.SQL;
import domain.stores.User.Store;
import domain.stores.User.User;

import java.io.IOException;
import java.util.logging.*;

/**
 * LoginController contains handlers for bridging the UI and domain layers.
 *
 * @author Brandon Epperson
 */
public class LoginController {
    private static final Logger log = Logger.getLogger("LoginController");
    private static Store store = null;

    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db the SQL instance to interact with.
     */
    public static void Configure(SQL db) {
        store = new Store(db);

        log.setLevel(Level.INFO);
        log.setUseParentHandlers(false);

        Handler ch = new ConsoleHandler();
        ch.setFormatter(new domain.log.Formatter());
        log.addHandler(ch);
        try {
            Handler fileHandler = new FileHandler("./login_activity.txt", 0, 1, true);
            fileHandler.setFormatter(new domain.log.Formatter());
            log.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * authenticate queries the store to authenticate a user.
     *
     * @param username the username
     * @param password the password
     */
    public static User authenticate(String username, String password) {
        User user = store.login(username, password);
        if (user != null) {
            log.info("successful login for user=" + username);
            return user;
        }
        log.info("failed login attempt for user=" + username);
        return null;
    }

}
