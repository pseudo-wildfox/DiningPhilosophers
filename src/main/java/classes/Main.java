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

    @Override
    public void start(Stage primaryStage) {
        JavaFXManager.getInstance().start(primaryStage);
    }



    public static void main(String[] args) throws InterruptedException {
        launch(args);
        //waiter.startDinner();
    }
}
