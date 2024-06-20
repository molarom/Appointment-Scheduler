import domain.Customer;
import domain.database.SQL;
import domain.stores.CustomerStore;
import domain.time.Time;

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

    Time t = new Time();
    System.out.println("Time start: " + t.LocaltoString());
    //Customer c2 = new Customer("knight of nee", "123 not street st.", "44444", "999-888-5555", "admin", "admin");
    //c2.setCreateDate(t);
    //c2.setLastUpdate(t);
    //cs.add(c2);

    Customer c = cs.getById(1);
    System.out.println("GetById:" + c.toString());
    List<Customer> customers = cs.getAll();

    for (Customer customer : customers) {
      System.out.println("GetAll:" + customer.toString());
    }

    Customer c4 = cs.getByName("count of monte cristo");
    System.out.println("GetByName:" + c4.toString());

    Time t2 = new Time();
    System.out.println("Time Taken: " + t2.getDelta(t));
  }
}
