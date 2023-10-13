package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Model;

public class ChooseKindOfPlay {
    private Pane view ;
    private Controller controller ;
    private Model model;
    Button netWorkPlayButton;
    Button localPlayButton;
    Text text;
    Text name;

    public Parent asParent() {
        return view ;
    }

    private void createAndConfigurePane() {

        view = new Pane();
        view.setId("pane");
        view.setPrefSize(1800, 880);
        name=new Text("Backgammon");
        name.setX(780);
        name.setY(220);
        name.setFont(Font.font("Verdana", FontWeight.BOLD,30));
        name.setFill(Color.WHITE);

        text=new Text("Please select:");
        text.setX(820);
        text.setY(320);
        text.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        text.setFill(Color.WHITE);

        localPlayButton = new Button("Local Play");
        localPlayButton.setLayoutX(850);
        localPlayButton.setLayoutY(370);

        netWorkPlayButton = new Button("Network Play");
        netWorkPlayButton.setLayoutX(840);
        netWorkPlayButton.setLayoutY(470);

        view.getChildren().addAll(localPlayButton,text,name,netWorkPlayButton);
    }

    void checkButton(){
        localPlayButton.setOnAction(event -> {
            Parent mainView = new GetNames(controller, model).getView();
            view.getScene().setRoot(mainView);
        });
    }

    public ChooseKindOfPlay(Controller controller, Model model) {
        this.controller = controller ;
        this.model = model ;
        createAndConfigurePane();

        checkButton();

    }

}
