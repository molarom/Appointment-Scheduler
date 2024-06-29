package ui.customers;

import domain.stores.User.User;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

/**
 * CustomerTablePane contains the buttons and tableview displayed in the application.
 */
public class CustomerTablePane extends VBox {
    /**
     * Constructs a new CustomerTablePane
     * @param currentUser the user to reference in the current context.
     */
    public CustomerTablePane(User currentUser) {
        CustomerTableView customerTableView = new CustomerTableView();
        CustomerTableControls customerTableControls = new CustomerTableControls(currentUser, customerTableView);

        this.setPadding(new Insets(30, 30, 30, 28));
        this.getChildren().addAll(
                customerTableControls,
                customerTableView
        );
    }
}
