import domain.Customer;
import domain.database.SQL;
import domain.stores.CustomerStore;

import java.util.List;

/**
 * An application for managing appointment schedules.
 * <p>
 * Documentation is located in the root of the project directory underneath the "./docs" folder.
 * </p>
 * @author Brandon Epperson
 */
class Main {

  /**
   * The entrypoint of the application.
   *
   * @param args Command line arguments to pass to the program at startup.
   */
  public static void main(String[] args) throws Exception {
    String jdbc_url = "jdbc:mysql://localhost:3306/c195";
    String user = "root";
    String password = "password";

    SQL db = new SQL(jdbc_url, user, password);

    CustomerStore cs = new CustomerStore(db);

    Customer c = cs.getById(1);
    System.out.println("GetById:" + c.toString());
    List<Customer> customers = cs.getAll();

    for (Customer customer : customers) {
      System.out.println("GetAll:" + customer.toString());
    }
  }
}
