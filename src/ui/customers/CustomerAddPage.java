package ui.customers;

import domain.User;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerAddPage extends Stage {
    private static User currentUser;

    public CustomerAddPage(User currentUser, CustomerTableView customerTableView) {
        CustomerAddPage.currentUser = currentUser;

        AnchorPane root = new AnchorPane();

        CustomerInfoForm customerInfoForm = new CustomerInfoForm(CustomerAddPage.currentUser);
        AnchorPane.setTopAnchor(customerInfoForm, 5.0);
        AnchorPane.setBottomAnchor(customerInfoForm, 40.0);
        AnchorPane.setLeftAnchor(customerInfoForm, 5.0);
        AnchorPane.setRightAnchor(customerInfoForm, 5.0);

        CustomerAddPageButtons customerAddPageButtons = new CustomerAddPageButtons(this);
        AnchorPane.setBottomAnchor(customerAddPageButtons, 10.0);
        AnchorPane.setLeftAnchor(customerAddPageButtons, 5.0);
        AnchorPane.setRightAnchor(customerAddPageButtons, 5.0);

        root.getChildren().addAll(customerInfoForm, customerAddPageButtons);

        this.setTitle("Add Customer");
        Scene scene = new Scene(root);
        this.setScene(scene);

        this.setMinWidth(500);
        this.setMinHeight(500);
        this.setResizable(false);
        this.setOnHiding(e -> {
            Platform.runLater(customerTableView::refreshCustomers);
        });
    }
}
