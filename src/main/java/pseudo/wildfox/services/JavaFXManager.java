package pseudo.wildfox.services;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import lombok.Data;
import lombok.extern.log4j.Log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static pseudo.wildfox.Properties.*;


@Log4j
@Data
public class JavaFXManager {

    private static JavaFXManager manager;
    static {
        manager = new JavaFXManager();
    }
    private Stage stage;
    private Group root;

    private JavaFXManager() {
        this.stage = new Stage();
    }

    public synchronized static JavaFXManager getInstance() {
        return manager;
    }

    public void start(Stage primaryStage) {
        this.root = new Group(drawPhilosophers());
        root.getChildren().add(drawTable());
        stage.setTitle(TITLE);

        Button button = new Button("Next");
        button.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
        button.setId("Next");
        root.getChildren().add(button);


        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.setFill(Color.WHEAT);
        stage.setScene(scene);
        stage.show();
    }

    public void makeInterrupt(String buttonId, List<? extends Thread> threads) {
        Node button = root.getChildren().filtered(e -> e.getId() == buttonId).get(0);
        button.setOnMousePressed(e -> threads.forEach(Thread::interrupt));
    }

    public void makeStopProgram(String buttonId) {
        Node button = root.getChildren().filtered(e -> e.getId() == buttonId).get(0);
        button.setOnMousePressed(e -> System.exit(0));
    }

    private ImageView[] drawPhilosophers() {
        ImageView[] imageViews = new ImageView[PHILOSOPHERS_NAMES.length];
        for (int i = 0; i< PHILOSOPHERS_NAMES.length; i++) {
            imageViews[i] = loadImageView(PHILOSOPHERS_NAMES[i], 110, COORDINATES[i][0], COORDINATES[i][1], PALE);
        }
        return imageViews;
    }

    private ImageView loadImageView(String name, int size, int x, int y, double opacity) {
        try (InputStream stream = new FileInputStream("src\\main\\resources\\images\\" + name + ".png")) {
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(stream));
            imageView.setId(name);
            imageView.setX(x);
            imageView.setY(y);
            imageView.setFitWidth(size);
            imageView.setPreserveRatio(true);
            imageView.setOpacity(opacity);
            return imageView;
        } catch (IOException e) {
            log.error("Cant load image " + name + " from " + "src\\main\\resources\\images\\", e);
            return new ImageView(); //Load empty object to prevent shutdown
        }
    }

    private ImageView drawTable() {
        return loadImageView("table", TABLE_SIZE, TABLE_X, TABLE_Y, BRIGHT);
    }


    @Deprecated
    private Circle drawFurniture() {
        Circle table = new Circle();
        table.setFill(Color.BURLYWOOD);
        table.setCenterX(250);
        table.setCenterY(250);
        table.setRadius(140);

        return table;
    }

    public void setOpacity(String name, double value) {
        root.getChildren().filtered(e -> e.getId() == name).get(0).setOpacity(value);
    }
}
