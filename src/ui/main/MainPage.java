package ui.main;

import app.alerts.Alerts;
import app.controllers.AppointmentController;
import domain.stores.Appointment.Appointment;
import domain.stores.User.User;
import domain.time.Time;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * MainPage is the page displayed after a user is authenticated.
 */
public class MainPage {
    static final ResourceBundle rs = ResourceBundle.getBundle("resources.language", Locale.getDefault());
    private static final BorderPane layout = new BorderPane();
    private static User currentUser;
    private static VBox customerView = new VBox();
    private static VBox appointmentView = new VBox();
    private static VBox reportView = new VBox();
    private final Scene scene;
    private final AnchorPane root;

    /**
     * MainPage constructs a new Main Page
     *
     * @param scene the scene to populate UI components
     */
    public MainPage(Scene scene) {
        this.scene = scene;

        MainSideBar mainSideBar = new MainSideBar();

        // ------------------------------------------------------
        // Layout Setup

        layout.setLeft(mainSideBar);

        // Appointment view as a default.
        setAppointmentView();

        // ------------------------------------------------------
        root = new AnchorPane();

        AnchorPane.setTopAnchor(layout, 0.0);
        AnchorPane.setLeftAnchor(layout, 0.0);
        AnchorPane.setRightAnchor(layout, 0.0);
        AnchorPane.setBottomAnchor(layout, 0.0);


        root.setMinSize(1280, 720);
        root.getChildren().addAll(
                layout
        );
    }

    /**
     * Sets the current user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
        List<Appointment> appts = AppointmentController.getByUserId(user.getUserId());
        if (!appts.isEmpty()) {
            Time now = new Time().withZone(Time.SystemT);
            Time later = new Time().withZone(Time.SystemT).addMinutes(15);

            for (Appointment appointment : appts) {
                if (appointment.getStart().isInRange(now, later)) {
                    Platform.runLater(() -> Alerts.Info(
                            "Appointment " + appointment.getAppointmentId() + " begins at " + appointment.getStart().withZone(Time.SystemT)
                    ));
                    return;
                }
            }
        }
        Platform.runLater(() -> Alerts.Info("No upcoming appointments"));
    }

    /**
     * Sets the appointment view on the MainPage.
     */
    public static void setAppointmentView() {
        MainAppointmentView.setCurrentUser(currentUser);
        MainPage.appointmentView = new MainAppointmentView();

        if (layout.getCenter() == null) {
            layout.setCenter(MainPage.appointmentView);
        }

        if (layout.getCenter() != appointmentView) {
            layout.setCenter(MainPage.appointmentView);
        }
    }

    /**
     * Sets the customer view on the MainPage.
     */
    public static void setCustomerView() {
        MainCustomerView.setCurrentUser(currentUser);
        MainPage.customerView = new MainCustomerView();

        if (layout.getCenter() == null) {
            layout.setCenter(MainPage.appointmentView);
        }
        if (layout.getCenter() != customerView) {
            layout.setCenter(MainPage.customerView);
        }
    }

    /**
     * Sets the report view on the MainPage.
     */
    public static void setReportView() {
        MainReportView.setCurrentUser(currentUser);
        MainPage.reportView = new MainReportView();

        if (layout.getCenter() == null) {
            layout.setCenter(MainPage.appointmentView);
        }
        if (layout.getCenter() != reportView) {
            layout.setCenter(MainPage.reportView);
        }
    }

    /**
     * Displays the main page
     */
    public void ShowMainPage() {
        scene.setRoot(root);
        scene.getWindow().sizeToScene();
        scene.getWindow().centerOnScreen();
    }


}
