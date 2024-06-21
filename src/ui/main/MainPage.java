package ui.main;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainPage {
    private static final ResourceBundle rs = ResourceBundle.getBundle("resources.language", Locale.getDefault());
    private final Scene scene;
    private final AnchorPane root;

    public MainPage(Scene scene) {
        this.scene = scene;

        root = new AnchorPane();

        Label title = new Label("Main page");
        AnchorPane.setTopAnchor(title, 10.0);
        AnchorPane.setLeftAnchor(title, 10.0);

        root.setMinSize(1280, 720);
        root.getChildren().add(title);
    }

    public void ShowMainPage() {
        scene.setRoot(root);
    }
}
