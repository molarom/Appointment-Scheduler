package ui.main;

import domain.stores.User.User;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ui.reports.ReportsTablePane;

/**
 * MainReportView is the view displayed on the MainPage for reports.
 */
public class MainReportView extends VBox {
    private static User currentUser;

    public MainReportView() {
        TitleBar titleBar = new TitleBar(MainReportView.currentUser, "Reports", 800);


        // ------------------------------------------------------
        // Monthly Report Setup

        ReportsTablePane reportsTablePane = new ReportsTablePane();
        HBox.setHgrow(reportsTablePane, Priority.ALWAYS);


        this.getChildren().addAll(
                titleBar,
                reportsTablePane
        );
    }

    /**
     * @param currentUser the currentUser to set.
     */
    public static void setCurrentUser(User currentUser) {
        MainReportView.currentUser = currentUser;
    }
}
