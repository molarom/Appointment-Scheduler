package ui.customers;

import domain.User;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerAddPage extends Stage {
    private static User currentUser;
    private final Scene scene;

    public CustomerAddPage(User currentUser) {
        CustomerAddPage.currentUser = currentUser;
        this.setTitle("Add Customer");


        // ------------------------------------------------------
        CustomerInfoForm customerInfoForm = new CustomerInfoForm(CustomerAddPage.currentUser);


        AnchorPane root = new AnchorPane();

        AnchorPane.setTopAnchor(customerInfoForm, 5.0);
        AnchorPane.setBottomAnchor(customerInfoForm, 5.0);
        AnchorPane.setLeftAnchor(customerInfoForm, 5.0);
        AnchorPane.setRightAnchor(customerInfoForm, 5.0);

        root.getChildren().add(customerInfoForm);


        this.scene = new Scene(root);
        this.setScene(scene);

        this.setMinWidth(500);
        this.setMinHeight(500);
        this.setResizable(false);
    }
}
