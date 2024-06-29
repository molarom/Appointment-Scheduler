package app.controllers;

import domain.database.SQL;
import domain.stores.Appointment.Appointment;
import domain.stores.Appointment.MonthlyReport;
import domain.stores.Appointment.Store;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * AppointmentController contains the methods for the UI Layer to interact with
 * the database.
 *
 * @author Brandon Epperson
 */
public class AppointmentController {
    private static Logger log = null;
    private static Store store = null;


    /**
     * Configure sets up the required parameters for the Controller.
     * This should only be called once.
     *
     * @param db     the SQL instance to interact with.
     * @param logger the logger to use
     */
    static void Configure(SQL db, Logger logger) {
        store = new Store(db);
        log = logger;
    }

    /**
     * @return an ObservableList of Appointment
     */
    public static ObservableList<Appointment> getAppointments() {
        List<Appointment> appointments = store.getAll();
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
        boolean success = store.add(appointment);
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
        boolean success = store.update(appointment);
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
        boolean success = store.delete(id);
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
        return store.count();
    }

    /**
     * @return the max value in the appointment_id column
     */
    public static int maxId() {
        return store.maxId();
    }

    /**
     * searches for appointments by user id
     *
     * @param id the user id
     * @return the list of appointments
     */
    public static List<Appointment> getByUserId(int id) {
        List<Appointment> appointments = store.getByUserId(id);
        if (!appointments.isEmpty()) {
            log.info("Total appointments returned for getByUserId(): " + appointments.size());
            return appointments;
        }
        return new ArrayList<>();
    }

    /**
     * @return an ObservableList of Appointment
     */
    public static ObservableList<Appointment> getWeeklyAppointments() {
        List<Appointment> appointments = store.getAllWeekly();
        if (!appointments.isEmpty()) {
            log.info("Total appointments returned from getAllWeekly(): " + appointments.size());
            return FXCollections.observableList(appointments);
        }
        log.warning("No appointments returned from getAllWeekly()");
        return FXCollections.emptyObservableList();
    }

    /**
     * @return an ObservableList of Appointment
     */
    public static ObservableList<Appointment> getMonthlyAppointments() {
        List<Appointment> appointments = store.getAllMonthly();
        if (!appointments.isEmpty()) {
            log.info("Total appointments returned from getAllMonthly(): " + appointments.size());
            return FXCollections.observableList(appointments);
        }
        log.warning("No appointments returned from getAllMonthly()");
        return FXCollections.emptyObservableList();
    }

    /**
     * @return an ObservableList of MonthlyReport
     */
    public static ObservableList<MonthlyReport> getMonthlyReport() {
        List<MonthlyReport> reports = store.getMonthlyReport();
        if (!reports.isEmpty()) {
            log.info("Total rows fetched from getMonthlyReport: " + reports.size());
            return FXCollections.observableList(reports);
        }
        log.warning("No rows fetched from getMonthlyReport");
        return FXCollections.emptyObservableList();
    }
}
