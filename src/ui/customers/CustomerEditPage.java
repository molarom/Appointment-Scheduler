package ui.customers;

import domain.stores.Customer.CustomerView;
import domain.stores.User.User;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * CustomerEditPage is the pop-up page displayed to edit an existing customer.
 */
public class CustomerEditPage extends Stage {
    private static User currentUser;

    /**
     * Construct a new CustomerEditPage
     *
     * @param currentUser       the current user
     * @param customerView      the CustomerView to edit
     * @param customerTableView the customerTableView to reference for refresh
     */
    public CustomerEditPage(User currentUser, CustomerView customerView, CustomerTableView customerTableView) {
        CustomerEditPage.currentUser = currentUser;

        CustomerInfoForm.setCustomerView(customerView);
        CustomerInfoForm customerInfoForm = new CustomerInfoForm(CustomerEditPage.currentUser);

        AnchorPane root = new AnchorPane();

        AnchorPane.setTopAnchor(customerInfoForm, 5.0);
        AnchorPane.setBottomAnchor(customerInfoForm, 40.0);
        AnchorPane.setLeftAnchor(customerInfoForm, 5.0);
        AnchorPane.setRightAnchor(customerInfoForm, 5.0);

        CustomerEditPageButtons customerEditPageButtons = new CustomerEditPageButtons(this);
        AnchorPane.setBottomAnchor(customerEditPageButtons, 10.0);
        AnchorPane.setLeftAnchor(customerEditPageButtons, 5.0);
        AnchorPane.setRightAnchor(customerEditPageButtons, 5.0);

        root.getChildren().addAll(customerInfoForm, customerEditPageButtons);

        this.setTitle("Edit Customer");
        Scene scene = new Scene(root);
        this.setScene(scene);

        this.setMinWidth(500);
        this.setMinHeight(500);
        this.setResizable(false);
        this.setOnHiding(e -> {
            CustomerInfoForm.clearCustomerView();
            Platform.runLater(customerTableView::refreshCustomers);
        });
    }
}
