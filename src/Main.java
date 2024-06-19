import domain.Customer;
import domain.database.SQLdb;
import domain.store.CustomerStore;

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

    SQLdb db = new SQLdb(jdbc_url, user, password);

    CustomerStore cs = new CustomerStore(db);

    Customer c = cs.getById(1);

    System.out.println(c.toString());
  }
}
