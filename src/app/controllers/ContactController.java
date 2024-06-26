package app.controllers;

import domain.Contact;
import domain.database.SQL;
import domain.stores.ContactStore;
import domain.stores.CountryStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.logging.Logger;

public class ContactController {
    private static Logger log = null;
    private static ContactStore contactStore = null;

    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db     the SQL instance to interact with.
     * @param logger the logger to use
     */
    static void Configure(SQL db, Logger logger) {
        contactStore = new ContactStore(db);
        log = logger;
    }

    public static ObservableList<Contact> getAllContacts() {
        List<Contact> contacts = contactStore.getAll();
        if (!contacts.isEmpty()) {
            log.info("Total contacts returned from getAll(): " + contacts.size());
            return FXCollections.observableList(contacts);
        }
        log.warning("No contacts returned from getAll()");
        return FXCollections.emptyObservableList();
    }
}
