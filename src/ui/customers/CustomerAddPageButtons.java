package ui.customers;

import app.alerts.Alerts;
import app.controllers.CustomerController;
import domain.Customer;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * CustomerAddPageButtons are the submit and cancel buttons on the add customer form.
 */
public class CustomerAddPageButtons extends HBox {
    /**
     * Constructs a new CustomerAddPageButtons
     *
     * @param window the window to close when submitted or canceled
     */
    public CustomerAddPageButtons(Stage window) {
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> {
            Customer c = CustomerInfoForm.getCustomerFromForm();
            if (c != null) {
                if (!CustomerController.addCustomer(c)) {
                    Alerts.Error("Customer " + c.getCustomerId() + " could not be added!");
                } else {
                    Alerts.Info("Customer " + c.getCustomerId() + " added successfully!");
                    window.close();
                }
            }
        });
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> window.close());
        this.setSpacing(10);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(submitButton, cancelButton);
    }
}
