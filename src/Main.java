import domain.Customer;
import domain.FirstLevelDivision;
import domain.database.SQL;
import domain.stores.CustomerStore;
import domain.stores.FirstLevelDivisionStore;
import domain.time.Time;

import java.util.List;

/**
 * An application for managing appointment schedules.
 * <p>
 * Documentation is located in the root of the project directory underneath the "./docs" folder.
 * </p>
 *
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

        FirstLevelDivisionStore fs = new FirstLevelDivisionStore(db);
        CustomerStore cs = new CustomerStore(db);

        Time t = new Time();
        System.out.println("Time start: " + t.LocaltoString());

        List<FirstLevelDivision> fsDivs = fs.getAllDivisions();
        for (FirstLevelDivision div : fsDivs) {
            System.out.println(div.toString());
        }

        List<Customer> csAll = cs.getAll();
        for (Customer c : csAll) {
            System.out.println(c.toString());
        }

        Customer c2 = cs.getById(1);
        System.out.println("getById: " + c2.toString());

        Time t2 = new Time();
        System.out.println("Time Taken: " + t2.getDelta(t));
    }
}
