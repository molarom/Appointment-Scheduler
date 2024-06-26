package domain.stores.Appointment;

import domain.database.SQL;
import domain.database.models.Rows;
import domain.time.Time;

import java.util.ArrayList;
import java.util.List;

/**
 * Store represents a data store for Appointment objects.
 *
 * @author Brandon Epperson
 */
public class Store {
    private final SQL db;

    /**
     * Creates a new Store to retrieve Appointment documents from the database.
     *
     * @param db the SQL instance to use.
     */
    public Store(SQL db) {
        this.db = db;
    }

    /**
     * add adds a new appointment to the database.
     *
     * @param appointment the appointment to add
     * @return true if the query is successful
     */
    public boolean add(Appointment appointment) {
        String query = "INSERT INTO " +
                "appointments (" +
                "title, " +
                "description, " +
                "location, " +
                "type, " +
                "start, " +
                "end, " +
                "create_date, " +
                "created_by, " +
                "last_update, " +
                "last_updated_by, " +
                "customer_id, " +
                "user_id, " +
                "contact_id " +
                ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            db.PreparedQueryExec(query,
                    appointment.getTitle(),
                    appointment.getDescription(),
                    appointment.getLocation(),
                    appointment.getType(),
                    appointment.getStart().withZone(Time.UTC).toSqlTimestamp(),
                    appointment.getEnd().withZone(Time.UTC).toSqlTimestamp(),
                    appointment.getCreateDate().withZone(Time.UTC).toSqlTimestamp(),
                    appointment.getCreatedBy(),
                    appointment.getLastUpdate().withZone(Time.UTC).toSqlTimestamp(),
                    appointment.getLastUpdatedBy(),
                    appointment.getCustomerId(),
                    appointment.getUserId(),
                    appointment.getContactId()
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * update attempts to update an appointment in the database
     *
     * @param appointment the appointment to update
     * @return true if the query was successful
     */
    public boolean update(Appointment appointment) {
        String query = "UPDATE " +
                "appointments " +
                "SET " +
                "title = ?, " +
                "description = ?, " +
                "location = ?, " +
                "type = ?, " +
                "start = ?, " +
                "end = ?, " +
                "last_update = ?, " +
                "last_updated_by = ?, " +
                "customer_id = ?, " +
                "user_id = ?, " +
                "contact_id = ? " +
                "WHERE appointment_id = ?";

        try {
            db.PreparedQueryExec(query,
                    appointment.getTitle(),
                    appointment.getDescription(),
                    appointment.getLocation(),
                    appointment.getType(),
                    appointment.getStart().withZone(Time.UTC).toSqlTimestamp(),
                    appointment.getEnd().withZone(Time.UTC).toSqlTimestamp(),
                    appointment.getLastUpdate().withZone(Time.UTC).toSqlTimestamp(),
                    appointment.getLastUpdatedBy(),
                    appointment.getCustomerId(),
                    appointment.getUserId(),
                    appointment.getContactId(),
                    appointment.getAppointmentId()
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * delete attempts to delete an appointment from the database
     *
     * @param appointmentId the appointment's id to delete
     * @return true if the query was successful
     */
    public boolean delete(int appointmentId) {
        String query = "DELETE FROM appointments WHERE appointment_id = ?";
        try {
            db.PreparedQueryExec(query, appointmentId);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Queries the database for an appointment based on the id.
     * If no appointment is found it returns an empty Appointment object.
     *
     * @param id the appointment_id to search for.
     * @return the appointment object
     */
    public Appointment getById(int id) {
        String query = "SELECT " +
                "* " +
                "FROM appointments " +
                "WHERE appointment_id = ? " +
                "LIMIT 1";

        Rows rows = db.PreparedQuery(query, id);
        Appointment appointment = new Appointment();

        return rows.get(0).Scan(appointment);
    }

    /**
     * Queries the database for an appointment  based on the title.
     * If no appointment is found it returns an empty Appointment object.
     *
     * @param title the title to search for.
     * @return the appointment object
     */
    public Appointment getByTitle(String title) {
        String query = "SELECT " +
                "* " +
                "FROM appointments " +
                "WHERE title = ? " +
                "LIMIT 1";

        Rows rows = db.PreparedQuery(query, title);
        Appointment appointment = new Appointment();

        return rows.get(0).Scan(appointment);
    }

    /**
     * getAll attempts to fetch all appointments contained in the database.
     * Uses a lambda to scan each row and populate the list.
     *
     * @return a list of all appointments
     */
    public List<Appointment> getAll() {
        String query = "SELECT " +
                "* " +
                "FROM appointments " +
                "ORDER BY appointment_id";

        Rows rows = db.PreparedQuery(query);
        List<Appointment> appointments = new ArrayList<>();
        rows.forEach(row -> {
            Appointment appointment = new Appointment();
            appointments.add(row.Scan(appointment));
        });
        return appointments;
    }

    /**
     * getAllWeekly attempts to fetch all appointments contained in the database
     * that start within the next week.
     *
     * @return a list of all appointments
     */
    public List<Appointment> getAllWeekly() {
        String query = "SELECT " +
                "* " +
                "FROM appointments " +
                "WHERE week(start) = week(now())" +
                "ORDER BY appointment_id";

        Rows rows = db.PreparedQuery(query);
        List<Appointment> weekly = new ArrayList<>();
        rows.forEach(row -> {
            Appointment appointment = new Appointment();
            weekly.add(row.Scan(appointment));
        });
        return weekly;
    }

    /**
     * getAllMonthly attempts to fetch all appointments contained in the database
     * that start within the next week.
     *
     * @return a list of all appointments
     */
    public List<Appointment> getAllMonthly() {
        String query = "SELECT " +
                "* " +
                "FROM appointments " +
                "WHERE month(start) = month(now())" +
                "ORDER BY appointment_id";

        Rows rows = db.PreparedQuery(query);
        List<Appointment> monthly = new ArrayList<>();
        rows.forEach(row -> {
            Appointment appointment = new Appointment();
            monthly.add(row.Scan(appointment));
        });
        return monthly;
    }

    /**
     * getByUserId attempts to fetch all appointments for a provided user id.
     *
     * @param id the user id
     * @return a list of appointments
     */
    public List<Appointment> getByUserId(int id) {
        String query = "SELECT " +
                "* " +
                "FROM appointments " +
                "WHERE user_id = ?";

        Rows rows = db.PreparedQuery(query, id);
        List<Appointment> appointments = new ArrayList<>();
        rows.forEach(row -> {
            Appointment appointment = new Appointment();
            appointments.add(row.Scan(appointment));
        });
        return appointments;
    }

    /**
     * countByCustomerId attempts to fetch the number of appointments for a provided customer id.
     *
     * @param id the user id
     * @return the number of appointments
     */
    public int countByCustomerId(int id) {
        String query = "SELECT " +
                "count(appointment_id) " +
                "FROM appointments " +
                "WHERE customer_id = ?";

        Rows rows = db.PreparedQuery(query, id);
        int count = 0;
        return rows.get(0).Scan(count);
    }

    /**
     * @return the number of appointments in the database.
     */
    public int count() {
        String query = "SELECT COUNT(*) FROM appointments";

        Rows rows = db.PreparedQuery(query);
        int count = 0;
        return rows.get(0).Scan(count);
    }

    /**
     * @return the highest number in the id column
     */
    public int maxId() {
        String query = "SELECT max(appointment_id) FROM appointments";

        Rows row = db.PreparedQuery(query);
        int max = 0;
        return row.get(0).Scan(max);
    }

    public List<MonthlyReport> getMonthlyReport() {
        String query = """
                    SELECT
                        MONTHNAME(start) AS month,
                        type,
                        Count(*) AS total
                    FROM appointments
                    GROUP BY MONTHNAME(start), type
                """;

        Rows rows = db.PreparedQuery(query);
        List<MonthlyReport> mr = new ArrayList<>();
        rows.forEach(row -> {
            MonthlyReport r = new MonthlyReport();
            mr.add(row.Scan(r));
        });
        return mr;
    }
}
