package ui.login;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * LoginPage is the page displayed to a user upon first loading the application.
 *
 * @author Brandon Epperson
 */
public class LoginPage {
    static final ResourceBundle rs = ResourceBundle.getBundle("resources.language", Locale.getDefault());
    private static Scene scene;
    private final AnchorPane root;


    /**
     * LoginPage constructs a new login page.
     *
     * @param scene the scene to populate UI components.
     */
    public LoginPage(Scene scene) {
        LoginPage.scene = scene;

        // ------------------------------------------------------
        // Login Form

        LoginForm loginForm = new LoginForm(scene);
        LoginBottomBanner loginBottomBanner = new LoginBottomBanner();

        // ------------------------------------------------------
        // Root Node

        root = new AnchorPane();

        AnchorPane.setRightAnchor(loginForm, 0.0);
        AnchorPane.setLeftAnchor(loginForm, 0.0);
        AnchorPane.setBottomAnchor(loginForm, 0.0);
        AnchorPane.setTopAnchor(loginForm, 0.0);

        AnchorPane.setBottomAnchor(loginBottomBanner, 0.0);
        AnchorPane.setLeftAnchor(loginBottomBanner, 0.0);
        AnchorPane.setRightAnchor(loginBottomBanner, 0.0);

        // ------------------------------------------------------

        root.prefHeightProperty().bind(scene.heightProperty());
        root.prefWidthProperty().bind(scene.widthProperty());
        root.getChildren().addAll(
                loginForm,
                loginBottomBanner
        );
    }

    /**
     * Displays the Login page.
     */
    public void ShowLoginPage() {
        scene.setRoot(root);
    }


}
