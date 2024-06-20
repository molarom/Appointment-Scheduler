package domain.database.models;

import domain.database.annotations.Column;
import domain.time.Time;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Rows represents multiple rows returned from a database query.
 *
 * @author Brandon Epperson
 */
public class Rows extends ArrayList<Row> {
    /**
     * Creates a new Rows object with the given size
     *
     * @param size the number of rows
     */
    public Rows(int size) {
        super(size);
    }

    public List<?> Scan(Class<?> destType) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        List<? super Object> result = new ArrayList<>();
        for (Row r : this) {
            var obj = destType.getDeclaredConstructor().newInstance();
            try {
                Field[] fields = obj.getClass().getDeclaredFields();
                for (Field f : fields) {
                    if (!f.isAnnotationPresent(Column.class)) {
                        continue;
                    }
                    f.setAccessible(true);
                    if (f.getType() != Time.class) {
                        f.set(obj, r.get(f.getAnnotation(Column.class).name()));
                        continue;
                    }

                    Time t = Time.fromObject(r.get(f.getAnnotation(Column.class).name()));
                    f.set(obj, t);
                    f.setAccessible(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            result.add(obj);
        }
        return  result;
    }
}
