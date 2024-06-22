package app.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Alerts provides methods for displaying common alerts.
 *
 * @author Brandon Epperson
 */
public class Alerts {
    private static final ResourceBundle rs = ResourceBundle.getBundle("resources.language", Locale.getDefault());

    public static void Warning(String content) {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setContentText(content);
        a.showAndWait();
    }

    /**
     * Exit displays an alert confirming that the user
     * wants to exit.
     */
    public static void Exit() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle(rs.getString("alert.exit.title"));
        a.setHeaderText(rs.getString("alert.exit.header"));

        a.showAndWait()
                .filter(response -> response == ButtonType.OK)
                .ifPresent(response -> javafx.application.Platform.exit());
    }
}
