package france.uha.ensisa.fl.reversi;

import java.awt.Point;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Florent
 */
public class Reversi extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        
        Font.loadFont(getClass().getResourceAsStream("fonts/Blenda.otf"), 20);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Reversi.class.getResource("css/stylesheet.css").toString());
        Image icon = new Image(Reversi.class.getResource("images/icon.png").toString());
        stage.getIcons().add(icon);
        stage.setTitle("Reversi");
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}