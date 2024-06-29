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

            // If we aren't getting a custom class just convert and cast.
            if (this.size() == 1) {
                if (this.values().toArray()[0] instanceof Long value) {
                    return type.cast(value.intValue());
                }
                return type.cast(this.values().toArray()[0]);
            }

            Field[] fields = type.getDeclaredFields();
            for (Field f : fields) {
                if (!f.isAnnotationPresent(Column.class)) {
                    continue;
                }
                f.setAccessible(true);
                if (this.get(f.getAnnotation(Column.class).name()) instanceof Long value) {
                    f.set(dest, value.intValue());
                    continue;
                }
                if (f.getType() != Time.class) {
                    f.set(dest, this.get(f.getAnnotation(Column.class).name()));
                    continue;
                }

                // If it's coming from the DB it should be stored in UTC.
                Time t = Time.fromObject(this.get(f.getAnnotation(Column.class).name()), Time.UTC);
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
