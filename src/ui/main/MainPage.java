package ui.main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * MainPage is the page displayed after a user is authenticated.
 */
public class MainPage {
    private static final ResourceBundle rs = ResourceBundle.getBundle("resources.language", Locale.getDefault());
    private final Scene scene;
    private final AnchorPane root;

    /**
     * MainPage constructs a new Main Page
     *
     * @param scene the scene to populate UI components
     */
    public MainPage(Scene scene) {
        this.scene = scene;

        HBox asdf = new HBox();

        // ------------------------------------------------------
        // SideBar
        VBox mainSideBar = new VBox();
        mainSideBar.getStyleClass().add("main-sidebar");

        Button homeButton = new Button(rs.getString("main.home"));
        homeButton.getStyleClass().add("main-sidebar-button");

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
                homeButton,
                customersButton,
                appointmentsButton,
                reportsButton,
                exitButton
        );

        // ------------------------------------------------------
        // HomePage Info

        GridPane homePage = new GridPane();
        homePage.setGridLinesVisible(true);

        Label iconLabel = new Label("Icon PlaceHolder");
        homePage.add(iconLabel, 0, 0);
        Label titleLabel = new Label("Scheduler");
        homePage.add(titleLabel, 0, 1);
        Label tagLineLabel = new Label("Where scheduling customer appointments is a breeze.");
        homePage.add(tagLineLabel, 1, 0);
        Label userNameLabel = new Label("User Name");
        homePage.add(userNameLabel, 0, 2);

        homePage.getStyleClass().add("home-page");


        asdf.getChildren().addAll(
                mainSideBar,
                homePage
        );

        // ------------------------------------------------------
        root = new AnchorPane();

        AnchorPane.setTopAnchor(asdf, 0.0);
        AnchorPane.setLeftAnchor(asdf, 0.0);
        AnchorPane.setRightAnchor(asdf, 0.0);
        AnchorPane.setBottomAnchor(asdf, 0.0);


        root.setMinSize(1280, 720);
        root.getChildren().addAll(
                asdf
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
}
