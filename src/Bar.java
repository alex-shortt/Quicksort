import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Bar {
    Rectangle rect;
    int val;
    Group root;
    private final double MAX_VAL = 100;
    private final double MAX_HEIGHT = 100;
    private final double WIDTH = 40;
    private final double X_PADDING = 20;
    private final double X_MARGIN = 5;

    public Bar (Group passRoot, int passVal){
        root = passRoot;
        val = passVal;
        rect = new Rectangle(WIDTH, MAX_HEIGHT *val / MAX_VAL);
        root.getChildren().add(rect);
        rect.setLayoutY(root.getScene().getHeight() / 2 - rect.getHeight() + MAX_VAL / 2);
        Random r = new Random();
        rect.setFill(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

        //add numbers to show val
    }

    public void setPosIndex(int i){
        setPosX(i *  WIDTH + (i * X_MARGIN *  2));
    }

    public void setPosX(double x){
        rect.setLayoutX(x + X_PADDING);
    }

    public void setColor(Color c){
        rect.setFill(c);
    }

    public double getFullWidth(){
        return WIDTH + (X_MARGIN * 2);
    }

    public double getX_PADDING(){
        return X_PADDING;
    }
}
