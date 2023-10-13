package view;
import javafx.scene.image.Image;


public class DieImages
{
    final Image die1 = new Image(getClass().getResourceAsStream("inverted-dice-1.png"));
    final Image die2 = new Image(getClass().getResourceAsStream("inverted-dice-2.png"));
    final Image die3 = new Image(getClass().getResourceAsStream("inverted-dice-3.png"));
    final Image die4 = new Image(getClass().getResourceAsStream("inverted-dice-4.png"));
    final Image die5 = new Image(getClass().getResourceAsStream("inverted-dice-5.png"));
    final Image die6 = new Image(getClass().getResourceAsStream("inverted-dice-6.png"));
    final Image die7 = new Image(getClass().getResourceAsStream("inverted-dice-1-1.png"));
    final Image die8 = new Image(getClass().getResourceAsStream("inverted-dice-2-2.png"));
    final Image die9 = new Image(getClass().getResourceAsStream("inverted-dice-3-3.png"));
    final Image die10 = new Image(getClass().getResourceAsStream("inverted-dice-4-4.png"));
    final Image die11 = new Image(getClass().getResourceAsStream("inverted-dice-5-5.png"));
    final Image die12 = new Image(getClass().getResourceAsStream("inverted-dice-6-6.png"));

    final Image[] images = new Image[12];


    public DieImages()
    {
        images[0] = die1;
        images[1] = die2;
        images[2] = die3;
        images[3] = die4;
        images[4] = die5;
        images[5] = die6;
        images[6] = die7;
        images[7] = die8;
        images[8] = die9;
        images[9] = die10;
        images[10] = die11;
        images[11] = die12;
    }

    public Image[] getImages()
    {
        return images;
    }
}