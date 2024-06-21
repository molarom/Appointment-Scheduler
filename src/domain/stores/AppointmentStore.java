package domain.stores;

import domain.Appointment;
import domain.database.SQL;
import domain.database.models.Rows;

import java.util.ArrayList;
import java.util.List;

/**
 * AppointmentStore represents a data store for Appointment objects.
 *
 * @author Brandon Epperson
 */
public class AppointmentStore {
    private final SQL db;

    /**
     * Creates a new AppointmentStore to retrieve Appointment documents from the database.
     *
     * @param db the SQL instance to use.
     */
    public AppointmentStore(SQL db) {
        this.db = db;
    }

    /**
     * add adds a new appointment to the database.
     *
     * @param appointment the appointment to add
     */
    public void add(Appointment appointment) {
        // TODO: Update this query
        String query = "INSERT INTO " +
                "appointments (" +
                "appointment_name, " +
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
}
