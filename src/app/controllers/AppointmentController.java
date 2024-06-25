package app.controllers;

import domain.Appointment;
import domain.database.SQL;
import domain.stores.AppointmentStore;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.logging.Logger;

/**
 * AppointmentController contains the methods for the UI Layer to interact with
 * the database.
 */
public class AppointmentController {
    private static Logger log = null;
    private static AppointmentStore appointmentStore = null;


    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db     the SQL instance to interact with.
     * @param logger the logger to use
     */
    static void Configure(SQL db, Logger logger) {
        appointmentStore = new AppointmentStore(db);
        log = logger;
    }

    /**
     * @return an ObservableList of Appointment
     */
    public static ObservableList<Appointment> getAppointments() {
        List<Appointment> appointments = appointmentStore.getAll();
        if (!appointments.isEmpty()) {
            log.info("Total appointments returned from getAll(): " + appointments.size());
            return FXCollections.observableList(appointments);
        }
        log.warning("No appointments returned from getAll()");
        return FXCollections.emptyObservableList();
    }

    /**
     * addAppointment adds an appointment to the database
     *
     * @param appointment the appointment to add
     * @return true if the query was successful.
     */
    public static boolean addAppointment(Appointment appointment) {
        boolean success = appointmentStore.add(appointment);
        if (!success) {
            log.warning("Failed to add appointment: " + appointment);
            return false;
        }
        log.info("Appointment added: " + appointment);
        return true;
    }

    /**
     * updateAppointment updates a appointment in the database
     *
     * @param appointment the appointment to update
     * @return true if the query was successful
     */
    public static boolean updateAppointment(Appointment appointment) {
        boolean success = appointmentStore.update(appointment);
        if (!success) {
            log.warning("Failed to update appointment: " + appointment);
            return false;
        }
        log.info("Appointment updated: " + appointment);
        return true;
    }

    /**
     * deleteAppointment deletes a appointment in the database
     *
     * @param id the id of the appointment to delete
     * @return true if the query was successful
     */
    public static boolean deleteAppointment(int id) {
        boolean success = appointmentStore.delete(id);
        if (!success) {
            log.warning("Failed to delete appointment: " + id);
            return false;
        }
        log.info("Appointment deleted: " + id);
        return true;
    }


    /**
     * @return the number of appointments stored in the database
     */
    public static int countAppointments() {
        return appointmentStore.count();
    }

    /**
     * @return the max value in the appointment_id column
     */
    public static int maxId() {
        return appointmentStore.maxId();
    }
}
