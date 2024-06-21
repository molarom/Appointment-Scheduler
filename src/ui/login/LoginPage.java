package ui.login;

import app.controllers.LoginController.LoginController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

public class LoginPage {
    private final Scene scene;
    private final AnchorPane root;
    private final TextField username;
    private final PasswordField password;

    public LoginPage(Scene scene) {
        this.scene = scene;

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
                LoginController.authenticate(username.getText(), password.getText());
            }
        });

        // ------------------------------------------------------
        // Buttons

        Button loginButton = new Button("Login");
        Button cancelButton = new Button("Cancel");

        loginButton.setOnAction(event -> {
            LoginController.authenticate(username.getText(), password.getText());
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
}
