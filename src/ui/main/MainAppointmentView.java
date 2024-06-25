package ui.main;

import domain.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ui.appointments.AppointmentTablePane;
import ui.users.CurrentUserInfo;

public class MainAppointmentView extends VBox {
    private static User currentUser;
    public MainAppointmentView() {
        // ------------------------------------------------------
        // Title Bar

        GridPane titleBar = new GridPane();

        Label title = new Label("Appointments");
        title.getStyleClass().add("title");
        title.setPadding(new Insets(10, 0, 5, 25));
        title.setMaxWidth(Double.MAX_VALUE);
        title.setMaxHeight(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER_LEFT);
        titleBar.add(title, 0, 0);

        CurrentUserInfo currentUserInfo = new CurrentUserInfo(currentUser);
        currentUserInfo.setPadding(new Insets(10, 0, 0, 0));
        currentUserInfo.setMaxHeight(Double.MAX_VALUE);
        currentUserInfo.setMaxWidth(Double.MAX_VALUE);
        currentUserInfo.setAlignment(Pos.CENTER_RIGHT);
        titleBar.add(currentUserInfo, 1, 0);
        titleBar.setHgap(700);

        // ------------------------------------------------------

        AppointmentTablePane appointmentTablePane = new AppointmentTablePane(currentUser);
        HBox.setHgrow(appointmentTablePane, Priority.ALWAYS);

        this.getChildren().addAll(
                titleBar,
                appointmentTablePane
        );
    }

    public static void setCurrentUser(User currentUser) {
        MainAppointmentView.currentUser = currentUser;
    }
}
