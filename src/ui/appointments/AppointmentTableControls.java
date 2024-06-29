package ui.appointments;

import app.alerts.Alerts;
import app.controllers.AppointmentController;
import domain.stores.Appointment.Appointment;
import domain.stores.User.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

/**
 * AppointmentTableControls contains the buttons for interacting with the data
 * stored in the table.
 *
 * @author Brandon Epperson
 */
public class AppointmentTableControls extends HBox {
    private static User currentUser;
    private static AppointmentTableView appointmentTableView;

    /**
     * Appointment Table controls creates a new AppointmentTableControls
     *
     * @param currentUser          the currentUser
     * @param appointmentTableView the TableView to control
     */
    public AppointmentTableControls(User currentUser, AppointmentTableView appointmentTableView) {
        AppointmentTableControls.currentUser = currentUser;
        AppointmentTableControls.appointmentTableView = appointmentTableView;

        Button addButton = new Button("Add");
        addButton.setAlignment(Pos.CENTER_LEFT);
        addButton.getStyleClass().add("table-button");
        addButton.setOnAction(event -> addAppointment());

        Button updateButton = new Button("Update");
        updateButton.setAlignment(Pos.CENTER_LEFT);
        updateButton.getStyleClass().add("table-button");
        updateButton.setOnAction(event -> updateAppointment());

        Button removeButton = new Button("Remove");
        removeButton.setAlignment(Pos.CENTER_LEFT);
        removeButton.getStyleClass().add("table-button");
        removeButton.setOnAction(event -> removeAppointment());

        ToggleGroup tg = new ToggleGroup();

        RadioButton allButton = new RadioButton("All");
        allButton.setSelected(true);
        allButton.setAlignment(Pos.CENTER_LEFT);
        allButton.getStyleClass().add("radio-button");
        allButton.setOnAction(event -> setAllList());

        RadioButton weeklyButton = new RadioButton("This Week");
        weeklyButton.setAlignment(Pos.CENTER_LEFT);
        weeklyButton.getStyleClass().add("radio-button");
        weeklyButton.setOnAction(event -> setWeeklyList());

        RadioButton monthlyButton = new RadioButton("This Month");
        monthlyButton.setAlignment(Pos.CENTER_LEFT);
        monthlyButton.getStyleClass().add("radio-button");
        monthlyButton.setOnAction(event -> setMonthlyList());

        allButton.setToggleGroup(tg);
        weeklyButton.setToggleGroup(tg);
        monthlyButton.setToggleGroup(tg);

        this.setSpacing(10);
        this.setPadding(new Insets(10, 0, 10, 0));
        this.getChildren().addAll(
                addButton,
                updateButton,
                removeButton,
                allButton,
                weeklyButton,
                monthlyButton
        );
    }

    /**
     * Add appointment opens the AppointmentAddPage
     */
    private void addAppointment() {
        AppointmentAddPage appointmentAddPage = new AppointmentAddPage(currentUser, appointmentTableView);
        appointmentAddPage.show();
    }

    /**
     * updateAppointment opens the UpdateAppointmentPage
     */
    private void updateAppointment() {
        Appointment appointment = appointmentTableView.getAppointmentFromRow();
        if (appointment != null) {
            AppointmentEditPage appointmentEditPage = new AppointmentEditPage(currentUser, appointment, appointmentTableView);
            appointmentEditPage.show();
        } else {
            Alerts.Warning("No appointment selected.");
        }
    }

    /**
     * removeAppointment deletes an appointment from the database
     */
    private void removeAppointment() {
        Appointment appointment = appointmentTableView.getAppointmentFromRow();
        if (appointment != null) {
            if (!AppointmentController.deleteAppointment(appointment.getAppointmentId())) {
                Alerts.Error("Failed to delete appointment.");
            } else {
                Alerts.Info("Appointment " + appointment.getAppointmentId() + " deleted.");
                appointmentTableView.refreshAppointments();
            }
        } else {
            Alerts.Warning("No appointment selected.");
        }
    }

    /**
     * setAllList displays all appointments
     */
    private void setAllList() {
        appointmentTableView.refreshAppointments();
    }

    /**
     * setWeeklyList filters the tableview to show the appointments in the current week.
     */
    private void setWeeklyList() {
        appointmentTableView.setWeeklyAppointments();
    }

    /**
     * setMonthlyList filters the tableview to show the appointments in the current month.
     */
    private void setMonthlyList() {
        appointmentTableView.setMonthlyAppointments();
    }
}
