package ui.reports;

import app.controllers.AppointmentController;
import app.controllers.CustomerController;
import domain.stores.Appointment.Appointment;
import domain.stores.Customer.CustomerReport;
import domain.stores.User.User;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ui.contacts.ContactComboBox;

import java.util.ArrayList;
import java.util.List;

/**
 * ReportsTablePane contains all the reports displayed in the application.
 *
 * @author Brandon Epperson
 */
public class ReportsTablePane extends VBox {
    /**
     * Constructs a new ReportsTablesPane
     */
    public ReportsTablePane() {

        HBox monthlyAndOther = new HBox();

        // ------------------------------------------------------
        // Monthly Report

        VBox monthlyReport = new VBox();
        Label monthlyLabel = new Label("Appointment Type By Month");
        MonthlyReportTableView monthlyReportTableView = new MonthlyReportTableView();

        monthlyReport.setSpacing(10);
        monthlyReport.getChildren().addAll(
                monthlyLabel,
                monthlyReportTableView
        );

        // ------------------------------------------------------
        // Customers Report

        VBox customersReport = new VBox();
        Label customersLabel = new Label("Customers By Country");
        PieChart customerChart = new PieChart();

        List<Data> pieChartData = new ArrayList<>();
        ObservableList<CustomerReport> customerReports = CustomerController.getCustomerReport();
        customerReports.forEach(cr -> {
            pieChartData.add(new Data(cr.getCountryName(), cr.getCount()));
        });
        customerChart.setLabelsVisible(true);
        customerChart.setStartAngle(180);
        customerChart.setData(FXCollections.observableList(pieChartData));


        customersReport.setSpacing(10);
        customersReport.getChildren().addAll(
                customersLabel,
                customerChart
        );

        // ------------------------------------------------------

        monthlyAndOther.setSpacing(10);
        monthlyAndOther.getChildren().addAll(
                monthlyReport,
                customersReport
        );
        HBox.setHgrow(monthlyReport, Priority.ALWAYS);

        // ------------------------------------------------------
        // Contact Report

        VBox contactReport = new VBox();

        Label contactLabel = new Label("Contact Schedules");
        ContactComboBox contactComboBox = new ContactComboBox();
        contactComboBox.getSelectionModel().selectFirst();
        FilteredList<Appointment> items = new FilteredList<>(AppointmentController.getAppointments());
        items.predicateProperty().bind(Bindings.createObjectBinding(() ->
                        appointment ->
                                appointment.getContactId() == contactComboBox.getSelectionModel().getSelectedItem()
                                        .getContactId()
                , contactComboBox.getSelectionModel().selectedItemProperty()));

        ContactReportTableView contactReportTableView = new ContactReportTableView();
        contactReportTableView.setItems(items);

        contactReport.setSpacing(10);
        contactReport.getChildren().addAll(
                contactLabel,
                contactComboBox,
                contactReportTableView
        );


        this.setPadding(new Insets(30, 30, 30, 28));
        this.setSpacing(20);
        this.getChildren().addAll(
                monthlyAndOther,
                contactReport
        );
    }
}
