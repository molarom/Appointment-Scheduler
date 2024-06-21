package domain.stores;

import domain.User;
import domain.database.SQL;
import domain.database.models.Rows;

import java.util.ArrayList;
import java.util.List;

// TODO: add login method

/**
 * UserStore represents a data store for User objects.
 *
 * @author Brandon Epperson
 */
public class UserStore {
    private final SQL db;

    /**
     * Creates a new UserStore to retrieve User documents from the database.
     *
     * @param db the SQL instance to use.
     */
    public UserStore(SQL db) {
        this.db = db;
    }

    /**
     * login attempts to retrieve a user with the given name and password.
     */
    public User login(String username, String password) {
        String query = "SELECT " +
                "* " +
                "FROM users " +
                "WHERE user_name = ? AND password = ?";

        Rows rows = db.PreparedQuery(query, username, password);
        User user = new User();

        try {
            return rows.get(0).Scan(user);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * add adds a new user to the database.
     *
     * @param user the user to add
     */
    public void add(User user) {
        // TODO: update this query
        String query = "INSERT INTO " +
                "users (" +
                "user_name, " +
                "address, " +
                "postal_code, " +
                "phone, " +
                "created_by, " +
                "last_updated_by, " +
                "create_date, " +
                "last_update " +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    }

    /**
     * Queries the database for a user based on their user_id.
     * If no user is found it returns an empty User object.
     *
     * @param id the user_id to search for.
     * @return the user object
     */
    public User getById(int id) {
        String query = "SELECT " +
                "* " +
                "FROM users " +
                "WHERE user_id = ? " +
                "LIMIT 1";

        Rows rows = db.PreparedQuery(query, id);
        User user = new User();

        return rows.get(0).Scan(user);
    }

    /**
     * Queries the database for a user based on their user_id.
     * If no user is found it returns an empty User object.
     *
     * @param name the user_name to search for.
     * @return the user object
     */
    public User getByName(String name) {
        String query = "SELECT " +
                "* " +
                "FROM users " +
                "WHERE user_name = ? " +
                "LIMIT 1";

        Rows rows = db.PreparedQuery(query, name);
        User user = new User();

        return rows.get(0).Scan(user);
    }

    /**
     * getAll attempts to fetch all users contained in the database.
     * Uses a lambda to scan each row and populate the list.
     *
     * @return a list of all users
     */
    public List<User> getAll() {
        String query = "SELECT " +
                "* " +
                "FROM users " +
                "ORDER BY user_id";

        Rows rows = db.PreparedQuery(query);
        List<User> users = new ArrayList<>();
        rows.forEach(row -> {
            User user = new User();
            users.add(row.Scan(user));
        });
        return users;
    }
}
