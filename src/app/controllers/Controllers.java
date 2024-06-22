package app.controllers;

import app.controllers.CustomerController.CustomerController;
import app.controllers.LoginController.LoginController;
import domain.database.SQL;

import java.util.logging.Logger;

public class Controllers {
    public static void Setup(SQL db, Logger logger) {
        LoginController.Configure(db);
        CustomerController.Configure(db, logger);
    }
}
