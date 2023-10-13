package view;

import controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Model;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.awt.*;

public class Options {
    private Pane view;
    private Controller controller ;
    private Model model;
    String name1;
    String name2;
    int die1;
    int die2;
    Button clockWise;
    Button counterClockWise;
    Button black;
    Button white;
    TextField time;
    TextField numberOfSets;
    Text numberOfSet;
    Text turn;
    Text typeOfPlay;
    Text timeOfPlay;
    Text color;
    Button start;
    Text message;
    int playerColor;
    double playTurnTime;
    int type;
    int setNumber;
    int foundedTurn;
    boolean flagColor=false;
    boolean flagType=false;
    Text whoseTurn;
    Text selectedColor;
    Text selectedType;

    public Parent getView() {
        return view;
    }

    public Options(Controller controller, Model model,int die1,int die2,String name1,String name2) {
        this.name1=name1;
        this.name2=name2;
        this.die1=die1;
        this.die2=die2;
        this.controller=controller;
        this.model=model;
        foundedTurn=controller.getModel().whichPlayerStart(die1,die2);
        createAndConfigurePane();
        checkColor();
        checkType();
        checkStart();
    }

    void createAndConfigurePane() {

        view = new Pane();
        view.setId("pane");
        view.setPrefSize(1800, 880);

        black = new Button();
        black.setLayoutX(750);
        black.setLayoutY(200);
        black.setText("BLACK");

        start = new Button("Start Game");
        start.setLayoutX(1050);
        start.setLayoutY(425);

        message=new Text("");
        message.setX(1050);
        message.setY(500);
        message.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        message.setFill(Color.WHITE);

        selectedColor=new Text("");
        selectedColor.setX(1250);
        selectedColor.setY(225);
        selectedColor.setFont(Font.font("Verdana", FontWeight.BOLD,12));
        selectedColor.setFill(Color.WHITE);

        selectedType=new Text("");
        selectedType.setX(1250);
        selectedType.setY(325);
        selectedType.setFont(Font.font("Verdana", FontWeight.BOLD,12));
        selectedType.setFill(Color.WHITE);

        if (foundedTurn==1){
            whoseTurn=new Text(name1+"! choose your prefered options.");
        }else {
            whoseTurn=new Text(name2+"! choose your prefered options.");
        }
        whoseTurn.setX(770);
        whoseTurn.setY(100);
        whoseTurn.setFont(Font.font("Verdana", FontWeight.BOLD,17));
        whoseTurn.setFill(Color.WHITE);

        white = new Button();
        white.setLayoutX(1050);
        white.setLayoutY(200);
        white.setText("WHITE");

        color=new Text("COLOR");
        color.setX(600);
        color.setY(225);
        color.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        color.setFill(Color.WHITE);

        clockWise = new Button();
        clockWise.setLayoutX(750);
        clockWise.setLayoutY(300);
        clockWise.setText("ClockWise");

        counterClockWise = new Button();
        counterClockWise.setLayoutX(1050);
        counterClockWise.setLayoutY(300);
        counterClockWise.setText("C.ClockWise");

        typeOfPlay=new Text("TYPE");
        typeOfPlay.setX(600);
        typeOfPlay.setY(325);
        typeOfPlay.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        typeOfPlay.setFill(Color.WHITE);

        numberOfSets = new TextField("1");
        numberOfSets.setLayoutX(750);
        numberOfSets.setLayoutY(400);

        time = new TextField("3");
        time.setLayoutX(750);
        time.setLayoutY(500);

        numberOfSet=new Text("Number of sets");
        numberOfSet.setX(600);
        numberOfSet.setY(425);
        numberOfSet.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        numberOfSet.setFill(Color.WHITE);

        turn=new Text("Turn time (s)");
        turn.setX(600);
        turn.setY(525);
        turn.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        turn.setFill(Color.WHITE);


        view.setPrefWidth(150);
        start.setMinWidth(view.getPrefWidth());
        clockWise.setMinWidth(view.getPrefWidth());
        counterClockWise.setMinWidth(view.getPrefWidth());
        black.setMinWidth(view.getPrefWidth());
        white.setMinWidth(view.getPrefWidth());

        view.getChildren().addAll(black,white,color,counterClockWise,clockWise,typeOfPlay,time,numberOfSets,numberOfSet,turn,start,message,whoseTurn,
                selectedColor,selectedType);

    }

    public void checkColor(){
        white.setOnAction(event -> {
            selectedColor.setText("WHITE selected.");
            flagColor=true;
            playerColor=2;
        });

        black.setOnAction(event -> {
            selectedColor.setText("BLACK selected.");
            flagColor=true;
            playerColor=1;
        });
    }

    public void checkType(){
        clockWise.setOnAction(event -> {
            selectedType.setText("ClockWise selected.");
            flagType=true;
            type=4;
        });

        counterClockWise.setOnAction(event -> {
            selectedType.setText("Counter-ClockWise selected.");
            flagType=true;
            type=3;
        });
    }

    public void checkStart(){
        start.setOnAction(event -> {

            if(!numberOfSets.equals("") && !time.equals("") && flagColor && flagType){
                setNumber=Integer.parseInt(numberOfSets.getText());
                playTurnTime=Integer.parseInt(time.getText());
                System.out.println(setNumber+" "+playTurnTime);
                Parent mainView = new View(controller, model,setNumber,playTurnTime,playerColor,type,foundedTurn,die1,die2,name1,name2).asParent();
                view.getScene().setRoot(mainView);
            }else {
                message.setText("Fill completely");

            }
        });
    }
}
