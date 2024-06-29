package ui.reports;

import app.controllers.AppointmentController;
import domain.stores.Appointment.MonthlyReport;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * MonthlyReportTableView displays a table containing the number of appointment types
 * broken down by month.
 *
 * @author Brandon Epperson
 */
public class MonthlyReportTableView extends TableView<MonthlyReport> {

    /**
     * CustomerTableView constructs a new CustomerTableView for use in the application.
     */
    @SuppressWarnings("unchecked")
    public MonthlyReportTableView() {
        this.getStyleClass().add("table-view");
        this.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // ------------------------------------------------------
        // Columns

        TableColumn<MonthlyReport, Integer> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<MonthlyReport, String> monthCol = new TableColumn<>("Month");
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));

        TableColumn<MonthlyReport, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        ObservableList<MonthlyReport> monthlyReports = AppointmentController.getMonthlyReport();
        this.setItems(monthlyReports);
        this.getColumns().addAll(
                totalCol,
                monthCol,
                typeCol
        );
    }
}
