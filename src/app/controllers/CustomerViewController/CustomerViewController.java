package app.controllers.CustomerViewController;

import domain.CustomerView;
import domain.database.SQL;
import domain.stores.CustomerViewStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.logging.Logger;

/**
 * CustomerViewController contains the methods for the ui to interact with
 * the database.
 */
public class CustomerViewController {
    private static Logger log = null;
    private static CustomerViewStore customerViewStore = null;


    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db     the SQL instance to interact with.
     * @param logger the logger to use
     */
    public static void Configure(SQL db, Logger logger) {
        customerViewStore = new CustomerViewStore(db);
        log = logger;
    }

    /**
     * @return an ObservableList of CustomerView
     */
    public static ObservableList<CustomerView> getCustomerViews() {
        List<CustomerView> customerViews = customerViewStore.getAllCustomers();
        if (!customerViews.isEmpty()) {
            log.info("Total customers returned from getAllCustomers(): " + customerViews.size());
            return FXCollections.observableList(customerViews);
        }
        log.warning("No customers returned from getAllCustomers()");
        return FXCollections.emptyObservableList();
    }
}
