import app.controllers.Controllers;
import domain.database.SQL;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ui.main.MainPage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
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
    private static final ResourceBundle rs = ResourceBundle.getBundle("resources.language", Locale.getDefault());

    @Override
    public void start(Stage primaryStage) throws Exception {
        // ------------------------------------------------------
        // Logger
        Logger logger = Logger.getLogger("App");
        logger.setLevel(Level.INFO);
        logger.setUseParentHandlers(false);

        Handler ch = new ConsoleHandler();
        ch.setFormatter(new domain.log.Formatter());
        logger.addHandler(ch);


        // ------------------------------------------------------
        // Setup the Database

        SQL db = null;
        try (FileInputStream fis = new FileInputStream("database.properties")) {
            Properties dbConfig = new Properties();
            dbConfig.load(fis);
            System.out.println(dbConfig.getProperty("database"));
            db = new SQL(
                    dbConfig.getProperty("host"),
                    dbConfig.getProperty("port"),
                    dbConfig.getProperty("database"),
                    dbConfig.getProperty("username"),
                    dbConfig.getProperty("password")
            );
        } catch (FileNotFoundException ex) {
            db = new SQL(
                    "localhost",
                    "3306",
                    "c195",
                    "root",
                    "password"
            );
        }

        logger.info("Connected to the database");

        // ------------------------------------------------------
        // Initialize Controllers

        Controllers.Setup(db, logger);

        // ------------------------------------------------------
        // Display the UI

        Scene scene = new Scene(new BorderPane());
        scene.getStylesheets().add(getClass().getResource("ui/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(485);
        primaryStage.setMinHeight(400);
        primaryStage.setTitle(rs.getString("app.title"));
        primaryStage.show();

        MainPage mainPage = new MainPage(scene);
        mainPage.ShowMainPage();
        // TODO: Don't forget to change this back
        //LoginPage login = new LoginPage(scene);
        //login.ShowLoginPage();
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
