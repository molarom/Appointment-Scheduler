package ui.customers;

import app.alerts.Alerts;
import app.controllers.CustomerViewController.CustomerViewController;
import domain.CustomerView;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * CustomerTableView displays a table containing all customer
 * documents stored in the database.
 * Uses a lambda to fire a non-blocking alert if no customers are found on startup.
 *
 * @author Brandon Epperson
 */
public class CustomerTableView extends TableView<CustomerView> {

    /**
     * CustomerTableView constructs a new CustomerTableView for use in the application.
     */
    @SuppressWarnings("unchecked")
    public CustomerTableView() {
        this.getStyleClass().add("table-view");
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // ------------------------------------------------------
        // Columns

        TableColumn<CustomerView, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        idCol.setMaxWidth(1200);

        TableColumn<CustomerView, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));

        TableColumn<CustomerView, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<CustomerView, String> addrCol = new TableColumn<>("Address");
        addrCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<CustomerView, String> postalCol = new TableColumn<>("Postal Code");
        postalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        TableColumn<CustomerView, String> divisionCol = new TableColumn<>("Division");
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));

        TableColumn<CustomerView, String> countryCol = new TableColumn<>("Country");
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<CustomerView, String> createdByCol = new TableColumn<>("Created By");
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));

        TableColumn<CustomerView, String> createdDateCol = new TableColumn<>("Created At");
        createdDateCol.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getCreateDate().toLocalString()));

        TableColumn<CustomerView, String> lastUpdateCol = new TableColumn<>("Updated At");
        lastUpdateCol.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getLastUpdate().toLocalString()));

        TableColumn<CustomerView, String> updatedByCol = new TableColumn<>("Updated By");
        updatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));


        // ------------------------------------------------------

        this.refreshCustomers();
        this.getColumns().addAll(
                idCol,
                nameCol,
                phoneCol,
                addrCol,
                postalCol,
                divisionCol,
                countryCol,
                createdByCol,
                createdDateCol,
                updatedByCol,
                lastUpdateCol
        );
    }

    /**
     * getCustomerFromRow fetches the Customer from the selected row.
     *
     * @return the CustomerView from the row.
     */
    public CustomerView getCustomerViewFromRow() {
        if (!this.getItems().isEmpty()) {
            return this.getSelectionModel().getSelectedItem();
        }
        return null;
    }

    /**
     * refreshCustomers refreshes the data stored the table.
     */
    public void refreshCustomers() {
        ObservableList<CustomerView> customers = CustomerViewController.getCustomerViews();
        if (customers.isEmpty()) {
            Platform.runLater(() -> Alerts.Warning("No customers found."));
        }
        this.setItems(customers);
    }
}
