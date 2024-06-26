package ui.main;

import domain.User;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ui.users.CurrentUserInfo;

/**
 * TitleBar is the heading displayed at the top of a view
 * on the MainPage.
 *
 * @author Brandon Epperson
 */
public class TitleBar extends GridPane {

    /**
     * Constructs a new titleBar
     *
     * @param currentUser the currentUser to set
     * @param pageName    the Title displayed in a Label
     * @param Hgap        the gap between the title and the CurrentUserInfo
     */
    public TitleBar(User currentUser, String pageName, int Hgap) {
        Label title = new Label(pageName);
        title.getStyleClass().add("title");
        title.setPadding(new Insets(10, 0, 5, 25));
        title.setMaxWidth(Double.MAX_VALUE);
        title.setMaxHeight(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER_LEFT);
        this.add(title, 0, 0);

        CurrentUserInfo currentUserInfo = new CurrentUserInfo(currentUser);
        currentUserInfo.setPadding(new Insets(10, 0, 0, 0));
        currentUserInfo.setMaxHeight(Double.MAX_VALUE);
        currentUserInfo.setMaxWidth(Double.MAX_VALUE);
        currentUserInfo.setAlignment(Pos.CENTER_RIGHT);
        this.add(currentUserInfo, 1, 0);
        this.setHgap(Hgap);
    }
}
