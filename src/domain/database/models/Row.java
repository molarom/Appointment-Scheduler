package domain.database.models;

import java.util.HashMap;

/**
 * Row represents a single row returned from a database query.
 *
 * @author Brandon Epperson
 */
public class Row extends HashMap<String, Object> {
    /**
     * Creates a new Row object with the given size
     * @param size the number of columns within a row.
     */
    public Row(int size) {
        super(size);
    }
}
