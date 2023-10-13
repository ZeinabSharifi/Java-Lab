package view;

import controller.Controller;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import model.CheckersMove;
import model.Model;
import model.Piece;
import javafx.scene.text.Text;
import model.Point;

import javax.xml.crypto.dom.DOMCryptoContext;
import java.util.ArrayList;
import java.util.Random;

import static model.Model.black;
import static model.Model.white;

public class View {
    private Controller controller ;
    private Model model ;
    private Pane view ;
    //public static Text colorA=new Text();
    Circle [] circles=new Circle[30];
    Polygon [] triangles=new Polygon[24];
    ArrayList<CheckersMove> possibleMoves;
    private Die die1;
    private Die die2;

    private boolean dice1Used = false;
    private boolean dice2Used=false;
    private boolean dice3Used=false;
    private boolean dice4Used=false;
    private boolean useFourDice=false;
    private int dice1;
    private int dice2;
    private int tempRandom1;
    private int tempRandom2;
    private int currentDie;
    private  boolean flag=true;
    //private Button btnDie;//for rolling die
    Text turn;
    Circle cTurn;


    public View(Controller controller, Model model,int setNumber,double playTurnTime,int playerColor,int type,int foundedTurn,int die1,int die2,String name1
    ,String name2) {

        this.controller = controller ;
        this.model = model ;
        this.possibleMoves=new ArrayList<>();
        controller.getModel().setNumberOfSets(setNumber);
        controller.getModel().setTurnTime(playTurnTime);
        controller.getModel().setType(type);
        controller.getModel().getPlayerOne().setName(name1);
        controller.getModel().getPlayerTwo().setName(name2);
        //----------------------------
        int c1,c2; //c1 will always go to the player who starts the game
        if (playerColor==Model.white){
            c1=2;
            c2=1;
        }else {
            c1=1;
            c2=2;
        }
        if(foundedTurn==1){//player1 has won,will get c1
            //player1
            controller.getModel().getPlayerOne().setColor(c1);
            controller.getModel().getPlayerOne().setTurn(true);
            controller.getModel().getPlayerOne().setCanDouble(true);
            //player2
            controller.getModel().getPlayerTwo().setColor(c2);
            controller.getModel().getPlayerTwo().setTurn(false);
            controller.getModel().getPlayerTwo().setCanDouble(false);
        }else {
            //player1
            controller.getModel().getPlayerOne().setColor(c2);
            controller.getModel().getPlayerOne().setTurn(false);
            controller.getModel().getPlayerOne().setCanDouble(false);
            //player2
            controller.getModel().getPlayerTwo().setColor(c1);
            controller.getModel().getPlayerTwo().setTurn(true);
            controller.getModel().getPlayerTwo().setCanDouble(true);
        }
        
        createAndConfigurePane();
        checkClick();
        startGame(die1,die2);


    }

    public Parent asParent() {
        return view ;
    }

    private void createAndConfigurePane() {
        view = new Pane();
        view.setPrefSize(1800, 880);

        turn=new Text("Turn:");
        turn.setX(1600);
        turn.setY(120);
        turn.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        turn.setFill(Color.BLACK);
        cTurn=new Circle(35);
        cTurn.setTranslateX(1700);
        cTurn.setTranslateY(120);
        if (model.getPlayerOne().isTurn()){
            String color=model.getPlayerOne().getColor()==white?"#FFFFFF":"#000000";
            String cStroke=model.getPlayerOne().getColor()==white?"#000000":"#FFFFFF";
            cTurn.setFill(Color.valueOf(color));
            cTurn.setStroke(Color.valueOf(cStroke));
        }else {
            String color=model.getPlayerTwo().getColor()==white?"#FFFFFF":"#000000";
            String cStroke=model.getPlayerTwo().getColor()==white?"#000000":"#FFFFFF";
            cTurn.setFill(Color.valueOf(color));
            cTurn.setStroke(Color.valueOf(cStroke));
        }

        Text player1Color=new Text(model.getPlayerOne().getColor()==black?"BLACK":"WHITE");
        player1Color.setX(1650);
        player1Color.setY(300);
        player1Color.setFont(Font.font("Verdana", FontWeight.BOLD,15));

        Text player2Color=new Text(model.getPlayerTwo().getColor()==black?"BLACK":"WHITE");
        player2Color.setX(1650);
        player2Color.setY(400);
        player2Color.setFont(Font.font("Verdana", FontWeight.BOLD,15));

        Text player1Name=new Text(model.getPlayerOne().getName()+" :");
        player1Name.setX(1550);
        player1Name.setY(300);
        player1Name.setFont(Font.font("Verdana", FontWeight.BOLD,15));

        Text player2Name=new Text(model.getPlayerTwo().getName()+" :");
        player2Name.setX(1550);
        player2Name.setY(400);
        player2Name.setFont(Font.font("Verdana", FontWeight.BOLD,15));

        Button newGame=new Button("New Game");
        newGame.setLayoutX(1550);
        newGame.setLayoutY(820);
        newGame.setOnAction(event -> {
            Parent mainView = new GetNames(controller, model).getView();
            view.getScene().setRoot(mainView);
        });

        view.getChildren().addAll(cTurn,turn,player1Color,player2Color,player1Name,player2Name,newGame);
        //==========================



        int numberOfAddedCircles=0;
        Rectangle rectBottom=new Rectangle(0,860,1800,20);
        Rectangle rectRight=new Rectangle(1780,0,20,880);
        Rectangle rectLeft=new Rectangle(0,0,20,880);
        Rectangle rectTop=new Rectangle(0,0,1800,20);
        Rectangle rectL=new Rectangle(100,0,20,880);
        Rectangle rectM=new Rectangle(720,0,50,880);
        Rectangle rectR=new Rectangle(1370,0,20,880);
        Rectangle rectT=new Rectangle(1470,0,20,880);
        Rectangle tray=new Rectangle();
        if (model.getType()==model.type2){
            tray=new Rectangle(20,430,80,20);

        }else {
            tray=new Rectangle(1390,430,80,20);
        }

        tray.setFill(Color.valueOf("0x71310D"));
        rectL.setFill(Color.valueOf("0x71310D"));
        rectM.setFill(Color.valueOf("0x71310D"));
        rectR.setFill(Color.valueOf("0x71310D"));
        rectT.setFill(Color.valueOf("0x71310D"));
        rectTop.setFill(Color.valueOf("0x71310D"));
        rectBottom.setFill(Color.valueOf("0x71310D"));
        rectRight.setFill(Color.valueOf("0x71310D"));
        rectLeft.setFill(Color.valueOf("0x71310D"));
        view.getChildren().addAll(rectTop,rectBottom,rectRight,rectLeft,rectL,rectM,rectR,rectT,tray);



        Double x1=0.0;
        Double x2=0.0;
        Double[] x1Circle=new Double[24];
        Double[] x2Circle=new Double[24];
        Double y1;
        Double y2;
        boolean isClockwise=model.type2==model.getType()?true:false;
        boolean isDown;
        for(int i =1;i<25;i++){
            Polygon triangle=new Polygon();
            isDown=i<13?true:false;
            y1=isDown?860.0:20.0;
            y2=isDown?510.0:370.0;

            switch (model.getBoard().get(i-1).getNumber()){  //type1:clockwise  type2:counterclockwise
                case 1:
                    x1=!isClockwise?1370.0:120.0;
                    x2=!isClockwise?1270.0:220.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    view.getChildren().add(triangle);
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    triangle.setStroke(Color.valueOf("0xBB8346"));
                    triangle.setStrokeWidth(1);
                    triangles[0]=triangle;

                    break;
                case 2:
                    x1=!isClockwise?1270.0:220.0;
                    x2=!isClockwise?1170.0:320.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    view.getChildren().add(triangle);
                    triangle.setFill(Color.valueOf("0x523608"));
                    triangle.setStroke(Color.valueOf("0x523608"));
                    triangle.setStrokeWidth(1);
                    triangles[1]=triangle;

                    break;
                case 3:
                    x1=!isClockwise?1170.0:320.0;
                    x2=!isClockwise?1070.0:420.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0xBB8346"));
                    triangle.setStrokeWidth(1);
                    triangles[2]=triangle;

                    break;
                case 4:
                    x1=!isClockwise?1070.0:420.0;
                    x2=!isClockwise?970.0:520.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0x523608"));
                    triangle.setStrokeWidth(1);
                    triangles[3]=triangle;

                    break;
                case 5:
                    x1=!isClockwise?970.0:520.0;
                    x2=!isClockwise?870.0:620.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0xBB8346"));
                    triangle.setStrokeWidth(1);
                    triangles[4]=triangle;

                    break;
                case 6:
                    x1=!isClockwise?870.0:620.0;
                    x2=!isClockwise?770.0:720.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0x523608"));
                    triangle.setStrokeWidth(1);
                    triangles[5]=triangle;

                    break;
                case 7:
                    x1=!isClockwise?720.0:770.0;
                    x2=!isClockwise?620.0:870.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0xBB8346"));
                    triangle.setStrokeWidth(1);
                    triangles[6]=triangle;

                    break;
                case 8:
                    x1=!isClockwise?620.0:870.0;
                    x2=!isClockwise?520.0:970.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0x523608"));
                    triangle.setStrokeWidth(1);
                    triangles[7]=triangle;

                    break;
                case 9:
                    x1=!isClockwise?520.0:970.0;
                    x2=!isClockwise?420.0:1070.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0xBB8346"));
                    triangle.setStrokeWidth(1);
                    triangles[8]=triangle;

                    break;
                case 10:
                    x1=!isClockwise?420.0:1070.0;
                    x2=!isClockwise?320.0:1170.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0x523608"));
                    triangle.setStrokeWidth(1);
                    triangles[9]=triangle;

                    break;
                case 11:
                    x1=!isClockwise?320.0:1170.0;
                    x2=!isClockwise?220.0:1270.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0xBB8346"));
                    triangle.setStrokeWidth(1);
                    triangles[10]=triangle;

                    break;
                case 12:
                    x1=!isClockwise?220.0:1270.0;
                    x2=!isClockwise?120.0:1370.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0x523608"));
                    triangle.setStrokeWidth(1);
                    triangles[11]=triangle;

                    break;
                case 13:
                    x1=!isClockwise?120.0:1370.0;
                    x2=!isClockwise?220.0:1270.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0xBB8346"));
                    triangle.setStrokeWidth(1);
                    triangles[12]=triangle;

                    break;
                case 14:
                    x1=!isClockwise?220.0:1270.0;
                    x2=!isClockwise?320.0:1170.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0x523608"));
                    triangle.setStrokeWidth(1);
                    triangles[13]=triangle;

                    break;
                case 15:
                    x1=!isClockwise?320.:1170.0;
                    x2=!isClockwise?420.0:1070.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0xBB8346"));
                    triangle.setStrokeWidth(1);
                    triangles[14]=triangle;

                    break;
                case 16:
                    x1=!isClockwise?420.0:1070.0;
                    x2=!isClockwise?520.0:970.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0x523608"));
                    triangle.setStrokeWidth(1);
                    triangles[15]=triangle;

                    break;
                case 17:
                    x1=!isClockwise?520.0:970.0;
                    x2=!isClockwise?620.0:870.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0xBB8346"));
                    triangle.setStrokeWidth(1);
                    triangles[16]=triangle;

                    break;
                case 18:
                    x1=!isClockwise?620.0:870.0;
                    x2=!isClockwise?720.0:770.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0x523608"));
                    triangle.setStrokeWidth(1);
                    triangles[17]=triangle;

                    break;
                case 19:
                    x1=!isClockwise?770.0:720.0;
                    x2=!isClockwise?870.0:620.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0xBB8346"));
                    triangle.setStrokeWidth(1);
                    triangles[18]=triangle;

                    break;
                case 20:
                    x1=!isClockwise?870.0:620.0;
                    x2=!isClockwise?970.0:520.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0x523608"));
                    triangle.setStrokeWidth(1);
                    triangles[19]=triangle;

                    break;
                case 21:
                    x1=!isClockwise?970.0:520.0;
                    x2=!isClockwise?1070.0:420.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0xBB8346"));
                    triangle.setStrokeWidth(1);
                    triangles[20]=triangle;

                    break;
                case 22:
                    x1=!isClockwise?1070.0:420.0;
                    x2=!isClockwise?1170.0:320.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0x523608"));
                    triangle.setStrokeWidth(1);
                    triangles[21]=triangle;

                    break;
                case 23:
                    x1=!isClockwise?1170.0:320.0;
                    x2=!isClockwise?1270.0:220.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0xBB8346"));
                    triangle.setStrokeWidth(1);
                    triangles[22]=triangle;

                    break;
                case 24:
                    x1=!isClockwise?1270.0:220.0;
                    x2=!isClockwise?1370.0:120.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    triangle.setStroke(Color.valueOf("0x523608"));
                    triangle.setStrokeWidth(1);
                    triangles[23]=triangle;

                    break;
                default:x2=0.0;
                    x1=0.0;
            }
            x1Circle[i-1]=x1;
            x2Circle[i-1]=x2;
            setX_Y_Point(i,x1,y1,x2,y2);
            System.out.println(i +"   " +x1 +y1 +x2 +y1 +((x1+x2)/2) +y2 +"\n");
        }

        //add checkers on the board

        for (int i=1;i<25;i++){
            int tmpCountPiece= model.getBoard().get(i-1).getPieceCount();
            String c2 = (model.getBoard().get(i-1).getColor()== black)? "0xFFFFFF": "0x000000";
            String c1 = (model.getBoard().get(i-1).getColor()== black)? "0x000000": "0xFFFFFF";
            if(i<=12){

                if (tmpCountPiece <= 5) {
                    for(int j=1;j<=tmpCountPiece;j++) {
                        Circle circle=new Circle(35);
                        circle.setFill(Color.valueOf(c1));
                        circle.setStroke(Color.valueOf(c2));
                        circle.setStrokeWidth(2);
                        if(!isClockwise){
                            circle.setTranslateX(x2Circle[i-1]+50);
                            setX_Y(i,j,x2Circle[i-1]+50,860.0-j*70.0+35.0);
                        }else {
                            circle.setTranslateX(x1Circle[i-1]+50);
                            setX_Y(i,j,x1Circle[i-1]+50,860.0-j*70.0+35.0);
                        }

                        circle.setTranslateY(860-j*70+35);


                        view.getChildren().add(circle);
                        circles[numberOfAddedCircles]=circle;
                        numberOfAddedCircles++;

                    }
                }else{
                    for(int j=1;j<=tmpCountPiece;j++) {
                        if (j==1){
                            Circle circle=new Circle(35);
                            circle.setFill(Color.valueOf(c1));
                            circle.setStroke(Color.valueOf(c2));
                            circle.setStrokeWidth(2);
                            if(!isClockwise){
                                circle.setTranslateX(x2Circle[i-1]+50);
                                setX_Y(i,j,x2Circle[i-1]+50,790.0+35.0);
                            }else {
                                circle.setTranslateX(x1Circle[i-1]+50);
                                setX_Y(i,j,x1Circle[i-1]+50,790.0+35.0);
                            }
                            circle.setTranslateY(790+35);



                            view.getChildren().add(circle);
                            circles[numberOfAddedCircles]=circle;
                            numberOfAddedCircles++;
                        }else {
                            Circle circle=new Circle(35);
                            circle.setFill(Color.valueOf(c1));
                            circle.setStroke(Color.valueOf(c2));
                            circle.setStrokeWidth(2);
                            if(!isClockwise){
                                circle.setTranslateX(x2Circle[i-1]+50);
                                setX_Y(i,j,x2Circle[i-1]+50,790.0-(j-1)*(350.0/tmpCountPiece)+35.0);
                            }else {
                                circle.setTranslateX(x1Circle[i-1]+50);
                                setX_Y(i,j,x1Circle[i-1]+50,790.0-(j-1)*(350.0/tmpCountPiece)+35.0);
                            }
                            circle.setTranslateY( 790-(j-1)*(350/tmpCountPiece)+35);


                            view.getChildren().add(circle);
                            circles[numberOfAddedCircles]=circle;
                            numberOfAddedCircles++;
                        }
                    }
                }
            }else{
                if (tmpCountPiece <= 5) {
                    for(int j=0;j<tmpCountPiece;j++) {
                        Circle circle=new Circle(35);
                        circle.setFill(Color.valueOf(c1));
                        circle.setStroke(Color.valueOf(c2));
                        circle.setStrokeWidth(2);
                        if(!isClockwise){
                            circle.setTranslateX(x1Circle[i-1]+50);
                            setX_Y(i,j+1,x1Circle[i-1]+50,20.0+j*70.0+35.0);
                        }else {
                            circle.setTranslateX(x2Circle[i-1]+50);
                            setX_Y(i,j+1,x2Circle[i-1]+50,20.0+j*70.0+35.0);
                        }
                        circle.setTranslateY(20+j*70+35);



                        view.getChildren().add(circle);
                        circles[numberOfAddedCircles]=circle;
                        numberOfAddedCircles++;
                    }
                }else{
                    for(int j=1;j<=tmpCountPiece;j++) {
                        if (j==1){
                            Circle circle=new Circle(35);
                            circle.setFill(Color.valueOf(c1));
                            circle.setStroke(Color.valueOf(c2));
                            circle.setStrokeWidth(2);
                            if(!isClockwise){
                                circle.setTranslateX(x1Circle[i-1]+50);
                                setX_Y(i,j,x1Circle[i-1]+50,55.0);
                            }else {
                                circle.setTranslateX(x2Circle[i-1]+50);
                                setX_Y(i,j,x2Circle[i-1]+50,55.0);
                            }
                            circle.setTranslateY(20+35);


                            view.getChildren().add(circle);
                            circles[numberOfAddedCircles]=circle;
                            numberOfAddedCircles++;
                        }else {
                            Circle circle=new Circle(35);
                            circle.setFill(Color.valueOf(c1));
                            circle.setStroke(Color.valueOf(c2));
                            circle.setStrokeWidth(2);
                            if(!isClockwise){
                                circle.setTranslateX(x1Circle[i-1]+50);
                                setX_Y(i,j,x1Circle[i-1]+50,20.0+(j-1)*(350.0/tmpCountPiece)+35.0);
                            }else {
                                circle.setTranslateX(x2Circle[i-1]+50);
                                setX_Y(i,j,x2Circle[i-1]+50,20.0+(j-1)*(350.0/tmpCountPiece)+35.0);
                            }
                            circle.setTranslateY(20+(j-1)*(350/tmpCountPiece)+35);


                            view.getChildren().add(circle);
                            circles[numberOfAddedCircles]=circle;
                            numberOfAddedCircles++;
                        }
                    }
                }

            }
        }



    }

    int flagOfRoll=0;
    void rollDie(int d1,int d2){
        //----------------------------------------------die
        DieImages images1 = new DieImages();
        die1 = new Die(images1.getImages());
        DieImages images2 = new DieImages();
        die2 = new Die(images2.getImages());
        StackPane stackPane1 = new StackPane(die1.getdieFace());//Add ImageView(Die) to stackPane
        StackPane stackPane2 = new StackPane(die2.getdieFace());//Add ImageView(Die) to stackPane
        Button btnDie = new Button();
        if(model.getType()== Model.type2)
        {

            stackPane1.setLayoutX(1390);
            stackPane2.setLayoutX(1390);
            stackPane1.setLayoutY(510);
            stackPane2.setLayoutY(600);
            btnDie.setLayoutX(1400);
            btnDie.setLayoutY(710);
        }else{
            stackPane1.setLayoutX(20);
            stackPane2.setLayoutX(20);
            stackPane1.setLayoutY(510);
            stackPane2.setLayoutY(600);
            btnDie.setLayoutX(30);
            btnDie.setLayoutY(710);

        }

        btnDie.setText("Roll Die");

        if (flagOfRoll==0){    //first roll
            die1.setDieFace(d1);
            die2.setDieFace(d2);
            btnDie.setDisable(true);
            flagOfRoll=1;
            swapDie(d1,d2);
            currentDie=dice1;
            showPossibleMove(dice1,dice1,dice2);
            // TODO: 7/6/2019  continue game here 

            startGame(1,2);
        }else {
            //=================
            System.out.println(model.getPlayerOne().isTurn()+" "+model.getPlayerTwo().isTurn()+"-----------------");
            //===============
            btnDie.setDisable(false);
            btnDie.setOnAction((ActionEvent event) -> {
                btnDie.setDisable(true);//Disable Button
                stackPane1.toFront();
                stackPane2.toFront();
                Random random1 = new Random();
                Random random2 = new Random();
                Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(.2), (actionEvent) -> {
                    tempRandom1 = random1.nextInt(6) + 1;
                    tempRandom2 = random2.nextInt(6) + 1;
                     System.out.println(tempRandom1);
                    if(dice1Used){
                        die1.setDieFace(tempRandom1 + 6);
                    }
                    else{
                        die1.setDieFace(tempRandom1);
                    }

                     System.out.println("///"+tempRandom2);
                    die2.setDieFace(tempRandom2);
                    if(dice2Used){
                        die2.setDieFace(tempRandom2 + 6);
                    }
                    else{
                        die2.setDieFace(tempRandom2);
                    }
                /*if(tempRandom1==tempRandom2){
                    DieImages images3 = new DieImages();
                    Die die3 = new Die(images3.getImages());
                    DieImages images4 = new DieImages();
                    Die die4 = new Die(images4.getImages());
                    StackPane stackPane3 = new StackPane(die3.getdieFace());//Add ImageView(Die) to stackPane
                    StackPane stackPane4 = new StackPane(die4.getdieFace());//Add ImageView(Die) to stackPane
                    if(dice3Used){
                        die3.setDieFace(tempRandom1 + 6);
                    }
                    else{
                        die3.setDieFace(tempRandom1);
                    }
                    if(dice4Used){
                        die4.setDieFace(tempRandom1 + 6);
                    }
                    else{
                        die4.setDieFace(tempRandom1);
                    }
                    if(model.getType()== Model.type1)
                    {
                        stackPane3.setLayoutX(1390);
                        stackPane4.setLayoutX(1390);
                        stackPane3.setLayoutY(420);
                        stackPane4.setLayoutY(330);
                    }else{
                        stackPane3.setLayoutX(20);
                        stackPane4.setLayoutX(20);
                        stackPane3.setLayoutY(420);
                        stackPane4.setLayoutY(330);


                    }
                    view.getChildren().addAll(stackPane3,stackPane4);
                }*/

                }));

                timeline1.setCycleCount(10);
                timeline1.play();
                timeline1.setOnFinished(actionEvent -> {

                    btnDie.setDisable(false);
                    int r1= random1.nextInt(6) + 1;
                    int r2= random2.nextInt(6) + 1;
                    swapDie(r1,r2);
                    currentDie=dice1;
                    showPossibleMove(dice1,dice1,dice2);
                    die1.setDieFace(r1);
                    die2.setDieFace(r2);
                });


            });


            
        }


        
        view.getChildren().addAll(stackPane1,stackPane2,btnDie);

    }

    void setX_Y(int i,int j,Double x,Double y){
        if (controller.getModel().getBoard().get(i-1).getPieceCount()!=0){
            controller.getModel().getBoard().get(i-1).getCheckers().get(j-1).setX(x);
            controller.getModel().getBoard().get(i-1).getCheckers().get(j-1).setY(y);
        }
    }

    void checkClick(){
        for (int i=0;i<30;i++){
            Circle c=circles[i];
            final double[] nextX = new double[1];
            final double[] nextY = new double[1];
            final Double[] X = new Double[1];
            final Double[] Y = new Double[1];
            final boolean[] trueSelect = {false};
            final boolean[] trueSelectPoint = {false};
            c.setOnMousePressed(e -> {
                nextY[0] = e.getSceneY();
                nextX[0] = e.getSceneX();
                c.toFront();
                X[0]=c.getTranslateX();
                Y[0]=c.getTranslateY();
                trueSelect[0] =checkTrueSelect(c);
                if(trueSelect[0]){
                    resetOtherStrokes(X[0],Y[0]);
                    showPossiblePoint(X[0],Y[0]);
                }
            });


            c.setOnMouseDragged(e -> {
                if (trueSelect[0]){
                    Piece tmp=findPiece(X[0],Y[0]);
                    if((tmp!= null && tmp.isTopChecker()==true)||(tmp!=null && tmp.isOnBar()==true)){
                        c.setTranslateX(e.getSceneX());
                        c.setTranslateY( e.getSceneY());

                    }else{
                        c.setTranslateY(Y[0]);
                        c.setTranslateX(X[0]);
                    }
                }

            });

            c.setOnMouseReleased(e -> {
                double destX = (e.getSceneX());
                double destY = (e.getSceneY());
                double currentX = (c.getTranslateX());
                double currentY = (c.getTranslateY());


                if (trueSelect[0]){
                    System.out.println("////X[0]  "+X[0] +"////Y[0]  " +Y[0] +"\n");
                    System.out.println("***destX  "+destX +"***destY  " +destY);
                    System.out.println("###centerX  " +currentX +"###currentY  " +currentY +"\n");

                    Piece tmp=findPiece(X[0],Y[0]);

                    if(tmp != null){
                        System.out.println("true\n");
                        System.out.println("triangle number: " +tmp.getAtPoint() +"\n");
                    }
                    Point origin=null;
                    int pieceCount =0;
                    if(!tmp.isOnBar()) {
                        origin = findTriangle(X[0], Y[0]);
                        pieceCount = origin.getPieceCount();
                        if(origin!=null){
                            System.out.println("true\n");
                            System.out.println("origin number: " +origin.getNumber() +"\n");
                        }
                    }

                    Point destination = findTriangle(currentX,currentY);
                    if(destination != null){
                        System.out.println("true");
                        System.out.println("dest number : " +destination.getNumber() +"\n");
                    }
                    boolean goOnBar = false;
                    if(destination!=null) {
                        if (destination.getPieceCount() == 1 && destination.getColor() != tmp.getColor() && destination.getColor() != Model.noColor) {
                            goOnBar = true;
                        }
                    }
                    //==================================================================================
                    if(destination!=null && origin!=null) {
                        trueSelectPoint[0] = checkTruePointSelect(destination.getNumber(), origin.getNumber());
                    }
                    //===================================================================================

                    if( ((tmp!=null && tmp.isTopChecker())||(tmp!=null && tmp.isOnBar())) && destination!=null && trueSelectPoint[0]){   // TODO: 7/5/2019  read possible from model
                        // c.setTranslateX(destX);
                        // c.setTranslateY( destY);
                        controller.getModel().makeMove(origin,destination,tmp);
                        System.out.println("origin pieceCount: " +origin.getPieceCount() +"origin color:  " +origin.getColor());
                        System.out.println("\ndest pieceCount: " +destination.getPieceCount() +"dest color:  " +destination.getColor());
                        updateView(origin,destination,c,goOnBar,pieceCount);
                        //=========================
                        resetDesignAfterMove();
                        setUsedDie();
                        System.out.println(dice1Used+" "+dice2Used+" "+dice3Used+" "+dice4Used+" ");
                        int nextDie=findNextDie(dice1,dice2);
                        resetAllDiceUsed(nextDie);
                        System.out.println(dice1Used+" "+dice2Used+" "+dice3Used+" "+dice4Used+" after reset");
                        if (nextDie==-1){
                           // btnDie.setDisable(false);
                            controller.getModel().changeTurn();
                            if (model.getPlayerOne().isTurn()){
                                String color=model.getPlayerOne().getColor()==white?"#FFFFFF":"#000000";
                                String cStroke=model.getPlayerOne().getColor()==white?"#000000":"#FFFFFF";
                                cTurn.setFill(Color.valueOf(color));
                                cTurn.setStroke(Color.valueOf(cStroke));
                            }else {
                                String color=model.getPlayerTwo().getColor()==white?"#FFFFFF":"#000000";
                                String cStroke=model.getPlayerTwo().getColor()==white?"#000000":"#FFFFFF";
                                cTurn.setFill(Color.valueOf(color));
                                cTurn.setStroke(Color.valueOf(cStroke));
                            }
                            rollDie(1,2);
                        }else {
                            currentDie=nextDie;
                            showPossibleMove(nextDie,dice1,dice2);
                        }
                        //========================
                        StackPane stackPane1 = new StackPane(die1.getdieFace());//Add ImageView(Die) to stackPane
                        StackPane stackPane2 = new StackPane(die2.getdieFace());//Add ImageView(Die) to stackPane
                        if(model.getType()== Model.type2)
                        {
                            stackPane1.setLayoutX(1390);
                            stackPane2.setLayoutX(1390);
                            stackPane1.setLayoutY(510);
                            stackPane2.setLayoutY(600);

                        }else{
                            stackPane1.setLayoutX(20);
                            stackPane2.setLayoutX(20);
                            stackPane1.setLayoutY(510);
                            stackPane2.setLayoutY(600);
                        }
                        die1.setDieFace(dice1);
                        die2.setDieFace(dice2);

                        if (dice1>dice2 && flag==true){
                            die1.setDieFace(dice1 + 6);
                            stackPane1.toFront();
                            flag=false;
                        }else {
                            die2.setDieFace(dice2 + 6);
                            die1.setDieFace(dice1 + 6);
                            stackPane1.toFront();
                            stackPane2.toFront();
                            flag=true;
                        }

                        view.getChildren().addAll(stackPane1,stackPane2);
                        //=========================
                    }else {
                        c.setTranslateY(Y[0]);
                        c.setTranslateX(X[0]);
                        resetDesignAfterMove();
                        showPossibleMove(currentDie,dice1,dice2);
                    }
               /* Piece tmp=findPiece(X[0],Y[0]);
                if(true && (tmp!=null?tmp.isTopChecker():false)){   // TODO: 7/5/2019  read possible from model
                    c.setTranslateX(destX);
                    c.setTranslateY( destY);
                }else {
                    c.setTranslateY(Y[0]);
                    c.setTranslateX(X[0]);
                }*/

                }

            });

        }

    }
    
    
    
    public void startGame(int d1,int d2){
        rollDie(d1,d2);
    }
    
    

    public Piece findPiece(double xCircle, double yCircle){
        Piece tmp=null;

        for (int i=0;i<24;i++){
            int pieceNumber=model.getBoard().get(i).getPieceCount();
            if(pieceNumber!=0){
                for (int j=0;j<pieceNumber;j++){
                    if(model.getBoard().get(i).getCheckers().get(j).getX()==xCircle && model.getBoard().get(i).getCheckers().get(j).getY()==yCircle){
                        tmp=model.getBoard().get(i).getCheckers().get(j);
                        return tmp;
                    }
                }
            }
        }
        //no piece with given locations has been found on triangles
        //search the pieces on bar
        for(int i=0;i<model.getPlayerOneOnBarCount();i++){
            if(model.getPlayerOne().getColor()==Model.black){//player one is black and has some pieces on bar
                if(model.getPlayerOneOnBar().get(i).getX()== 745.0 && model.getPlayerOneOnBar().get(i).getY()== 440-i*70-35){
                    return model.getPlayerOneOnBar().get(i);
                }
            }else{//player one is white and has some pieces on bar
                if(model.getPlayerOneOnBar().get(i).getX()== 745.0 && model.getPlayerOneOnBar().get(i).getY()== 860-i*70-35){
                    return model.getPlayerOneOnBar().get(i);
                }

            }

        }
        for(int i=0;i<model.getPlayerTwoOnBarCount();i++){
            if(model.getPlayerTwo().getColor()==Model.black){//player two is black and has some pieces on bar
                if(model.getPlayerTwoOnBar().get(i).getX()== 745.0 && model.getPlayerTwoOnBar().get(i).getY()== 440-i*70-35){
                    return model.getPlayerTwoOnBar().get(i);
                }
            }else{//player two is white and has some pieces on bar
                if(model.getPlayerTwoOnBar().get(i).getX()== 745.0 && model.getPlayerTwoOnBar().get(i).getY()== 860-i*70-35){
                    return model.getPlayerTwoOnBar().get(i);
                }

            }

        }

        return tmp;
    }

    public Point findTriangle(Double centerX, Double centerY){
        boolean isClockwise=model.type2==model.getType()?true:false;
        boolean triangleFounded =false;
        Point foundPOINT= null;
        for(int i=1;i<25;i++){
            if(i<13){
                if(!isClockwise) {
                    if (centerX > model.getBoard().get(i - 1).getX2() + 25 && centerX < model.getBoard().get(i - 1).getX1() - 25) {
                        if (centerY > model.getBoard().get(i - 1).getY2() +5 && centerY<model.getBoard().get(i - 1).getY1() -5){
                            triangleFounded = true;
                            foundPOINT = model.getBoard().get(i - 1);
                            return foundPOINT;

                        }
                    }
                }else{
                    if (centerX > model.getBoard().get(i - 1).getX1() + 25 && centerX < model.getBoard().get(i - 1).getX2() - 25) {
                        if (centerY > model.getBoard().get(i - 1).getY2() + 5 && centerY<model.getBoard().get(i - 1).getY1() -5){
                            triangleFounded = true;
                            foundPOINT = model.getBoard().get(i - 1);
                            return foundPOINT;
                        }
                    }

                }

            }else{
                if(!isClockwise) {
                    if (centerX > model.getBoard().get(i - 1).getX1() + 25 && centerX < model.getBoard().get(i - 1).getX2() - 25) {
                        if (centerY > model.getBoard().get(i - 1).getY1() + 5 && centerY<model.getBoard().get(i - 1).getY2() -5){
                            triangleFounded = true;
                            foundPOINT =  model.getBoard().get(i - 1);
                            return foundPOINT;

                        }
                    }
                }else{
                    if (centerX > model.getBoard().get(i - 1).getX2() + 25 && centerX < model.getBoard().get(i - 1).getX1() - 25) {
                        if (centerY > model.getBoard().get(i - 1).getY1() + 5 && centerY<model.getBoard().get(i - 1).getY2() -5){
                            triangleFounded = true;
                            foundPOINT = model.getBoard().get(i-1);
                            return foundPOINT;
                        }
                    }

                }

            }
        }
        return foundPOINT;
    }

    public void updateView(Point origin,Point point,Circle c,boolean goOnBar,int pieceCount){//model has already been updated after a certain Move

        //updating the origin in case it is a triangle on board(not bar)//
        //only useful when a triangle with more than 5 pieces on loses a piece.it should get back to normal view
        if(origin!=null){
            if(pieceCount>5){
                for(int i=0;i<origin.getPieceCount();i++) {
                    for (int k = 0; k < 30; k++) {
                        if(i==0){
                            if(circles[k].getTranslateX()==((origin.getX1()+origin.getX2())/2) && circles[k].getTranslateY()==((origin.getNumber()<13)?825:55)){
                                circles[k].toFront();
                            }
                        }else{
                            if(circles[k].getTranslateX()==((origin.getX1()+origin.getX2())/2) && circles[k].getTranslateY()==((origin.getNumber()<13)?790-i*(350/pieceCount)+35:20+i*(350/pieceCount)+35)) {
                                circles[k].setTranslateY((origin.getNumber() < 13) ? 860 - i*(350 /origin.getPieceCount())-35 : 20 + i *(350/origin.getPieceCount()) + 35);
                            }
                            circles[k].toFront();
                        }
                    }
                }
            }
        }
        int kTmp=-1;
        for(int k=0;k<30;k++){
            if(circles[k].equals(c)){//find the index of that circle
                kTmp = k;
            }
        }
        c.setTranslateX((point.getX1()+point.getX2())/2);

        System.out.println("updated x   " +c.getTranslateX() +"\n");
        if(point.getPieceCount()<6){
            if(point.getNumber()<13) {
                c.setTranslateY(point.getY1()-(point.getPieceCount()-1) * 70 - 35);
            }else{
                c.setTranslateY(point.getY1()+ (point.getPieceCount()-1)*70 + 35);
            }
            System.out.println("updated y   " +c.getTranslateY() +"\n");
            setX_Y(point.getNumber(),point.getPieceCount(),c.getTranslateX(),c.getTranslateY());
            System.out.println("updated x-y after setX_Y    \n" +"x   " +c.getTranslateX() +"   y   " +c.getTranslateY() +"\n");
            if(goOnBar){
                for(int k=0;k<30;k++){
                    if (circles[k].getTranslateX() == c.getTranslateX() && circles[k].getTranslateY() == c.getTranslateY() && k!=kTmp) {
                        circles[k].setTranslateX(745.0);
                        if(point.getColor()==Model.black){//the hitted checker was white
                            if(model.getPlayerOne().getColor()== Model.white){//the hitted checker was for player one
                                circles[k].setTranslateY(860-model.getPlayerOneOnBarCount()*70-35);
                            }
                            else{
                                circles[k].setTranslateY(860-model.getPlayerTwoOnBarCount()*70-35);
                            }

                        }else{//the hitted cheker was black
                            if(model.getPlayerOne().getColor()== Model.black){//the hitted checker was for player one
                                circles[k].setTranslateY(440-model.getPlayerOneOnBarCount()*70-35);
                            }
                            else{
                                circles[k].setTranslateY(440-model.getPlayerTwoOnBarCount()*70-35);
                            }
                        }

                    }
                }
            }
        }else{
            for(int i=0;i<point.getPieceCount()-1;i++){//pressing chckers when there are more than 5//pieces on the dest. are updated in for loop
                for(int j=0;j<30;j++){
                    if(circles[j].getTranslateY()==point.getCheckers().get(i).getY() && circles[j].getTranslateX()==point.getCheckers().get(i).getX()){
                        //find checkers of that point in circles
                        if(i!=0){//the first circle in that Point shouldn't move.update others
                            if(point.getNumber()<13){//the triangle is down
                                /*circles[j].setTranslateY(860-i*(315/(point.getPieceCount()-1))-35);*/
                                circles[j].setTranslateY(790-i*(350/point.getPieceCount())+35);

                            }else{
                                /*circles[j].setTranslateY(20+i*(315/(point.getPieceCount()-1))+35);*/
                                circles[j].setTranslateY(20+i*(350/point.getPieceCount())+35);

                            }
                            setX_Y(point.getNumber(),i+1,circles[j].getTranslateX(),circles[j].getTranslateY());

                        }
                        circles[j].toFront();
                    }
                }
            }
            if(point.getNumber()<13){
                c.setTranslateY(860-(point.getPieceCount()-1)*(350/point.getPieceCount())-35);
            }else{
                c.setTranslateY(20+(point.getPieceCount()-1)*(350/point.getPieceCount())+35);
            }
            setX_Y(point.getNumber(),point.getPieceCount(),c.getTranslateX(),c.getTranslateY());
            c.toFront();

        }

    }


    public void setX_Y_Point(int i,Double x1, Double y1,Double x2,Double y2){

        controller.getModel().getBoard().get(i-1).setX1(x1);
        controller.getModel().getBoard().get(i-1).setY1(y1);
        controller.getModel().getBoard().get(i-1).setX2(x2);
        controller.getModel().getBoard().get(i-1).setY2(y2);
    }

    public void showPossibleMove(int dieNumber,int dice1,int dice2){  //call model

        if (possibleMoves!=null){
            for (int i=0;i<possibleMoves.size();i++){
                possibleMoves.remove(0);
            }
        }
        possibleMoves=controller.getModel().getMoves(dieNumber,dice1,dice2);
        //===================
        System.out.println("------------------------size:"+possibleMoves.size());
        if (((model.getPlayerOne().isTurn() && model.getPlayerOneOnBar().size()!=0)||(model.getPlayerTwo().isTurn() && model.getPlayerTwoOnBar().size()!=0))||
                possibleMoves==null){
                    controller.getModel().changeTurn();
            if (model.getPlayerOne().isTurn()){
                String color=model.getPlayerOne().getColor()==white?"#FFFFFF":"#000000";
                String cStroke=model.getPlayerOne().getColor()==white?"#000000":"#FFFFFF";
                cTurn.setFill(Color.valueOf(color));
                cTurn.setStroke(Color.valueOf(cStroke));
            }else {
                String color=model.getPlayerTwo().getColor()==white?"#FFFFFF":"#000000";
                String cStroke=model.getPlayerTwo().getColor()==white?"#000000":"#FFFFFF";
                cTurn.setFill(Color.valueOf(color));
                cTurn.setStroke(Color.valueOf(cStroke));
            }
                     rollDie(1,2);
        }else if(possibleMoves!=null){
            int size=possibleMoves.size();
            for (int i=0;i<size;i++){
                double x=possibleMoves.get(i).getPiece().getX();
                double y=possibleMoves.get(i).getPiece().getY();
                findCircle(x,y);
            }
        }
    }

    public void findCircle(double x,double y){   //to show possible moves
        for (int i=0;i<30;i++){
            if (circles[i].getTranslateX()==x && circles[i].getTranslateY()==y){
                circles[i].setStroke(Color.valueOf("#88b04b"));
                circles[i].setStrokeWidth(5);
                return;
            }
        }
    }

    public  boolean checkTrueSelect(Circle c){  //for piece
        if (c.getStroke().equals(Color.valueOf("#88b04b"))){
            return true;
        }
        return false;
    }

    public void resetOtherStrokes(double x,double y){  //just keep the stroke of the selected piece
        for (int i=0;i<30;i++){
            if (circles[i].getTranslateX()!=x && circles[i].getTranslateY()!=y){
                circles[i].setStrokeWidth(2);
                if (circles[i].getFill().equals(Color.valueOf("#FFFFFF"))){
                    circles[i].setStroke(Color.valueOf("#000000"));
                }else {
                    circles[i].setStroke(Color.valueOf("#FFFFFF"));
                }
            }
        }
    }

    public void showPossiblePoint(double x,double y){
        int pointNumber;
        for (int i=0;i<possibleMoves.size();i++){
            if (possibleMoves.get(i).getPiece().getX()==x && possibleMoves.get(i).getPiece().getY()==y){
                if(possibleMoves.get(i).getDestinationPoint()!=null){
                    pointNumber=possibleMoves.get(i).getDestinationPoint().getNumber();
                    triangles[pointNumber-1].setStroke(Color.valueOf("#88b04b"));
                    triangles[pointNumber-1].setStrokeWidth(5);
                }else {
                    System.out.println("tray----------------------");
                    // TODO: 7/7/2019
                }
                
            }
        }

    }

    public boolean checkTruePointSelect(int destNumber,int orgNumber){
        if (possibleMoves.size()!=0){
            for (int i=0;i<possibleMoves.size();i++){
                if (possibleMoves.get(i).getDestinationPoint()!=null){
                    if (possibleMoves.get(i).getDestinationPoint().getNumber()==destNumber && possibleMoves.get(i).getCurrentPoint().getNumber()==orgNumber){
                        return true;
                    }
                }
                else {
                    // TODO: 7/7/2019 tray
                }
            }
        }
        return false;
    }


    public void resetDesignAfterMove(){ //remove strokes
        Paint c=Color.valueOf("#FFFFFF");
        for (int i=0;i<30;i++){
            if (circles[i].getStroke().equals(Color.valueOf("#88b04b"))){
                circles[i].setStrokeWidth(2);
                c=circles[i].getFill();
                if (circles[i].getFill().equals(Color.valueOf("#FFFFFF"))){
                    circles[i].setStroke(Color.valueOf("#000000"));
                }else {
                    circles[i].setStroke(Color.valueOf("#FFFFFF"));
                }
                break;
            }
        }
        for (int i=0;i<24;i++){
            if(triangles[i].getStroke().equals(Color.valueOf("#88b04b"))){
                if (c.equals(Color.valueOf("#000000")) && i>=18){
                    controller.getModel().setHomeNumberBlack(controller.getModel().getHomeNumberBlack()+1);
                }else if (c.equals(Color.valueOf("#FFFFFF")) && i<=5){
                    controller.getModel().setHomeNumberWhite(controller.getModel().getHomeNumberWhite()+1);
                }
                triangles[i].setStrokeWidth(1);
                triangles[i].setStroke(triangles[i].getFill());
                return;
            }
        }
    }




    public int findNextDie(int die1,int die2){
        if(dice1Used && !dice2Used && !dice3Used && !dice4Used){
           return die2;
        }
        if(dice1Used && dice2Used && !useFourDice && !dice3Used && !dice4Used){
           return -1;
        }
        if (dice1Used && dice2Used && useFourDice && !dice3Used && !dice4Used){
            return die1;
        }
        if (dice1Used && dice2Used && dice3Used && useFourDice && !dice4Used){
            return die1;
        }
        if (dice1Used && dice2Used && dice3Used && dice4Used && useFourDice){
            return -1;
        }
        return -1;
    }

    public void setUsedDie(){
        if (!useFourDice){
            if (!dice1Used){
                dice1Used=true;
            }else {
                dice2Used=true;
            }
        }else {
            if (!dice1Used){
                dice1Used=true;
            }else if(!dice2Used){
                dice2Used=true;
            }else if(!dice3Used){
                dice3Used=true;
            }else {
                dice4Used=true;
            }
        }
    }

    public void swapDie(int d1,int d2){
        if(d1==d2){
            useFourDice=true;
            dice1=d1;
            dice2=d1;
        }else if (d1>d2){
            dice1=d1;
            dice2=d2;
        }else {
            dice1=d2;
            dice2=d1;
        }
    }

    public void resetAllDiceUsed(int nextDie){
        if (nextDie==-1){
            dice4Used=false;
            dice2Used=false;
            dice1Used=false;
            dice3Used=false;
            useFourDice=false;
        }
    }


}
