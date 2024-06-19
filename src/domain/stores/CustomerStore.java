package domain.stores;

import domain.Customer;
import domain.database.SQL;
import domain.database.models.Row;
import domain.database.models.Rows;
import domain.time.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerStore {
    private final SQL db;

    /**
     * Creates a new CustomerStore to retrieve Customer documents from the database.
     *
     * @param db the SQLdb instance to use.
     */
    public CustomerStore(SQL db) {
        this.db = db;
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
                "WHERE customer_id = ? LIMIT 1";

        Rows rows = db.PreparedQuery(query, id);

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
        List<Customer> customers = new ArrayList<>();
        for (Row row : rows) {
            customers.add(rowToCustomer(row));
        }
        return customers;
    }

    /**
     * Populates a Customer object based on the data contained within a Row.
     *
     * @param r the Row to read from
     * @return the Customer object
     */
    @SuppressWarnings("DataFlowIssue")
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
