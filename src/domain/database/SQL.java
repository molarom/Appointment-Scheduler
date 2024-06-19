package domain.database;

import java.sql.*;


import com.mysql.cj.jdbc.MysqlDataSource;
import domain.database.models.Row;
import domain.database.models.Rows;

/**
 * SQLdb Represents a connection to a SQL database
 *
 * @author Brandon Epperson
 */
public class SQL {
    private final MysqlDataSource ds;

    public SQL(String connection_url, String user_name, String password) {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setURL(connection_url);
        ds.setUser(user_name);
        ds.setPassword(password);
        this.ds = ds;
    }

    public Rows PreparedQuery(String query, Object... params) {
        try {
            Connection conn = getConnection();
            PreparedStatement ps = createPreparedStatement(conn, query, params);
            Rows list = resultSetToRows(ps.executeQuery());
            conn.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @return a new connection to the database
     */
    private Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * Creates a prepareStatement to execute queries.
     * @param conn the database connection object
     * @param query the query string
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
     * Converts a ResultSet to a List for handling of generic types.
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
}
