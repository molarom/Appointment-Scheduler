package ui.appointments;

import app.alerts.Alerts;
import app.controllers.AppointmentController;
import domain.Appointment;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * AppointmentAddPageButtons are the submit and cancel buttons on the add appointment form.
 */
public class AppointmentAddPageButtons extends HBox {
    /**
     * Constructs a new AppointmentAddPageButtons
     *
     * @param window the window to close when submitted or canceled
     */
    public AppointmentAddPageButtons(Stage window) {
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            Appointment a = AppointmentInfoForm.getAppointmentFromForm();
            if (a != null) {
                System.out.println("appointment: " + a);
                //if (!AppointmentController.addAppointment(a)) {
                //    Alerts.Error("Appointment " + a.getAppointmentId() + " could not be added!");
                //} else {
                //    Alerts.Info("Appointment " + a.getAppointmentId() + " added successfully!");
                //    window.close();
                //}
            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());
        this.setSpacing(10);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(submitButton, cancelButton);
    }
}
