package app.controllers;

import domain.database.SQL;
import domain.stores.Contact.Contact;
import domain.stores.Contact.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * ContactController contains methods for the UI layer to interact with
 * the database.
 *
 * @author Brandon Epperson
 */
public class ContactController {
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

    public static ObservableList<Contact> getAllContacts() {
        List<Contact> contacts = store.getAll();
        if (!contacts.isEmpty()) {
            log.info("Total contacts returned from getAll(): " + contacts.size());
            return FXCollections.observableList(contacts);
        }
        log.warning("No contacts returned from getAll()");
        return FXCollections.emptyObservableList();
    }

    public static Contact getById(int id) {
        Contact contact = store.getById(id);
        if (!Objects.equals(contact.getContactName(), "")) {
            log.info("Fetched contact: " + contact.getContactName());
            return contact;
        }
        log.warning("Unable to find contact with id: " + id);
        return new Contact(id);
    }
}
