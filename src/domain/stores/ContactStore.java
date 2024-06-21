package domain.stores;

import domain.Contact;
import domain.database.SQL;
import domain.database.models.Rows;

import java.util.ArrayList;
import java.util.List;

/**
 * ContactStore represents a data store for Contact objects.
 *
 * @author Brandon Epperson
 */
public class ContactStore {
    private final SQL db;

    /**
     * Creates a new ContactStore to retrieve Contact documents from the database.
     *
     * @param db the SQL instance to use.
     */
    public ContactStore(SQL db) {
        this.db = db;
    }

    /**
     * Queries the database for a user based on their contact_id.
     * If no contact is found it returns an empty Contact object.
     *
     * @param id the contact_id to search for.
     * @return the contact object
     */
    public Contact getById(int id) {
        String query = "SELECT " +
                "* " +
                "FROM contacts " +
                "WHERE contact_id = ? " +
                "LIMIT 1";

        Rows rows = db.PreparedQuery(query, id);
        Contact contact = new Contact();

        return rows.get(0).Scan(contact);
    }

    /**
     * Queries the database for a user based on their contact_id.
     * If no contact is found it returns an empty Contact object.
     *
     * @param name the contact_name to search for.
     * @return the contact object
     */
    public Contact getByName(String name) {
        String query = "SELECT " +
                "* " +
                "FROM contacts " +
                "WHERE contact_name = ? " +
                "LIMIT 1";

        Rows rows = db.PreparedQuery(query, name);
        Contact contact = new Contact();

        return rows.get(0).Scan(contact);
    }

    /**
     * getAll attempts to fetch all contacts contained in the database.
     * Uses a lambda to scan each row and populate the list.
     *
     * @return a list of all contacts
     */
    public List<Contact> getAll() {
        String query = "SELECT " +
                "* " +
                "FROM contacts " +
                "ORDER BY contact_id";

        Rows rows = db.PreparedQuery(query);
        List<Contact> contacts = new ArrayList<>();
        rows.forEach(row -> {
            Contact contact = new Contact();
            contacts.add(row.Scan(contact));
        });
        return contacts;
    }
}
