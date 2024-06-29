package ui.appointments;

import domain.stores.Appointment.Appointment;
import domain.stores.User.User;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * AppointmentEditPage is the pop-up displayed to edit an appointment in the application
 *
 * @author Brandon Epperson
 */
public class AppointmentEditPage extends Stage {
    private static User currentUser;

    /**
     * Constructs a new AppointmentEditPage
     *
     * @param currentUser          the currentUser
     * @param appointment          the appointment to edit
     * @param appointmentTableView the appointmentTableView to refresh
     */
    public AppointmentEditPage(User currentUser, Appointment appointment, AppointmentTableView appointmentTableView) {
        AppointmentEditPage.currentUser = currentUser;

        AnchorPane root = new AnchorPane();

        if (!AppointmentInfoForm.setAppointment(appointment)) {
            Platform.runLater(this::close);
            return;
        }
        ;
        AppointmentInfoForm appointmentInfoForm = new AppointmentInfoForm(AppointmentEditPage.currentUser);
        AnchorPane.setTopAnchor(appointmentInfoForm, 5.0);
        AnchorPane.setBottomAnchor(appointmentInfoForm, 40.0);
        AnchorPane.setLeftAnchor(appointmentInfoForm, 5.0);
        AnchorPane.setRightAnchor(appointmentInfoForm, 5.0);

        AppointmentEditPageButtons appointmentEditPageButtons = new AppointmentEditPageButtons(this);
        AnchorPane.setBottomAnchor(appointmentEditPageButtons, 10.0);
        AnchorPane.setLeftAnchor(appointmentEditPageButtons, 5.0);
        AnchorPane.setRightAnchor(appointmentEditPageButtons, 5.0);

        root.getChildren().addAll(appointmentInfoForm, appointmentEditPageButtons);

        this.setTitle("Edit Appointment");
        Scene scene = new Scene(root);
        this.setScene(scene);

        this.setMinWidth(500);
        this.setMinHeight(500);
        this.setResizable(false);
        this.setOnHiding(e -> {
            Platform.runLater(appointmentTableView::refreshAppointments);
            AppointmentInfoForm.clearAppointmentView();
        });
    }
}
