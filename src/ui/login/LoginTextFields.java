package ui.login;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;

import static ui.login.LoginPage.rs;

/**
 * LoginTextFields are the text fields displayed on the Login Page
 */
public class LoginTextFields extends GridPane {
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
                LoginForm.loginUser();
            }
        });

        this.add(passwordLabel, 0, 1);
        this.add(password, 1, 1);

        this.setAlignment(Pos.CENTER);
        this.setVgap(10);
        this.setHgap(10);
    }
}
