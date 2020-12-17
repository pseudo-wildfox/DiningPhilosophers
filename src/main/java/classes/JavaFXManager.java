package classes;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Log4j
public class JavaFXManager {

    private static JavaFXManager manager;
    static {
        manager = new JavaFXManager();
    }

    public Stage stage = new Stage();
    public Group root;

    private JavaFXManager() {
        this.stage = new Stage();
        //this.root =

    }

    public static JavaFXManager getInstance() {
        return manager;
    }

    public void start(Stage primaryStage) {
        root = new Group(drawPhilosophers());
        root.getChildren().add(drawFurniture());
        stage.setTitle("Dining philosophers");
        //StackPane layout = new StackPane();
        stage.setScene(new Scene(root, 500, 500));
        stage.show();


        Label label = new Label("My Label");
        root.getChildren().add(label);
    }
    private ImageView[] drawPhilosophers() {
        ImageView[] imageViews = new ImageView[Philosopher.names.length];
        for (int i=0; i< Philosopher.names.length; i++) {
            imageViews[i] = loadImageView(Philosopher.names[i], 90, Philosopher.coordinates[i][0], Philosopher.coordinates[i][1]);
        }
        return imageViews;
    }

    private ImageView loadImageView(String name, int size, int x, int y) {
        try (InputStream stream = new FileInputStream("src\\main\\resources\\images\\" + name + ".png")) {
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(stream));
            imageView.setX(x);
            imageView.setY(y);
            imageView.setFitWidth(size);
            imageView.setPreserveRatio(true);
            return imageView;
        } catch (IOException e) {
            log.error("Cant load image " + name + " from " + "src\\main\\resources\\images\\", e);
            return new ImageView(); //Load empty object to prevent shutdown
        }
    }

    public Circle drawFurniture() {
        Circle circle = new Circle();
        circle.setFill(Color.AQUA);
        circle.setCenterX(250);
        circle.setCenterY(250);
        circle.setRadius(140);

        return circle;
    }
}
