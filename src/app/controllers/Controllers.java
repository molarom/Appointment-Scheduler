package app.controllers;

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
        AppointmentController.Configure(db, logger);
        ContactController.Configure(db, logger);
    }
}
