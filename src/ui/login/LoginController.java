package ui.login;

import domain.User;
import domain.database.SQL;
import domain.stores.UserStore;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    private static final Logger logger = Logger.getLogger(LoginController.class.getName());

    private final Scene scene;
    private final AnchorPane root;
    private final TextField username;
    private final PasswordField password;
    private final UserStore userStore;


    public LoginController(Scene scene, SQL db) {
        this.scene = scene;
        this.userStore = new UserStore(db);

        // ------------------------------------------------------
        // Labels

        Label loginLabel = new Label("Welcome!");
        Label usernameLabel = new Label("Username");
        Label passwordLabel = new Label("Password");

        loginLabel.getStyleClass().add("login-label");

        // ------------------------------------------------------
        // Fields

        username = new TextField();
        password = new PasswordField();
        password.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                authenticate();
            }
        });

        // ------------------------------------------------------
        // Buttons

        Button loginButton = new Button("Login");
        Button cancelButton = new Button("Cancel");

        loginButton.setOnAction(event -> {
            authenticate();
        });
        cancelButton.setOnAction(event -> {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Exit?");
            a.setHeaderText("Are you sure you want to exit?");
            a.showAndWait();
        });

        loginButton.getStyleClass().add("login-button");
        cancelButton.getStyleClass().add("login-button");


        // ------------------------------------------------------
        // AnchorPane

        root = new AnchorPane();

        AnchorPane.setTopAnchor(loginLabel, 40.0);
        AnchorPane.setLeftAnchor(loginLabel, 105.0);

        AnchorPane.setTopAnchor(usernameLabel, 102.0);
        AnchorPane.setLeftAnchor(usernameLabel, 30.0);

        AnchorPane.setTopAnchor(username, 100.0);
        AnchorPane.setLeftAnchor(username, 95.0);

        AnchorPane.setTopAnchor(passwordLabel, 153.0);
        AnchorPane.setLeftAnchor(passwordLabel, 30.0);

        AnchorPane.setTopAnchor(password, 150.0);
        AnchorPane.setLeftAnchor(password, 95.0);

        AnchorPane.setTopAnchor(loginButton, 185.0);
        AnchorPane.setLeftAnchor(loginButton, 110.0);

        AnchorPane.setTopAnchor(cancelButton, 185.0);
        AnchorPane.setRightAnchor(cancelButton, 110.0);

        // ------------------------------------------------------

        root.getChildren().addAll(
                username,
                password,
                loginLabel,
                usernameLabel,
                passwordLabel,
                loginButton,
                cancelButton
        );
    }

    public void ShowLoginPage() {
        scene.setRoot(root);
    }

    private void authenticate() {
        User user = userStore.login(username.getText(), password.getText());
        if (user.toString() != null) {
            logger.log(Level.INFO, "successful login for {0}", username.getText());
            return;
        }
        logger.log(Level.WARNING, "failed login attempt for {0}", username.getText());
    }

}
