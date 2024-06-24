package ui.main;

import domain.Customer;
import domain.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import ui.customers.CustomerTablePane;
import ui.users.CurrentUserInfo;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * MainPage is the page displayed after a user is authenticated.
 */
public class MainPage {
    static final ResourceBundle rs = ResourceBundle.getBundle("resources.language", Locale.getDefault());
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

        MainSideBar mainSideBar = new MainSideBar();

        // ------------------------------------------------------
        // Customer View

        VBox customerView = new VBox();

        // ------------------------------------------------------
        // Title Bar

        GridPane titleBar = new GridPane();

        Label title = new Label("Customers");
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
        titleBar.setHgap(800);

        // ------------------------------------------------------

        CustomerTablePane customerTablePane = new CustomerTablePane(currentUser);
        HBox.setHgrow(customerTablePane, Priority.ALWAYS);

        customerView.getChildren().addAll(
                titleBar,
                customerTablePane
        );

        // ------------------------------------------------------
        // Layout Setup

        layout.setLeft(mainSideBar);
        layout.setCenter(customerView);

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
