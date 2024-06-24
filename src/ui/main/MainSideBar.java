package ui.main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import static ui.main.MainPage.rs;

public class MainSideBar extends VBox {
    public MainSideBar() {


        Label sideBarIconLabel = new Label();
        sideBarIconLabel.getStyleClass().add("scheduler-icon");

        Button customersButton = new Button(rs.getString("main.customers"));
        customersButton.getStyleClass().add("main-sidebar-button");

        Button appointmentsButton = new Button(rs.getString("main.appointments"));
        appointmentsButton.getStyleClass().add("main-sidebar-button");

        Button reportsButton = new Button(rs.getString("main.reports"));
        reportsButton.getStyleClass().add("main-sidebar-button");

        Button exitButton = new Button(rs.getString("main.exit"));
        exitButton.getStyleClass().add("main-sidebar-button");


        this.setSpacing(10);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.getStyleClass().add("main-sidebar");
        this.getChildren().addAll(
                sideBarIconLabel,
                customersButton,
                appointmentsButton,
                reportsButton,
                exitButton
        );
    }
}
