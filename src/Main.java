import domain.database.SQL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.login.LoginController;

import java.util.logging.Handler;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An application for managing appointment schedules.
 * <p>
 * Documentation is located in the root of the project directory underneath the "./docs" folder.
 * </p>
 *
 * @author Brandon Epperson
 */
public class Main extends Application {

    static Logger logger = Logger.getLogger(Main.class.getName());
    @Override
    public void start(Stage primaryStage) throws Exception {
        // ------------------------------------------------------
        // Logger

        logger.setLevel(Level.FINE);
        Handler ch = new ConsoleHandler();
        ch.setFormatter(new domain.log.Formatter());
        logger.addHandler(ch);

        // ------------------------------------------------------
        // Database

        String jdbc_url = "jdbc:mysql://localhost:3306/c195";
        String user = "root";
        String password = "password";

        SQL db = new SQL(jdbc_url, user, password);

        // ------------------------------------------------------
        // Display the UI

        Scene scene = new Scene(new BorderPane());
        scene.getStylesheets().add(getClass().getResource("ui/style.css").toExternalForm());

        LoginController login = new LoginController(scene, db);
        login.ShowLoginPage();

        primaryStage.setScene(scene);
        primaryStage.setWidth(350);
        primaryStage.setHeight(300);
        primaryStage.setTitle("Appointment Scheduler");
        primaryStage.show();
    }

    /**
     * The entrypoint of the application.
     *
     * @param args Command line arguments to pass to the program at startup.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
