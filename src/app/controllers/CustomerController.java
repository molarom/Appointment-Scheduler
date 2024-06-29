package app.controllers;

import domain.database.SQL;
import domain.stores.Customer.Customer;
import domain.stores.Customer.CustomerReport;
import domain.stores.Customer.Store;
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
    private static Store store = null;


    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db     the SQL instance to interact with.
     * @param logger the logger to use
     */
    static void Configure(SQL db, Logger logger) {
        store = new Store(db);
        log = logger;
    }

    /**
     * @return an ObservableList of Customer
     */
    public static ObservableList<Customer> getCustomers() {
        List<Customer> customers = store.getAll();
        if (!customers.isEmpty()) {
            log.info("Total customers returned from getAll(): " + customers.size());
            return FXCollections.observableList(customers);
        }
        log.warning("No customers returned from getAll()");
        return FXCollections.emptyObservableList();
    }

    /**
     * addCustomer adds a customer to the database
     *
     * @param customer the customer to add
     * @return true if the query was successful.
     */
    public static boolean addCustomer(Customer customer) {
        boolean success = store.add(customer);
        if (!success) {
            log.warning("Failed to add customer: " + customer);
            return false;
        }
        log.info("Customer added: " + customer);
        return true;
    }

    /**
     * updateCustomer updates a customer in the database
     *
     * @param customer the customer to update
     * @return true if the query was successful
     */
    public static boolean updateCustomer(Customer customer) {
        boolean success = store.update(customer);
        if (!success) {
            log.warning("Failed to update customer: " + customer);
            return false;
        }
        log.info("Customer updated: " + customer);
        return true;
    }

    /**
     * deleteCustomer deletes a customer in the database
     *
     * @param id the id of the customer to delete
     * @return true if the query was successful
     */
    public static boolean deleteCustomer(int id) {
        boolean success = store.delete(id);
        if (!success) {
            log.warning("Failed to delete customer: " + id);
            return false;
        }
        log.info("Customer deleted: " + id);
        return true;
    }


    /**
     * @return the number of customers stored in the database
     */
    public static int countCustomers() {
        return store.count();
    }

    /**
     * @return the max value in the customer_id column
     */
    public static int maxId() {
        return store.maxId();
    }

    /**
     * @return the list of CustomerReport
     */
    public static ObservableList<CustomerReport> getCustomerReport() {
        List<CustomerReport> cr = store.getCustomerReport();
        if (!cr.isEmpty()) {
            log.info("Fetched " + cr.size() + " rows from getCustomerReport");
            return FXCollections.observableList(cr);
        }
        log.warning("No customers returned from getCustomerReport");
        return FXCollections.emptyObservableList();
    }
}
