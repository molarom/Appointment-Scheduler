package ui.users;

import app.controllers.UserController;
import domain.User;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

/**
 * UserComboBox is a comboBox containing a list of Countries
 *
 * @author Brandon Epperson
 */
public class UserComboBox extends ComboBox<User> {
    /**
     * UserComboBox constructs a new UserComboBox
     */
    public UserComboBox() {
        this.getItems().addAll(
                UserController.getAll()
        );

        this.setCellFactory(view -> new UserListCell());
        this.setButtonCell(new UserListCell());
    }

    /**
     * Set the user in the selection model.
     *
     * @param user the user object to search for.
     */
    public void setUser(User user) {
        if (user != null) {
            this.getItems().forEach(val -> {
                if (val.getUserId() == user.getUserId()) {
                    this.getSelectionModel().select(val);
                }
            });
            return;
        }
        this.getSelectionModel().clearSelection();
    }

    /**
     * UserListCell ensures that only the username is displayed
     * in the UI.
     */
    private static class UserListCell extends ListCell<User> {
        @Override
        public void updateItem(User item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getUserName());
            } else {
                setText(null);
            }
        }
    }
}
