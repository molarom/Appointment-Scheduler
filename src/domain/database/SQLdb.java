package domain.database;

import java.sql.*;
import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * SQLdb Represents a connection to a SQL database
 *
 * @author Brandon Epperson
 */
public class SQLdb {
    private final MysqlDataSource ds;

    public SQLdb(String connection_url, String user_name, String password) {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(connection_url);
        ds.setUser(user_name);
        ds.setPassword(password);
        this.ds = ds;
    }

    /**
     * @return a new connection to the database
     */
    public Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
