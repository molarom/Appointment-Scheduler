package domain.stores;

import domain.CustomerView;
import domain.database.SQL;
import domain.database.models.Rows;

import java.util.ArrayList;
import java.util.List;

public class CustomerViewStore {
    private final SQL db;

    /**
     * Creates a new CustomerStore to retrieve CustomerView documents from the database
     *
     * @param db the SQL instance to use.
     */
    public CustomerViewStore(SQL db) {
        this.db = db;
    }

    /**
     * getAllCustomers attempts to retrieve all
     * CustomerView information from the database.
     *
     * @return the list of returned CustomerView
     */
    public List<CustomerView> getAllCustomers() {
        String query = "SELECT " +
                "c.customer_id, " +
                "c.customer_name, " +
                "c.phone, " +
                "c.address, " +
                "c.postal_code, " +
                "c.created_by, " +
                "c.create_date, " +
                "c.last_updated_by, " +
                "c.last_update, " +
                "d.division_id, " +
                "d.division, " +
                "ct.country_id, " +
                "ct.country " +
                "FROM customers c " +
                "JOIN first_level_divisions d USING (division_id) " +
                "JOIN countries ct USING (country_id) ";

        Rows rows = db.PreparedQuery(query);
        List<CustomerView> customerViewList = new ArrayList<>();

        rows.forEach(row -> {
            CustomerView customerView = new CustomerView();
            row.Scan(customerView);
            customerViewList.add(customerView);
        });

        return customerViewList;
    }


}
