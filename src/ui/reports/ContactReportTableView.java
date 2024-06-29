package ui.reports;

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
 * ContactReportTableView displays a table containing appointments associated with a contact.
 *
 * @author Brandon Epperson
 */
public class ContactReportTableView extends TableView<Appointment> {
    ObservableList<Contact> contacts;

    /**
     * ContactReportTableView constructs a new ContactReportTableView for use in the application.
     */
    @SuppressWarnings("unchecked")
    public ContactReportTableView() {
        this.getStyleClass().add("table-view");
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.contacts = ContactController.getAllContacts();

        // ------------------------------------------------------
        // Columns

        TableColumn<Appointment, Integer> idCol = new TableColumn<>("Appointment ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        idCol.setMaxWidth(1200);

        TableColumn<Appointment, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Appointment, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Appointment, String> descriptionCol = new TableColumn<>("Description");
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Appointment, String> contactCol = new TableColumn<>("Contact");
        contactCol.setCellValueFactory(param -> {
            for (Contact contact : contacts) {
                if (contact.getContactId() == param.getValue().getContactId()) {
                    return new ReadOnlyStringWrapper(contact.getContactName());
                }
            }
            return new ReadOnlyStringWrapper("invalid contact name");
        });

        TableColumn<Appointment, String> startCol = new TableColumn<>("Start Date");
        startCol.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getStart().withZone(Time.SystemT).toString()));

        TableColumn<Appointment, String> endCol = new TableColumn<>("End Date");
        endCol.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getEnd().withZone(Time.SystemT).toString()));

        TableColumn<Appointment, String> customerIdCol = new TableColumn<>("Customer ID");
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));

        // ------------------------------------------------------

        this.refreshAppointments();
        this.getColumns().addAll(
                contactCol,
                idCol,
                titleCol,
                typeCol,
                descriptionCol,
                startCol,
                endCol,
                customerIdCol
        );
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
}
