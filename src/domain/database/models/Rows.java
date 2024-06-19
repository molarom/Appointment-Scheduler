package domain.database.models;

import java.util.ArrayList;

/**
 * Rows represents multiple rows returned from a database query.
 *
 * @author Brandon Epperson
 */
public class Rows extends ArrayList<Row> {
    /**
     * Creates a new Rows object with the given size
     * @param size the number of rows
     */
    public Rows(int size) {
        super(size);
    }
}
