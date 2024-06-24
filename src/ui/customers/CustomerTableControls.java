package ui.customers;

import app.alerts.Alerts;
import domain.CustomerView;
import domain.User;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class CustomerTableControls extends HBox {
    private static User currentUser;
    private static CustomerTableView customerTableView;

    public CustomerTableControls(User currentUser, CustomerTableView customerTableView) {
        CustomerTableControls.currentUser = currentUser;
        CustomerTableControls.customerTableView = customerTableView;

        Button addButton = new Button("Add");
        addButton.setAlignment(Pos.CENTER_LEFT);
        addButton.getStyleClass().add("table-button");
        addButton.setOnAction(event -> addCustomer());

        Button updateButton = new Button("Update");
        updateButton.setAlignment(Pos.CENTER_LEFT);
        updateButton.getStyleClass().add("table-button");
        updateButton.setOnAction(event -> updateCustomer());

        Button removeButton = new Button("Remove");
        removeButton.setAlignment(Pos.CENTER_LEFT);
        removeButton.getStyleClass().add("table-button");
        removeButton.setOnAction(event -> removeCustomer());

        this.setSpacing(10);
        this.setPadding(new Insets(10, 0, 10, 0));
        this.getChildren().addAll(
                addButton,
                updateButton,
                removeButton
        );
    }

    private void addCustomer() {
        CustomerAddPage customerAddPage = new CustomerAddPage(currentUser);
        customerAddPage.show();
    }

    private void updateCustomer() {
        CustomerView customer = customerTableView.getCustomerViewFromRow();
        if (customer != null) {
            System.out.println("Update customer" + customer.toString());
            return;
        }
        Platform.runLater(() -> Alerts.Warning("No customer selected."));
    }

    private void removeCustomer() {
        CustomerView customer = customerTableView.getCustomerViewFromRow();
        if (customer != null) {
            System.out.println("Remove customer" + customer.toString());
            return;
        }
        Platform.runLater(() -> Alerts.Warning("No customer selected."));
    }
}
