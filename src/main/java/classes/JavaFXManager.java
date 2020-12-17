package classes;

import javafx.scene.Group;
import javafx.scene.Scene;
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


@Log4j
@Data
public class JavaFXManager {

    private static JavaFXManager manager;
    static {
        manager = new JavaFXManager();
    }
    private Stage stage;
    private Group root;
    public static final double BRIGHT = 1.0;
    public static final double PALE = 0.4;

    private JavaFXManager() {
        this.stage = new Stage();
    }

    public synchronized static JavaFXManager getInstance() {
        return manager;
    }

    public void start(Stage primaryStage) {
        this.root = new Group(drawPhilosophers());
        root.getChildren().add(drawFurniture());
        stage.setTitle("Dining philosophers");
        //StackPane layout = new StackPane();
        stage.setScene(new Scene(root, 500, 500));
        stage.show();


        //Label label = new Label("My Label");
        //root.getChildren().add(label);
    }
    private ImageView[] drawPhilosophers() {
        ImageView[] imageViews = new ImageView[Philosopher.NAMES.length];
        for (int i = 0; i< Philosopher.NAMES.length; i++) {
            imageViews[i] = loadImageView(Philosopher.NAMES[i], 90, Philosopher.COORDINATES[i][0], Philosopher.COORDINATES[i][1]);
        }
        return imageViews;
    }

    private ImageView loadImageView(String name, int size, int x, int y) {
        try (InputStream stream = new FileInputStream("src\\main\\resources\\images\\" + name + ".png")) {
            ImageView imageView = new ImageView();
            imageView.setImage(new Image(stream));
            imageView.setId(name);
            imageView.setX(x);
            imageView.setY(y);
            imageView.setFitWidth(size);
            imageView.setPreserveRatio(true);
            imageView.setOpacity(0.4);
            return imageView;
        } catch (IOException e) {
            log.error("Cant load image " + name + " from " + "src\\main\\resources\\images\\", e);
            return new ImageView(); //Load empty object to prevent shutdown
        }
    }

    private Circle drawFurniture() {
        Circle table = new Circle();
        table.setFill(Color.AQUA);
        table.setCenterX(250);
        table.setCenterY(250);
        table.setRadius(140);

        return table;
    }

    public void setOpacity(String name, double value) {
        root.getChildren().filtered(e -> e.getId() == name).get(0).setOpacity(value);
    }
}
