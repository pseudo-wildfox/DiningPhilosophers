package classes;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;

/*
10.Сравнение многопоточных алгоритмов решения задачи «Обедающие философы».
 */


public class Main extends Application {
    private static Waiter waiter = new Waiter();

    @Override
    public void start(Stage primaryStage) {
        JavaFXManager.getInstance().start(primaryStage);
        waiter.start();
    }

    public static void main(String[] args) {
        BasicConfigurator.configure();
        launch(args);
    }
}
