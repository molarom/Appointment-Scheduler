package domain.stores;

import domain.Customer;
import domain.database.SQL;
import domain.database.models.Row;
import domain.database.models.Rows;
import domain.time.Time;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * CustomerStore represents a data store for Customer objects.
 *
 * @author Brandon Epperson
 */
public class CustomerStore {
    private final SQL db;

    /**
     * Creates a new CustomerStore to retrieve Customer documents from the database.
     *
     * @param db the SQL instance to use.
     */
    public CustomerStore(SQL db) {
        this.db = db;
    }

    public void add(Customer customer) {
        String query = "INSERT INTO " +
                "customers (" +
                "customer_name, " +
                "address, " +
                "postal_code, " +
                "phone, " +
                "created_by, " +
                "last_updated_by, " +
                "create_date, " +
                "last_update " +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        db.PreparedQueryExec(query,
                customer.getName(),
                customer.getAddress(),
                customer.getPostalCode(),
                customer.getPhone(),
                customer.getCreatedBy(),
                customer.getLastUpdatedBy(),
                customer.getCreateDate().toSqlTimestamp(),
                customer.getLastUpdate().toSqlTimestamp()
        );
    }

    /**
     * Queries the database for a user based on their customer_id.
     * If no customer is found it returns an empty Customer object.
     *
     * @param id the customer_id to search for.
     * @return the customer object
     */
    public Customer getById(int id) {
        String query = "SELECT " +
                "* " +
                "FROM customers " +
                "WHERE customer_id = ? " +
                "LIMIT 1";

        Rows rows = db.PreparedQuery(query, id);

        Row r = rows.get(0); // We limit the query to 1 result, no need for loops.
        return rowToCustomer(r);
    }

    /**
     * Queries the database for a user based on their customer_id.
     * If no customer is found it returns an empty Customer object.
     *
     * @param name the customer_name to search for.
     * @return the customer object
     */
    public Customer getByName(String name) {
        String query = "SELECT " +
                "* " +
                "FROM customers " +
                "WHERE customer_name = ? " +
                "LIMIT 1";

        Rows rows = db.PreparedQuery(query, name);

        Row r = rows.get(0); // We limit the query to 1 result, no need for loops.
        return rowToCustomer(r);
    }

    /**
     * @return a list of all customers contained in the database
     */
    public List<Customer> getAll() {
        String query = "SELECT " +
                "* " +
                "FROM customers " +
                "ORDER BY customer_id";

        Rows rows = db.PreparedQuery(query);
        try {
            @SuppressWarnings("unchecked")
            List<Customer> scan = (List<Customer>) rows.Scan(Customer.class);
            return scan;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    /**
     * Populates a Customer object based on the data contained within a Row.
     *
     * @param r the Row to read from
     * @return the Customer object
     */
    private Customer rowToCustomer(Row r) {
        Customer customer = new Customer(
                (int) r.get("customer_id")
        );
        customer.setName(r.get("customer_name").toString());
        customer.setAddress(r.get("address").toString());
        customer.setPostalCode(r.get("postal_code").toString());
        customer.setAddress(r.get("address").toString());
        customer.setPhone(r.get("phone").toString());
        customer.setCreatedBy(r.get("created_by").toString());
        customer.setLastUpdatedBy(r.get("last_updated_by").toString());

        Object create_date = Objects.requireNonNull(r.get("create_date"), "create_date cannot be null");
        customer.setCreateDate(Time.fromObject(create_date));

        Object last_update = Objects.requireNonNull(r.get("last_update"), "last_update cannot be null");
        customer.setLastUpdate(Time.fromObject(last_update));

        return customer;
    }
}
