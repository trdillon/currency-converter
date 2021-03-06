import config.Config;
import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.ViewUtil;

import java.io.IOException;

public class Main extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    private static Stage pStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        setPrimaryStage(primaryStage);
        getPrimaryStage().setTitle("Currency Converter");
        getPrimaryStage().initStyle(StageStyle.UNDECORATED); // no Windows border
        getPrimaryStage().getProperties().put("hostServices", this.getHostServices()); // inject for opening links in browser
        getPrimaryStage().setScene(createScene(loadMainPane()));
        getPrimaryStage().show();
    }

    // Static stage is used for draggable method
    private static Stage getPrimaryStage() {
        return pStage;
    }

    private void setPrimaryStage(Stage pStage) {
        Main.pStage = pStage;
    }

    // Load the main view with toolbar and sidebar
    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = loader.load(getClass().getResourceAsStream(ViewUtil.MAIN));

        // Make the window draggable
        mainPane.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        mainPane.setOnMouseDragged(event -> {
            getPrimaryStage().setX(event.getScreenX() - xOffset);
            getPrimaryStage().setY(event.getScreenY() - yOffset);
        });


        MainController mainController = loader.getController();
        ViewUtil.setMainController(mainController);
        ViewUtil.loadView(ViewUtil.HOME);
        ViewUtil.loadBars();

        return mainPane;
    }

    // Create the main scene and add stylesheets
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(mainPane);
        scene.getStylesheets().setAll(getClass().getResource("/css/Styles.css").toExternalForm());

        // Check config for theme and load it
        try {
            String theme = Config.getTheme();
            if (theme.equals("Light")) {
                scene.getStylesheets().add(getClass().getResource("/css/theme-light.css").toExternalForm());
            }
            if (theme.equals("Dark")) {
                scene.getStylesheets().add(getClass().getResource("/css/theme-dark.css").toExternalForm());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
