package ui.appointments;

import domain.stores.User.User;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

/**
 * AppointmentTablePane contains the buttons and tableview displayed in the application for
 * appointments.
 *
 * @author Brandon Epperson
 */
public class AppointmentTablePane extends VBox {
    /**
     * Constructs a new AppointmentTablePane
     * @param currentUser the current user
     */
    public AppointmentTablePane(User currentUser) {
        AppointmentTableView appointmentTableView = new AppointmentTableView();
        AppointmentTableControls appointmentTableControls = new AppointmentTableControls(currentUser, appointmentTableView);

        this.setPadding(new Insets(30, 30, 30, 28));
        this.getChildren().addAll(
                appointmentTableControls,
                appointmentTableView
        );
    }
}
