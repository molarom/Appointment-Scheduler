package ui.main;

import domain.User;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
     * Displays the main page
     */
    public void ShowMainPage() {
        scene.setRoot(root);
        scene.getWindow().sizeToScene();
        scene.getWindow().centerOnScreen();
    }


}
