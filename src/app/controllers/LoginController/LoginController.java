package app.controllers.LoginController;

import domain.User;
import domain.database.SQL;
import domain.stores.UserStore;

import java.io.IOException;
import java.util.logging.*;

/**
 * LoginController contains handlers for bridging the UI and domain layers.
 *
 * @author Brandon Epperson
 */
public class LoginController {
    private static final Logger log = Logger.getLogger("LoginController");
    private static UserStore userStore = null;

    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db the SQL instance to interact with.
     */
    public static void Configure(SQL db) {
        userStore = new UserStore(db);

        log.setLevel(Level.INFO);
        log.setUseParentHandlers(false);

        Handler ch = new ConsoleHandler();
        ch.setFormatter(new domain.log.Formatter());
        log.addHandler(ch);
        try {
            Handler fileHandler = new FileHandler("./login_activity.txt", 200, 1);
            fileHandler.setFormatter(new domain.log.Formatter());
            log.addHandler(fileHandler);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * authenticate queries the userStore to authenticate a user.
     *
     * @param username the username
     * @param password the password
     */
    public static User authenticate(String username, String password) {
        User user = userStore.login(username, password);
        if (user != null) {
            log.info("successful login for user=" + username);
            return user;
        }
        log.info("failed login attempt for user=" + username);
        return null;
    }

}
