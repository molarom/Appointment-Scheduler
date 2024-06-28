package ui.appointments;

import domain.Appointment;
import domain.User;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AppointmentEditPage extends Stage {
    private static User currentUser;

    public AppointmentEditPage(User currentUser, Appointment appointment, AppointmentTableView appointmentTableView) {
        AppointmentEditPage.currentUser = currentUser;

        AnchorPane root = new AnchorPane();

        AppointmentInfoForm.setAppointment(appointment);
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
