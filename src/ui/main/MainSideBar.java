package ui.main;

import app.alerts.Alerts;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static ui.main.MainPage.rs;

/**
 * MainSideBar is the sidebar displayed on the MainPage.
 *
 * @author Brandon Epperson
 */
public class MainSideBar extends VBox {
    /**
     * Constructs a new MainSideBar
     */
    public MainSideBar() {


        Label sideBarIconLabel = new Label();
        sideBarIconLabel.getStyleClass().add("scheduler-icon");

        Button customersButton = new Button(rs.getString("main.customers"));
        customersButton.getStyleClass().add("main-sidebar-button");
        customersButton.setOnAction(e -> MainPage.setCustomerView());

        Button appointmentsButton = new Button(rs.getString("main.appointments"));
        appointmentsButton.getStyleClass().add("main-sidebar-button");
        appointmentsButton.setOnAction(e -> MainPage.setAppointmentView());

        Button reportsButton = new Button(rs.getString("main.reports"));
        reportsButton.getStyleClass().add("main-sidebar-button");
        reportsButton.setOnAction(e -> MainPage.setReportView());

        Button exitButton = new Button(rs.getString("main.exit"));
        exitButton.getStyleClass().add("main-sidebar-button");
        exitButton.setOnAction(e -> Alerts.Exit());


        this.setSpacing(10);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getStyleClass().add("main-sidebar");
        this.getChildren().addAll(
                sideBarIconLabel,
                appointmentsButton,
                customersButton,
                reportsButton,
                exitButton
        );
    }
}
