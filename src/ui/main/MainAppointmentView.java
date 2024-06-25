package ui.main;

import domain.User;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ui.appointments.AppointmentTablePane;

/**
 * MainAppointmentView is the view displayed on the MainPage for Appointments
 *
 * @author Brandon Epperson
 */
public class MainAppointmentView extends VBox {
    private static User currentUser;

    /**
     * Constructs a new MainAppointmentView
     */
    public MainAppointmentView() {
        // ------------------------------------------------------
        // Title Bar

        TitleBar titleBar = new TitleBar(MainAppointmentView.currentUser, "Appointments", 700);

        // ------------------------------------------------------

        AppointmentTablePane appointmentTablePane = new AppointmentTablePane(currentUser);
        HBox.setHgrow(appointmentTablePane, Priority.ALWAYS);

        this.getChildren().addAll(
                titleBar,
                appointmentTablePane
        );
    }

    /**
     * @param currentUser the currentUser to set.
     */
    public static void setCurrentUser(User currentUser) {
        MainAppointmentView.currentUser = currentUser;
    }
}
