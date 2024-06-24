package app.controllers.CustomerController;

import domain.Customer;
import domain.database.SQL;
import domain.stores.CustomerStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.logging.Logger;

/**
 * CustomerController contains the methods for the UI Layer to interact with
 * the database.
 */
public class CustomerController {
    private static Logger log = null;
    private static CustomerStore customerStore = null;


    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db     the SQL instance to interact with.
     * @param logger the logger to use
     */
    public static void Configure(SQL db, Logger logger) {
        customerStore = new CustomerStore(db);
        log = logger;
    }

    /**
     * @return an ObservableList of Customer
     */
    public static ObservableList<Customer> getCustomers() {
        List<Customer> customers = customerStore.getAll();
        if (!customers.isEmpty()) {
            log.info("Total customers returned from getAll(): " + customers.size());
            return FXCollections.observableList(customers);
        }
        log.warning("No customers returned from getAll()");
        return FXCollections.emptyObservableList();
    }

    public static void addCustomer(Customer customer) {
        System.out.println(customer);
    }

    public static int countCustomers() {
        return customerStore.count();
    }
}