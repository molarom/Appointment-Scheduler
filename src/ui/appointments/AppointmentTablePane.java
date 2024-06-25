package ui.appointments;

import domain.User;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class AppointmentTablePane extends VBox {
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
