package domain.database;

import com.mysql.cj.jdbc.MysqlDataSource;
import domain.database.models.Row;
import domain.database.models.Rows;

import java.sql.*;

/**
 * SQLdb Represents a connection to a SQL database
 *
 * @author Brandon Epperson
 */
public class SQL {
    private final MysqlDataSource ds;

    /**
     * Constructs a new SQL instance to interact with a SQL database.
     *
     * @param host      the host to connect to
     * @param port      the port to connect to
     * @param database  the database to connect to
     * @param user_name the user_name to authenticate as
     * @param password  the password for the user
     */
    public SQL(String host, String port, String database, String user_name, String password) {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL("jdbc:mysql://" + host + ":" + port + "/" + database);
        ds.setUser(user_name);
        ds.setPassword(password);
        this.ds = ds;
    }

    /**
     * Ping sends a ping query to the database to ensure it
     * is ready to accept connections.
     */
    public void Ping() throws SQLException {
        Connection conn = getConnection();
        String query = "SELECT 1";
        Statement stmt = conn.createStatement();
        stmt.executeQuery(query);
    }

    /**
     * PreparedQuery Executes a prepared statement against the database
     *
     * @param query  the query to run
     * @param params any query parameters to provide
     * @return the resulting Rows
     */
    public Rows PreparedQuery(String query, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Savepoint save = null;
        try {
            conn = getConnection();
            ps = createPreparedStatement(conn, query, params);
            save = conn.setSavepoint();
            rs = ps.executeQuery();
            Rows rows = resultSetToRows(rs);
            conn.commit();
            return rows;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(save);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            closeConnection(conn);
            closeResultSet(rs);
            closePreparedStatement(ps);
        }
        return null;
    }

    /**
     * PreparedQueryExec Executes a prepared statement against the database that
     * doesn't return anything.
     *
     * @param query  the query to run
     * @param params any query parameters to provide
     */
    public void PreparedQueryExec(String query, Object... params) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        Savepoint save = null;
        try {
            conn = getConnection();
            ps = createPreparedStatement(conn, query, params);
            save = conn.setSavepoint();
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(save);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            throw new RuntimeException("Failed to execute query: " + query, e);
        } finally {
            closeConnection(conn);
            closePreparedStatement(ps);
        }
    }


    /**
     * @return a new connection to the database
     */
    private Connection getConnection() throws SQLException {
        Connection conn = ds.getConnection();
        conn.setAutoCommit(false);
        return conn;
    }

    /**
     * Creates a prepareStatement to execute queries.
     *
     * @param conn   the database connection object
     * @param query  the query string
     * @param params the query parameters to set
     * @return the PreparedStatement
     */
    private PreparedStatement createPreparedStatement(Connection conn, String query, Object... params) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        return ps;
    }

    /**
     * Converts a ResultSet to Rows for handling of generic types.
     *
     * @param rs the ResultSet to convert
     * @return the list of converted row objects
     */
    private Rows resultSetToRows(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        Rows rows = new Rows(columnCount);
        while (rs.next()) {
            Row row = new Row(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                row.put(rsmd.getColumnName(i), rs.getObject(i));
            }
            rows.add(row);
        }

        return rows;
    }

    /**
     * attempts to close a connection
     *
     * @param conn the connection to close
     */
    private void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * closeResultSet attempts to close the handle to the resultSet
     *
     * @param rs the ResultSet to close
     */
    private void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * closePreparedStatement attempts to close a PreparedStatement
     *
     * @param ps the PreparedStatement to close
     */
    private void closePreparedStatement(PreparedStatement ps) {
        if (ps != null) {
            try {
                ps.close();
            } catch (Exception ignored) {
            }
        }
    }
}
