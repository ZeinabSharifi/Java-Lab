package view;

import controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.Model;
import java.util.Random;

public class FirstDie {
    private Pane view;
    private Controller controller ;
    private Model model;
    String name1;
    String name2;
    Button button;
    Text player1;
    Text player2;
    Text turn;
    int tempRandom1;
    int tempRandom2;
    boolean turnOne;
    Text massage;

    public Parent getView() {
        return view;
    }

    public FirstDie(Controller controller,Model model,String name1,String name2) {
        this.controller=controller;
        this.model=model;
        this.name1=name1;
        this.name2=name2;

        createAndConfigurePane();

        checkButton();
    }

    public void createAndConfigurePane(){
        view = new Pane();
        view.setId("pane");
        view.setPrefSize(1800, 880);

        button=new Button("NEXT");
        button.setLayoutX(840);
        button.setLayoutY(530);
        button.setDisable(true);

        player1=new Text(name1);
        player1.setX(980);
        player1.setY(335);
        player1.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        player1.setFill(Color.WHITE);

        player2=new Text(name2);
        player2.setX(980);
        player2.setY(425);
        player2.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        player2.setFill(Color.WHITE);


        massage=new Text("");
        massage.setX(795);
        massage.setY(600);
        massage.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        massage.setFill(Color.WHITE);


        turn=new Text("Roll Die to Set Turn");
        turn.setX(780);
        turn.setY(250);
        turn.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        turn.setFill(Color.WHITE);



        DieImages images1 = new DieImages();
        Die die1 = new Die(images1.getImages());
        DieImages images2 = new DieImages();
        Die die2 = new Die(images2.getImages());
        StackPane stackPane1 = new StackPane(die1.getdieFace());//Add ImageView(Die) to stackPane
        StackPane stackPane2 = new StackPane(die2.getdieFace());//Add ImageView(Die) to stackPane
        stackPane1.setLayoutX(850);
        stackPane2.setLayoutX(850);
        stackPane1.setLayoutY(290);
        stackPane2.setLayoutY(380);

        Button btn = new Button();
        btn.setLayoutX(840);
        btn.setLayoutY(490);
        btn.setText("Roll Die");
        btn.setOnAction((ActionEvent event) -> {
            btn.setDisable(true);//Disable Button

            Random random1 = new Random();
            Random random2 = new Random();
            Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(0.3), (actionEvent) -> {
                tempRandom1 = random1.nextInt(6) + 1;
                tempRandom2 = random2.nextInt(6) + 1;
                //System.out.println(tempRandom1);
                die1.setDieFace(tempRandom1);
                //System.out.println("///"+tempRandom2);
                die2.setDieFace(tempRandom2);

            }));



            timeline1.setCycleCount( 10);
            timeline1.play();
            timeline1.setOnFinished(actionEvent -> {
                if (tempRandom1!=tempRandom2){
                    btn.setDisable(true);//Enable Button
                    button.setDisable(false);
                    if (tempRandom2>tempRandom1){
                        massage.setText(name2+" will start the game.");
                        massage.setX(795);
                    }else if (tempRandom2<tempRandom1) {
                        massage.setText(name1 + " will start the game.");
                        massage.setX(795);
                    }
                }else {
                    btn.setDisable(false);//Enable Button
                    button.setDisable(true);
                    massage.setText("Roll die again!");
                    massage.setX(820);
                }

            });

        });


        view.setPrefWidth(100);
        btn.setMinWidth(view.getPrefWidth());
        button.setMinWidth(view.getPrefWidth());

        view.getChildren().addAll(stackPane1,stackPane2,btn,button,player1,player2,turn,massage);

    }

    public void checkButton(){
        button.setOnAction(event -> {
            if (tempRandom1!=tempRandom2){
                if (tempRandom1>tempRandom2){
                    turnOne=true;
                }else {
                    turnOne=false;
                }
                Parent mainView = new Options(controller, model,tempRandom1,tempRandom2,name1,name2).getView();
                //Parent mainView = new View(controller, model).asParent();
                view.getScene().setRoot(mainView);
            }

        });
    }
}
