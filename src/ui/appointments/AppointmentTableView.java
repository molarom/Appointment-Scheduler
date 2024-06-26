package ui.appointments;

import app.alerts.Alerts;
import app.controllers.AppointmentController;
import domain.Appointment;
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

    /**
     * AppointmentTableView constructs a new AppointmentTableView for use in the application.
     */
    @SuppressWarnings("unchecked")
    public AppointmentTableView() {
        this.getStyleClass().add("table-view");
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactId"));

        TableColumn<Appointment, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Appointment, String> startCol = new TableColumn<>("Start Date");
        startCol.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getLastUpdate().toString()));

        TableColumn<Appointment, String> endCol = new TableColumn<>("End Date");
        endCol.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getLastUpdate().toString()));

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
}
