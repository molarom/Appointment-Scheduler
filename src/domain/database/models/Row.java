package domain.database.models;

import domain.database.annotations.Column;
import domain.time.Time;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Row represents a single row returned from a database query.
 *
 * @author Brandon Epperson
 */
public class Row extends HashMap<String, Object> {
    /**
     * Creates a new Row object with the given size
     *
     * @param size the number of columns within a row.
     */
    public Row(int size) {
        super(size);
    }

    /**
     * Scan reads in a generic type and attempts to populate its fields
     * based on the Column annotations set.
     *
     * @param dest the Object to scan to.
     * @param <T>  The type of the Object to scan.
     * @return the scanned Object or null if an error occurs.
     */
    public <T> T Scan(T dest) {
        try {
            @SuppressWarnings("unchecked")
            Class<T> type = (Class<T>) dest.getClass();

            Field[] fields = type.getDeclaredFields();
            for (Field f : fields) {
                if (!f.isAnnotationPresent(Column.class)) {
                    continue;
                }
                f.setAccessible(true);
                if (f.getType() != Time.class) {
                    f.set(dest, this.get(f.getAnnotation(Column.class).name()));
                    continue;
                }

                Time t = Time.fromObject(this.get(f.getAnnotation(Column.class).name()));
                f.set(dest, t);
                f.setAccessible(false);
            }
            return type.cast(dest);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
