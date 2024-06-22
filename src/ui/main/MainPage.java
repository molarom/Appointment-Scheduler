package ui.main;

import domain.Customer;
import domain.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import ui.customers.CustomerTableView;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * MainPage is the page displayed after a user is authenticated.
 */
public class MainPage {
    private static final ResourceBundle rs = ResourceBundle.getBundle("resources.language", Locale.getDefault());
    private final Scene scene;
    private final AnchorPane root;
    private static User currentUser;
    // TODO: Clean this up.
    private static List<Customer> customerList;

    /**
     * MainPage constructs a new Main Page
     *
     * @param scene the scene to populate UI components
     */
    public MainPage(Scene scene) {
        this.scene = scene;

        BorderPane layout = new BorderPane();

        // ------------------------------------------------------
        // SideBar
        VBox mainSideBar = new VBox();
        mainSideBar.getStyleClass().add("main-sidebar");

        Label sideBarIconLabel = new Label();
        sideBarIconLabel.getStyleClass().add("scheduler-icon");
        sideBarIconLabel.setPadding(new Insets(20, 20, 20, 20));

        Button customersButton = new Button(rs.getString("main.customers"));
        customersButton.getStyleClass().add("main-sidebar-button");

        Button appointmentsButton = new Button(rs.getString("main.appointments"));
        appointmentsButton.getStyleClass().add("main-sidebar-button");

        Button reportsButton = new Button(rs.getString("main.reports"));
        reportsButton.getStyleClass().add("main-sidebar-button");

        Button exitButton = new Button(rs.getString("main.exit"));
        exitButton.getStyleClass().add("main-sidebar-button");

        mainSideBar.setSpacing(10);
        mainSideBar.setAlignment(Pos.TOP_LEFT);

        mainSideBar.getChildren().addAll(
                sideBarIconLabel,
                customersButton,
                appointmentsButton,
                reportsButton,
                exitButton
        );

        // ------------------------------------------------------
        // Customer Records

        VBox customerRecords = new VBox();

        // ------------------------------------------------------
        // Title Bar

        HBox titleBar = new HBox();


        // ------------------------------------------------------
        // User Info

        HBox userInfo = new HBox();
        userInfo.getStyleClass().add("home-page");

        Label iconLabel = new Label();
        iconLabel.getStyleClass().add("home-page-icon");

        Label userNameLabel = new Label(currentUser.getUserName());

        userInfo.setAlignment(Pos.CENTER_RIGHT);
        userInfo.setSpacing(10);
        userInfo.setPadding(new Insets(20, 20, 10, 20));
        userInfo.getChildren().addAll(
                iconLabel,
                userNameLabel
        );


        // ------------------------------------------------------
        // TableView

        CustomerTableView customerTableView = new CustomerTableView();

        HBox.setHgrow(customerTableView, Priority.ALWAYS);

        // ------------------------------------------------------

        customerRecords.getChildren().addAll(
                userInfo,
                customerTableView
        );

        // ------------------------------------------------------
        // Layout Setup

        layout.setLeft(mainSideBar);
        layout.setCenter(customerRecords);

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
     * Displays the main page
     */
    public void ShowMainPage() {
        scene.setRoot(root);
        scene.getWindow().sizeToScene();
        scene.getWindow().centerOnScreen();
    }

    /**
     * Sets the current user
     */
    public static void setCurrentUser(User user) {
        currentUser = user;
    }

}
