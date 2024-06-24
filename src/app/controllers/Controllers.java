package app.controllers;

import app.controllers.CountryController.CountryController;
import app.controllers.CustomerController.CustomerController;
import app.controllers.CustomerViewController.CustomerViewController;
import app.controllers.DivsionsController.DivisionsController;
import app.controllers.LoginController.LoginController;
import domain.database.SQL;

import java.util.logging.Logger;

/**
 * Controllers accepts parameters required by the controllers to
 * ensure proper setup.
 *
 * @author Brandon Epperson
 */
public class Controllers {
    /**
     * Setup configures all the controllers used by the UI.
     *
     * @param db     the SQL instance to use
     * @param logger the logger to use
     */
    public static void Setup(SQL db, Logger logger) {
        LoginController.Configure(db);
        CustomerController.Configure(db, logger);
        CustomerViewController.Configure(db, logger);
        CountryController.Configure(db, logger);
        DivisionsController.Configure(db, logger);
    }
}
