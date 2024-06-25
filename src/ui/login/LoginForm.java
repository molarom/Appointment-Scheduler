package ui.login;

import app.controllers.LoginController;
import domain.User;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import ui.main.MainPage;

import static ui.login.LoginPage.rs;

/**
 * LoginForm is the login form displayed on the page.
 *
 * @author Brandon Epperson
 */
class LoginForm extends VBox {
    private static TextField username;
    private static PasswordField password;
    private static Label loginLabel;
    private static Scene scene;

    /**
     * Constructs a new login form to be displayed on the page.
     */
    public LoginForm(Scene scene) {
        LoginForm.scene = scene;
        Label welcomeLabel = new Label(rs.getString("login.welcome"));
        welcomeLabel.getStyleClass().add("welcome-label");

        username = new TextField();
        password = new PasswordField();
        LoginTextFields loginTextFields = new LoginTextFields(username, password);

        loginLabel = new Label(rs.getString("login.incorrect"));
        LoginButtons loginButtons = new LoginButtons(loginLabel);

        this.getChildren().addAll(
                welcomeLabel,
                loginTextFields,
                loginButtons,
                loginLabel
        );
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
    }

    /**
     * loginUser attempts to log in a user
     */
    public static void loginUser() {
        User user = LoginController.authenticate(username.getText(), password.getText());
        if (user != null) {
            MainPage.setCurrentUser(user);
            MainPage mainPage = new MainPage(scene);
            mainPage.ShowMainPage();
        }
        loginLabel.setVisible(true);
    }
}
