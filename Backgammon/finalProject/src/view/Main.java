package view;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Model;
import view.View;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Model model = new Model(3,Model.black);
        Controller controller = new Controller(model);
        ChooseKindOfPlay chooseKindOfPlay=new ChooseKindOfPlay(controller, model);
        //View view = new View(controller, model);

        Scene scene = new Scene(chooseKindOfPlay.asParent());
        scene.getStylesheets().addAll(this.getClass().getResource("pane.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Backgammon");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
