package ui.customers;

import app.alerts.Alerts;
import app.controllers.CustomerController.CustomerController;
import domain.Customer;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerTableView extends TableView<Customer> {

    @SuppressWarnings("unchecked")
    public CustomerTableView() {
        this.getStyleClass().add("table-view");
        this.setEditable(true);
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        this.setPadding(new Insets(30, 30, 30, 28));

        // ------------------------------------------------------
        // Columns

        TableColumn<Customer, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        idCol.setPrefWidth(50);

        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Customer, String> addrCol = new TableColumn<>("Address");
        addrCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Customer, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Customer, String> createdByCol = new TableColumn<>("Created By");
        createdByCol.setCellValueFactory(new PropertyValueFactory<>("createdBy"));

        TableColumn<Customer, String> createdDateCol = new TableColumn<>("Created Date");
        createdDateCol.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getCreateDate().toLocalString()));

        TableColumn<Customer, String> updatedByCol = new TableColumn<>("Updated By");
        updatedByCol.setCellValueFactory(new PropertyValueFactory<>("lastUpdatedBy"));

        TableColumn<Customer, String> updatedDateCol = new TableColumn<>("Updated Date");
        updatedDateCol.setCellValueFactory(param ->
                new ReadOnlyStringWrapper(param.getValue().getCreateDate().toLocalString()));

        // ------------------------------------------------------

        ObservableList<Customer> customers = CustomerController.getCustomers();
        if (customers.isEmpty()) {
            Platform.runLater(() -> Alerts.Warning("No customers found."));
        }
        this.setItems(customers);
        this.getColumns().addAll(
                idCol,
                nameCol,
                addrCol,
                phoneCol,
                createdByCol,
                createdDateCol,
                updatedByCol,
                updatedDateCol
        );
    }
}
