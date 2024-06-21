package ui.login;

import app.alerts.Alerts;
import app.controllers.LoginController.LoginController;
import domain.User;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoginPage {
    private static final ResourceBundle rs = ResourceBundle.getBundle("resources.language", Locale.getDefault());
    private final Scene scene;
    private final AnchorPane root;


    public LoginPage(Scene scene) {
        this.scene = scene;

        // ------------------------------------------------------
        // Login Form

        LoginForm loginForm = new LoginForm();
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

    /**
     * LoginForm is the login form displayed on the page.
     */
    public static class LoginForm extends VBox {
        /**
         * Constructs a new login form to be displayed on the page.
         */
        public LoginForm() {
            Label welcomeLabel = new Label(rs.getString("login.welcome"));
            welcomeLabel.getStyleClass().add("welcome-label");

            TextField username = new TextField();
            PasswordField password = new PasswordField();
            LoginTextFields loginTextFields = new LoginTextFields(username, password);

            Label loginLabel = new Label(rs.getString("login.incorrect"));
            LoginButtons loginButtons = new LoginButtons(username, password, loginLabel);


            this.getChildren().addAll(
                    welcomeLabel,
                    loginTextFields,
                    loginButtons,
                    loginLabel
            );
            this.setAlignment(Pos.CENTER);
            this.setSpacing(10);
        }
    }

    /**
     * LoginTextFields are the text fields displayed on the Login Page
     */
    public static class LoginTextFields extends GridPane {
        /**
         * Constructs a new LoginTextFields object
         *
         * @param username the Username TextField
         * @param password the Password TextField
         */
        public LoginTextFields(TextField username, PasswordField password) {
            Label usernameLabel = new Label();
            usernameLabel.getStyleClass().add("user-label");

            username.setPromptText(rs.getString("login.username"));
            username.setFocusTraversable(false);

            this.add(usernameLabel, 0, 0);
            this.add(username, 1, 0);

            Label passwordLabel = new Label();
            passwordLabel.getStyleClass().add("pw-label");

            password.setPromptText(rs.getString("login.password"));
            password.setFocusTraversable(false);
            password.setOnKeyPressed(event -> {
                if (event.getCode().equals(KeyCode.ENTER)) {
                    LoginController.authenticate(username.getText(), password.getText());
                }
            });

            this.add(passwordLabel, 0, 1);
            this.add(password, 1, 1);

            this.setAlignment(Pos.CENTER);
            this.setVgap(10);
            this.setHgap(10);
        }
    }

    /**
     * LoginButtons are the buttons displayed on the login page.
     */
    public static class LoginButtons extends HBox {
        /**
         * LoginButtons constructs a new LoginButtons object
         *
         * @param username   the Username TextField
         * @param password   the PasswordField
         * @param loginLabel the label to display on failed login attempts.
         */
        public LoginButtons(TextField username, PasswordField password, Label loginLabel) {
            loginLabel.setVisible(false);
            loginLabel.getStyleClass().add("login-label");

            Button loginButton = new Button(rs.getString("login.login"));
            loginButton.getStyleClass().add("login-button");
            loginButton.setOnAction(event -> {
                User user = LoginController.authenticate(username.getText(), password.getText());
                if (user != null) {
                    System.out.println("winner");
                    return;
                }
                loginLabel.setVisible(true);
            });

            Button cancelButton = new Button(rs.getString("login.cancel"));
            cancelButton.getStyleClass().add("login-button");
            cancelButton.setOnAction(event -> {
                Alerts.Exit();
            });

            this.setSpacing(7);
            this.setAlignment(Pos.CENTER);
            this.getChildren().addAll(loginButton, cancelButton);
        }
    }

    /**
     * LoginBottomBanner is the banner at the bottom of the login page.
     */
    public static class LoginBottomBanner extends GridPane {
        /**
         * Creates a new LoginBottomBanner to display on the Login Page
         */
        public LoginBottomBanner() {
            this.getStyleClass().add("colored-banner");
            this.setSnapToPixel(false);

            RowConstraints row = new RowConstraints(16);
            this.getRowConstraints().addAll(row);

            ColumnConstraints colorCol = new ColumnConstraints();
            colorCol.setPercentWidth(75);
            this.getColumnConstraints().add(colorCol);

            // ------------------------------------------------------

            Label zoneIdLabel = new Label(ZoneId.systemDefault().toString());
            zoneIdLabel.setPadding(new Insets(0, 2, 2, 6));
            zoneIdLabel.getStyleClass().add("zone-label");

            ColumnConstraints zoneIdCol = new ColumnConstraints();
            zoneIdCol.setPercentWidth(25);
            zoneIdCol.setHalignment(HPos.RIGHT);

            this.getColumnConstraints().add(zoneIdCol);
            this.add(zoneIdLabel, 1, 0);
        }
    }

}
