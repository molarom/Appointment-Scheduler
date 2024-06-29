package ui.customers;

import app.controllers.CustomerController;
import domain.stores.Customer.Customer;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

/**
 * CustomerComboBox is a comboBox containing a list of Countries
 *
 * @author Brandon Epperson
 */
public class CustomerComboBox extends ComboBox<Customer> {
    /**
     * CustomerComboBox constructs a new CustomerComboBox
     */
    public CustomerComboBox() {
        this.getItems().addAll(
                CustomerController.getCustomers()
        );

        this.setCellFactory(view -> new CustomerListCell());
        this.setButtonCell(new CustomerListCell());
    }

    /**
     * Set the customer in the selection model.
     *
     * @param customer the customer object to search for.
     */
    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.getItems().forEach(val -> {
                if (val.getCustomerId() == customer.getCustomerId()) {
                    this.getSelectionModel().select(val);
                }
            });
            return;
        }
        this.getSelectionModel().clearSelection();
    }

    /**
     * CustomerListCell ensures that only the customer name is displayed
     * in the UI.
     */
    private static class CustomerListCell extends ListCell<Customer> {
        @Override
        public void updateItem(Customer item, boolean empty) {
            super.updateItem(item, empty);
            if (item != null) {
                setText(item.getName());
            } else {
                setText(null);
            }
        }
    }
}
