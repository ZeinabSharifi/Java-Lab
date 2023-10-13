package view;

import controller.Controller;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Model;

public class GetNames {
    private Pane view;
    private Controller controller ;
    private Model model;
    Text text;
    Text player1;
    Text player2;
    TextField player1Name;
    TextField player2Name;
    Button button;
    String name1;
    String name2;
    Text massage;
    int check;

    public Parent getView() {
        return view;
    }

    public GetNames(Controller controller,Model model) {
        this.controller=controller;
        this.model=model;
        createAndConfigurePane();

        checkButton();
    }

    void createAndConfigurePane(){

        view = new Pane();
        view.setId("pane");
        view.setPrefSize(1800, 880);

        player1Name = new TextField("Player1");
        player1Name.setLayoutX(820);
        player1Name.setLayoutY(300);


        player2Name = new TextField("Player2");
        player2Name.setLayoutX(820);
        player2Name.setLayoutY(400);

        text=new Text("Please Enter your names");
        text.setX(730);
        text.setY(250);
        text.setFont(Font.font("Verdana", FontWeight.BOLD,20));
        text.setFill(Color.WHITE);

        player1=new Text("Player1");
        player1.setX(740);
        player1.setY(320);
        player1.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        player1.setFill(Color.WHITE);

        player2=new Text("Player2");
        player2.setX(740);
        player2.setY(420);
        player2.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        player2.setFill(Color.WHITE);

        button=new Button("NEXT");
        button.setLayoutX(860);
        button.setLayoutY(450);

        massage=new Text("");
        massage.setX(795);
        massage.setY(500);
        massage.setFont(Font.font("Verdana", FontWeight.BOLD,10));
        massage.setFill(Color.WHITE);

        view.getChildren().addAll(player1,player2,player1Name,player2Name,text,button,massage);
    }

    void checkButton(){

        button.setOnAction(event -> {

            name2=player2Name.getText();
            name1=player1Name.getText();
            check=controller.getModel().checkName(name1,name2);
            if (check==2){
                massage.setText("Player 2, please enter your name.");
            }else if (check==1){
                massage.setText("Player 1, please enter your name.");
            }else if (check==3){
                massage.setText("Player 1 & 2, please enter your names.");
            }
           // System.out.println(check);

            if(check==0){
                controller.getModel().getPlayerOne().setName(name1);
                controller.getModel().getPlayerTwo().setName(name2);
                Parent mainView = new FirstDie(controller,model,name1,name2).getView();
                view.getScene().setRoot(mainView);
            }
          //  System.out.println(controller.getModel().getPlayerOne().getName());

        });
    }
}
