package ui.appointments;

import app.alerts.Alerts;
import app.controllers.AppointmentController;
import app.controllers.ContactController;
import domain.stores.Appointment.Appointment;
import domain.stores.Contact.Contact;
import domain.time.Time;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * AppointmentTableView displays a table containing all appointment
 * documents stored in the database.
 * Uses a lambda to fire a non-blocking alert if no appointments are found on startup.
 *
 * @author Brandon Epperson
 */
public class AppointmentTableView extends TableView<Appointment> {
    ObservableList<Contact> contacts;

    /**
     * AppointmentTableView constructs a new AppointmentTableView for use in the application.
     */
    @SuppressWarnings("unchecked")
    public AppointmentTableView() {
        this.getStyleClass().add("table-view");
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.contacts = ContactController.getAllContacts();

        // ------------------------------------------------------
        // Columns

        TableColumn<Appointment, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        idCol.setMaxWidth(1200);

        TableColumn<Appointment, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Appointment, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Appointment, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Appointment, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(param -> {
            for (Contact contact : contacts) {
                if (contact.getContactId() == param.getValue().getContactId()) {
                    return new ReadOnlyStringWrapper(contact.getContactName());
                }
            }
            return new ReadOnlyStringWrapper("invalid contact name");
        });

        TableColumn<Appointment, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Appointment, String> startCol = new TableColumn<>("Start Date");
        startCol.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getStart().withZone(Time.SystemT).toString()));

        TableColumn<Appointment, String> endCol = new TableColumn<>("End Date");
        endCol.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getEnd().withZone(Time.SystemT).toString()));

        TableColumn<Appointment, String> customerIdCol = new TableColumn<>("Customer ID");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        TableColumn<Appointment, String> userIdCol = new TableColumn<>("User ID");
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));


        // ------------------------------------------------------

        this.refreshAppointments();
        this.getColumns().addAll(
                idCol,
                titleCol,
                descriptionCol,
                locationCol,
                contactCol,
                typeCol,
                startCol,
                endCol,
                customerIdCol,
                userIdCol
        );
    }

    /**
     * getAppointmentFromRow fetches the Appointment from the selected row.
     *
     * @return the Appointment from the row.
     */
    public Appointment getAppointmentFromRow() {
        if (!this.getItems().isEmpty()) {
            return this.getSelectionModel().getSelectedItem();
        }
        return null;
    }

    /**
     * refreshAppointments refreshes the data stored the table.
     */
    public void refreshAppointments() {
        ObservableList<Appointment> appointments = AppointmentController.getAppointments();
        if (appointments.isEmpty()) {
            Platform.runLater(() -> Alerts.Warning("No appointments found."));
        }
        this.setItems(appointments);
        this.refresh();
    }

    /**
     * setWeeklyAppointments sets the list displayed in the table to appointments only within the
     * current week.
     */
    public void setWeeklyAppointments() {
        ObservableList<Appointment> appointments = AppointmentController.getWeeklyAppointments();
        if (appointments.isEmpty()) {
            Platform.runLater(() -> Alerts.Warning("No appointments found."));
        }
        this.setItems(appointments);
        this.refresh();
    }

    /**
     * setMonthlyAppointments sets the list displayed in the table to appointments only within the
     * current month.
     */
    public void setMonthlyAppointments() {
        ObservableList<Appointment> appointments = AppointmentController.getMonthlyAppointments();
        if (appointments.isEmpty()) {
            Platform.runLater(() -> Alerts.Warning("No appointments found."));
        }
        this.setItems(appointments);
        this.refresh();
    }
}
