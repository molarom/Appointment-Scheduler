package ui.users;

import domain.stores.User.User;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * CurrentUserInfo is the icon and username displayed at the top of the MainPage views.
 */
public class CurrentUserInfo extends HBox {
    private static User currentUser;

    /**
     * Constructs a new CurrentUserInfo
     * @param currentUser the user whose username will be displayed.
     */
    public CurrentUserInfo(User currentUser) {
        CurrentUserInfo.currentUser = currentUser;
        this.getStyleClass().add("home-page");

        Label iconLabel = new Label();
        iconLabel.getStyleClass().add("home-page-icon");

        Label userNameLabel = new Label(CurrentUserInfo.currentUser.getUserName());

        this.setAlignment(Pos.CENTER_RIGHT);
        this.setSpacing(10);
        this.getChildren().addAll(
                iconLabel,
                userNameLabel
        );
    }
}
