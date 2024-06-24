package ui.login;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.time.ZoneId;

/**
 * LoginBottomBanner is the banner at the bottom of the login page.
 *
 * @author Brandon Epperson
 */
public class LoginBottomBanner extends GridPane {
    /**
     * Creates a new LoginBottomBanner to display on the Login Page
     */
    public LoginBottomBanner() {
        this.getStyleClass().add("colored-banner");
        this.setSnapToPixel(false);

        RowConstraints row = new RowConstraints(16);
        this.getRowConstraints().addAll(row);

        ColumnConstraints colorCol = new ColumnConstraints();
        colorCol.setPercentWidth(75);
        this.getColumnConstraints().add(colorCol);

        // ------------------------------------------------------

        Label zoneIdLabel = new Label(ZoneId.systemDefault().toString());
        zoneIdLabel.setPadding(new Insets(0, 2, 2, 6));
        zoneIdLabel.getStyleClass().add("zone-label");

        ColumnConstraints zoneIdCol = new ColumnConstraints();
        zoneIdCol.setPercentWidth(25);
        zoneIdCol.setHalignment(HPos.RIGHT);

        this.getColumnConstraints().add(zoneIdCol);
        this.add(zoneIdLabel, 1, 0);
    }
}
