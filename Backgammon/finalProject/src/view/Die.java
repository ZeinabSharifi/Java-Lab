package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Die {
    ImageView dieFace;
    Image[] images;
    int dieFaceValue;

    public Die(Image[] images)
    {
        this.images = images;
        dieFace = new ImageView(this.images[0]);//set default to image 0
    }

    public Die(Image[] images, int dieFaceValue)
    {
        //Need to catch for values less than 1 and greater than 6!
        this.images = images;
        this.dieFaceValue=dieFaceValue;
        dieFace = new ImageView(this.images[dieFaceValue - 1]);
    }

    public ImageView getdieFace()
    {
        return dieFace;
    }

    public void setDieFace(int dieFaceValue)
    {
        //Need to catch for values less than 1 and greater than 6!
        dieFace.setImage(this.images[dieFaceValue - 1]);
    }

    public int getDieFaceValue() {
        return dieFaceValue;
    }

    public void setDieFaceValue(int dieFaceValue) {
        this.dieFaceValue = dieFaceValue;
    }
}
