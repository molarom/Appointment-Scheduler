package ui.login;

import app.alerts.Alerts;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import static ui.login.LoginPage.rs;

/**
 * LoginButtons are the buttons displayed on the login page.
 */
public class LoginButtons extends HBox {
    /**
     * LoginButtons constructs a new LoginButtons object
     *
     * @param loginLabel the label to display on failed login attempts.
     */
    public LoginButtons(Label loginLabel) {
        loginLabel.setVisible(false);
        loginLabel.getStyleClass().add("login-label");

        Button loginButton = new Button(rs.getString("login.login"));
        loginButton.getStyleClass().add("login-button");
        loginButton.setOnAction(event -> LoginForm.loginUser());

        Button cancelButton = new Button(rs.getString("login.cancel"));
        cancelButton.getStyleClass().add("login-button");
        cancelButton.setOnAction(event -> Alerts.Exit());

        this.setSpacing(7);
        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(loginButton, cancelButton);
    }
}
