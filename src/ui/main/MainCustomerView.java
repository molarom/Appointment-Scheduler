package ui.main;

import domain.stores.User.User;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ui.customers.CustomerTablePane;

/**
 * MainCustomerView is the view displayed on the MainPage for Customers
 *
 * @author Brandon Epperson
 */
public class MainCustomerView extends VBox {
    private static User currentUser;

    /**
     * Constructs a new MainCustomerView.
     */
    public MainCustomerView() {
        TitleBar titleBar = new TitleBar(MainCustomerView.currentUser, "Customers", 800);

        CustomerTablePane customerTablePane = new CustomerTablePane(MainCustomerView.currentUser);
        HBox.setHgrow(customerTablePane, Priority.ALWAYS);

        this.getChildren().addAll(
                titleBar,
                customerTablePane
        );
    }

    /**
     * @param currentUser the currentUser to set.
     */
    public static void setCurrentUser(User currentUser) {
        MainCustomerView.currentUser = currentUser;
    }
}
