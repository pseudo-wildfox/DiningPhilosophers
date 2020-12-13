package classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
10.Сравнение многопоточных алгоритмов решения задачи «Обедающие философы».
 */


public class Main extends Application {
    private static Waiter waiter = new Waiter();


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Dining philosophers");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }


    public static void main(String[] args) throws InterruptedException {
        waiter.startDinner();

        launch(args);
    }
}
