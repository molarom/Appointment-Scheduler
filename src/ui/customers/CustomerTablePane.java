package ui.customers;

import domain.User;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class CustomerTablePane extends VBox {
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
