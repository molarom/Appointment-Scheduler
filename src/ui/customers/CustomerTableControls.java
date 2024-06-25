package ui.customers;

import app.alerts.Alerts;
import app.controllers.CustomerController;
import domain.CustomerView;
import domain.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * CustomerTableControls contains the buttons for interacting with the data
 * stored in the table.
 *
 * @author Brandon Epperson
 */
public class CustomerTableControls extends HBox {
    private static User currentUser;
    private static CustomerTableView customerTableView;

    /**
     * Customer Table controls creates a new CustomerTableControls
     *
     * @param currentUser       the currentUser
     * @param customerTableView the TableView to control
     */
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

    /**
     * Add customer opens the CustomerAddPage
     */
    private void addCustomer() {
        CustomerAddPage customerAddPage = new CustomerAddPage(currentUser, customerTableView);
        customerAddPage.show();
    }

    /**
     * updateCustomer opens the UpdateCustomerPage
     */
    private void updateCustomer() {
        CustomerView customer = customerTableView.getCustomerViewFromRow();
        if (customer != null) {
            CustomerEditPage customerEditPage = new CustomerEditPage(currentUser, customer, customerTableView);
            customerEditPage.show();
        } else {
            Alerts.Warning("No customer selected.");
        }
    }

    /**
     * removeCustomer deletes a customer from the database
     */
    private void removeCustomer() {
        CustomerView customer = customerTableView.getCustomerViewFromRow();
        if (customer != null) {
            // TODO: Add check for any existing Appointments.
            if (!CustomerController.deleteCustomer(customer.getCustomerId())) {
                Alerts.Error("Failed to delete customer.");
            } else {
                Alerts.Info("Customer " + customer.getCustomerId() + " deleted.");
                customerTableView.refreshCustomers();
            }
        } else {
            Alerts.Warning("No customer selected.");
        }
    }
}
