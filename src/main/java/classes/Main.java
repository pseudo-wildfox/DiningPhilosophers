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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/*
10.Сравнение многопоточных алгоритмов решения задачи «Обедающие философы».
 */


public class Main extends Application {
    private static Waiter waiter = new Waiter(Philosopher.philosophersFactory());
    public static Stage stage = new Stage();
    public static Group root;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //URL url = new File("src/main/resources/fxml/sample.fxml").toURI().toURL();
        //Parent root = FXMLLoader.load(url);

        ImageView[] imageViews = new ImageView[Philosopher.names.length];
        for (int i=0; i< Philosopher.names.length; i++) {
            imageViews[i] = loadImageView(Philosopher.names[i], 90, Philosopher.coordinates[i][0], Philosopher.coordinates[i][1]);
            //root.getChildren().add();
        }


        root = new Group(imageViews);
        root.getChildren().add(drawFurniture());
        stage.setTitle("Dining philosophers");
        //StackPane layout = new StackPane();
        stage.setScene(new Scene(root, 500, 500));
        stage.show();

        waiter.startDinner();
        Label label = new Label("My Label");
        root.getChildren().add(label);

    }

    private ImageView loadImageView(String name, int size, int x, int y) throws IOException {
        try (InputStream stream = new FileInputStream("src\\main\\resources\\images\\" + name + ".png")) {
            Image image = new Image(stream);
            //Creating the image view
            ImageView imageView = new ImageView();
            //Setting image to the image view
            imageView.setImage(image);
            //Setting the image view parameters
            imageView.setX(x);
            imageView.setY(y);
            imageView.setFitWidth(size);
            imageView.setPreserveRatio(true);
        return imageView;
        }
    }

    public Circle drawFurniture() {
        Circle circle = new Circle();
        circle.setFill(Color.AQUA);
        //circle.setEffect(new GaussianBlur(Math.random() * 8 + 2));
        //circle.setOpacity(Math.random());
        circle.setCenterX(250);
        circle.setCenterY(250);
        circle.setRadius(140);

        return circle;
    }

    public static void main(String[] args) throws InterruptedException {
        launch(args);
    }
}
