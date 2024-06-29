package ui.appointments;

import app.alerts.Alerts;
import app.controllers.AppointmentController;
import domain.stores.Appointment.Appointment;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * AppointmentEditPageButtons are the submit and cancel buttons on the edit appointment form.
 */
public class AppointmentEditPageButtons extends HBox {
    /**
     * Constructs a new AppointmentEditPageButtons
     *
     * @param window the window to close when submitted or canceled
     */
    public AppointmentEditPageButtons(Stage window) {
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            Appointment c = AppointmentInfoForm.getAppointmentFromForm();
            if (c != null) {
                if (!AppointmentController.updateAppointment(c)) {
                    Alerts.Error("Appointment " + c.getAppointmentId() + " could not be updated!");
                } else {
                    Alerts.Info("Appointment " + c.getAppointmentId() + " added successfully!");
                    window.close();
                }
            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());
        this.setSpacing(10);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(submitButton, cancelButton);
    }
}
