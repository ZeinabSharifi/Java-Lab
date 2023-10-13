package view;

import controller.Controller;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import model.Model;
import model.Piece;

import javax.xml.crypto.dom.DOMCryptoContext;

public class View {
    private Controller controller ;
    private Model model ;
    private Pane view ;
    //public static Text colorA=new Text();
    Circle [] circles=new Circle[30];

   // Circle circle=new Circle(20);



    public View(Controller controller, Model model) {

        this.controller = controller ;
        this.model = model ;

        createAndConfigurePane();

        checkClick();


    }

    public Parent asParent() {
        return view ;
    }

    private void createAndConfigurePane() {
        view = new Pane();
        view.setPrefSize(1800, 880);

        int numberOfAddedCircles=0;
        Rectangle rectBottom=new Rectangle(0,860,1800,20);
        Rectangle rectRight=new Rectangle(1780,0,20,880);
        Rectangle rectLeft=new Rectangle(0,0,20,880);
        Rectangle rectTop=new Rectangle(0,0,1800,20);
        Rectangle rectL=new Rectangle(100,0,20,880);
        Rectangle rectM=new Rectangle(720,0,50,880);
        Rectangle rectR=new Rectangle(1370,0,20,880);
        Rectangle rectT=new Rectangle(1470,0,20,880);

        rectL.setFill(Color.valueOf("0x71310D"));
        rectM.setFill(Color.valueOf("0x71310D"));
        rectR.setFill(Color.valueOf("0x71310D"));
        rectT.setFill(Color.valueOf("0x71310D"));
        rectTop.setFill(Color.valueOf("0x71310D"));
        rectBottom.setFill(Color.valueOf("0x71310D"));
        rectRight.setFill(Color.valueOf("0x71310D"));
        rectLeft.setFill(Color.valueOf("0x71310D"));
        view.getChildren().addAll(rectTop,rectBottom,rectRight,rectLeft,rectL,rectM,rectR,rectT);



        Double x1=0.0;
        Double x2=0.0;
        Double[] x1Circle=new Double[24];
        Double[] x2Circle=new Double[24];
        Double y1;
        Double y2;
        boolean isClockwise=model.type1==model.getType()?true:false;
        boolean isDown;
        for(int i =1;i<25;i++){
            Polygon triangle=new Polygon();
            isDown=i<13?true:false;
            y1=isDown?860.0:20.0;
            y2=isDown?510.0:370.0;

            switch (model.getBoard().get(i-1).getNumber()){  //type1:clockwise  type2:counterclockwise
                case 1:
                    x1=isClockwise?1370.0:120.0;
                    x2=isClockwise?1270.0:220.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    view.getChildren().add(triangle);
                    triangle.setFill(Color.valueOf("0xBB8346"));

                    break;
                case 2:
                    x1=isClockwise?1270.0:220.0;
                    x2=isClockwise?1170.0:320.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    view.getChildren().add(triangle);
                    triangle.setFill(Color.valueOf("0x523608"));
                    break;
                case 3:
                    x1=isClockwise?1170.0:320.0;
                    x2=isClockwise?1070.0:420.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    break;
                case 4:
                    x1=isClockwise?1070.0:420.0;
                    x2=isClockwise?970.0:520.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    break;
                case 5:
                    x1=isClockwise?970.0:520.0;
                    x2=isClockwise?870.0:620.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    break;
                case 6:
                    x1=isClockwise?870.0:620.0;
                    x2=isClockwise?770.0:720.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    break;
                case 7:
                    x1=isClockwise?720.0:770.0;
                    x2=isClockwise?620.0:870.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    break;
                case 8:
                    x1=isClockwise?620.0:870.0;
                    x2=isClockwise?520.0:970.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    break;
                case 9:
                    x1=isClockwise?520.0:970.0;
                    x2=isClockwise?420.0:1070.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    break;
                case 10:
                    x1=isClockwise?420.0:1070.0;
                    x2=isClockwise?320.0:1170.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    break;
                case 11:
                    x1=isClockwise?320.0:1170.0;
                    x2=isClockwise?220.0:1270.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    break;
                case 12:
                    x1=isClockwise?220.0:1270.0;
                    x2=isClockwise?120.0:1370.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    break;
                case 13:
                    x1=isClockwise?120.0:1370.0;
                    x2=isClockwise?220.0:1270.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    break;
                case 14:
                    x1=isClockwise?220.0:1270.0;
                    x2=isClockwise?320.0:1170.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    break;
                case 15:
                    x1=isClockwise?320.:1170.0;
                    x2=isClockwise?420.0:1070.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    break;
                case 16:
                    x1=isClockwise?420.0:1070.0;
                    x2=isClockwise?520.0:970.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    break;
                case 17:
                    x1=isClockwise?520.0:970.0;
                    x2=isClockwise?620.0:870.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    break;
                case 18:
                    x1=isClockwise?620.0:870.0;
                    x2=isClockwise?720.0:770.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    break;
                case 19:
                    x1=isClockwise?770.0:720.0;
                    x2=isClockwise?870.0:620.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    break;
                case 20:
                    x1=isClockwise?870.0:620.0;
                    x2=isClockwise?970.0:520.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    break;
                case 21:
                    x1=isClockwise?970.0:520.0;
                    x2=isClockwise?1070.0:420.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    break;
                case 22:
                    x1=isClockwise?1070.0:420.0;
                    x2=isClockwise?1170.0:320.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    break;
                case 23:
                    x1=isClockwise?1170.0:320.0;
                    x2=isClockwise?1270.0:220.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0xBB8346"));
                    view.getChildren().add(triangle);
                    break;
                case 24:
                    x1=isClockwise?1270.0:220.0;
                    x2=isClockwise?1370.0:120.0;
                    triangle.getPoints().addAll(new Double[]{
                            x1,y1,x2,y1,(x1+x2)/2,y2
                    });
                    triangle.setFill(Color.valueOf("0x523608"));
                    view.getChildren().add(triangle);
                    break;
                default:x2=0.0;
                    x1=0.0;
            }
            x1Circle[i-1]=x1;
            x2Circle[i-1]=x2;

        }

        //add checkers on the board

        for (int i=1;i<25;i++){
            int tmpCountPiece= model.getBoard().get(i-1).getPieceCount();
            String c2 = (model.getBoard().get(i-1).getColor()==model.black)? "0xFFFFFF": "0x000000";
            String c1 = (model.getBoard().get(i-1).getColor()==model.black)? "0x000000": "0xFFFFFF";
            if(i<=12){

                if (tmpCountPiece <= 5) {
                    for(int j=1;j<=tmpCountPiece;j++) {
                        Circle circle=new Circle(35);
                        circle.setFill(Color.valueOf(c1));
                        circle.setStroke(Color.valueOf(c2));
                        if(isClockwise){
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
                            if(isClockwise){
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
                            if(isClockwise){
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
                        if(isClockwise){
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
                            if(isClockwise){
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
                            if(isClockwise){
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

       /* for (int i=0;i<24;i++){
            if (controller.getModel().getBoard().get(i).getPieceCount()!=0){
                for(int j=0;j<controller.getModel().getBoard().get(i).getPieceCount();j++){
                    System.out.println(i+" x:"+controller.getModel().getBoard().get(i).getCheckers().get(j).getX()+" y:"+
                            controller.getModel().getBoard().get(i).getCheckers().get(j).getY());
                }
            }
        }*/

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

            c.setOnMousePressed(e -> {
                nextY[0] = e.getSceneY();
                nextX[0] = e.getSceneX();
                c.toFront();
                X[0]=c.getTranslateX();
                Y[0]=c.getTranslateY();
            });

            c.setOnMouseDragged(e -> {
                Piece tmp=findPiece(X[0],Y[0]);
                if(tmp!=null?tmp.isTopChecker():false){
                    c.setTranslateX(e.getSceneX());
                    c.setTranslateY( e.getSceneY());
                }else{
                    c.setTranslateY(Y[0]);
                    c.setTranslateX(X[0]);
                }


            });

            c.setOnMouseReleased(e -> {
                double destX = (e.getSceneX());
                double destY = (e.getSceneY());
                double currentX = (c.getCenterX());
                double currentY = (c.getCenterY());

                Piece tmp=findPiece(X[0],Y[0]);
                if(true && (tmp!=null?tmp.isTopChecker():false)){   // TODO: 7/5/2019  read possible from model
                    c.setTranslateX(destX);
                    c.setTranslateY( destY);
                }else {
                    c.setTranslateY(Y[0]);
                    c.setTranslateX(X[0]);
                }

            });

        }

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
        return tmp;
    }





    public void doMove(){

    }

}
