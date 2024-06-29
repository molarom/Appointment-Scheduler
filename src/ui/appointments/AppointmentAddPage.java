package ui.appointments;

import domain.stores.User.User;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * AppointmentAddPage is the pop-up displayed in the application for adding a new appointment.
 *
 * @author Brandon Epperson
 */
public class AppointmentAddPage extends Stage {
    private static User currentUser;

    /**
     * Constructs a new AppointmentAddPage
     * @param currentUser the current user
     * @param appointmentTableView the appointmentTableView to refresh
     */
    public AppointmentAddPage(User currentUser, AppointmentTableView appointmentTableView) {
        AppointmentAddPage.currentUser = currentUser;

        AnchorPane root = new AnchorPane();

        AppointmentInfoForm appointmentInfoForm = new AppointmentInfoForm(AppointmentAddPage.currentUser);
        AnchorPane.setTopAnchor(appointmentInfoForm, 5.0);
        AnchorPane.setBottomAnchor(appointmentInfoForm, 40.0);
        AnchorPane.setLeftAnchor(appointmentInfoForm, 5.0);
        AnchorPane.setRightAnchor(appointmentInfoForm, 5.0);

        AppointmentAddPageButtons appointmentAddPageButtons = new AppointmentAddPageButtons(this);
        AnchorPane.setBottomAnchor(appointmentAddPageButtons, 0.0);
        AnchorPane.setLeftAnchor(appointmentAddPageButtons, 5.0);
        AnchorPane.setRightAnchor(appointmentAddPageButtons, 5.0);

        root.setPadding(new Insets(10));
        root.getChildren().addAll(appointmentInfoForm, appointmentAddPageButtons);

        this.setTitle("Add Appointment");
        Scene scene = new Scene(root);
        this.setScene(scene);

        this.setMinWidth(500);
        this.setMinHeight(700);
        this.setResizable(false);
        this.setOnHiding(e -> {
            Platform.runLater(appointmentTableView::refreshAppointments);
            AppointmentInfoForm.clearAppointmentView();
        });
    }
}
