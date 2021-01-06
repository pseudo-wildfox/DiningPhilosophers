package pseudo.wildfox;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.BasicConfigurator;
import pseudo.wildfox.services.DinnerInvitation;
import pseudo.wildfox.services.JavaFXManager;

/*
10.Сравнение многопоточных алгоритмов решения задачи «Обедающие философы».
 */


    public class Main extends Application {
        private static DinnerInvitation dinner = new DinnerInvitation();

        public static void main(String[] args) {
            BasicConfigurator.configure();
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) {
            JavaFXManager.getInstance().start(primaryStage);
            dinner.start();
        }
    }
