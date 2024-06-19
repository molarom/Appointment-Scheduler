package domain.store;

import domain.Customer;
import domain.database.SQLdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerStore {
    private SQLdb db;

    /**
     * Creates a new CustomerStore to retrieve Customer documents from the database.
     * @param db the SQLdb instance to use.
     */
    public CustomerStore(SQLdb db) {
       this.db = db;
    }

    public Customer getById(int id) {
       Connection conn = db.getConnection();
       try {
           PreparedStatement ps = conn.prepareStatement(
                   "SELECT " +
                   "* " +
                   "FROM customers " +
                   "WHERE customer_id = ?"
           );
           ps.setInt(1, id);

           ResultSet rs = ps.executeQuery();
           if (rs.next()) {
               Customer customer = new Customer();

               customer.setName(rs.getString("customer_name"));
               customer.setAddress(rs.getString("address"));
               customer.setPostalCode(rs.getString("postal_code"));
               customer.setAddress(rs.getString("address"));
               customer.setPhone(rs.getString("phone"));

                return customer;
           }
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return null;
    }
}
