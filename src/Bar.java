import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class Bar {
    Rectangle rect;
    Text text;
    int val;
    int index;
    Group root;
    private final double MAX_VAL = 100;
    private final double MAX_HEIGHT = 100;
    private final double WIDTH = 40;
    private final double X_PADDING = 20;
    private final double X_MARGIN = 5;
    private final double time = .75;

    public Bar(Group passRoot, int passVal) {
        root = passRoot;
        val = passVal;

        rect = new Rectangle(WIDTH, MAX_HEIGHT * val / MAX_VAL);
        rect.setLayoutY(root.getScene().getHeight() / 2 - rect.getHeight() + MAX_VAL / 2);
        rect.setFill(Color.rgb(82, 77, 84));

        text = new Text("" + val);
        text.setLayoutY(root.getScene().getHeight() / 2 + MAX_VAL / 2 + 15);
        text.setFont(Font.font("Arial"));
        text.setWrappingWidth(WIDTH);
        text.setTextAlignment(TextAlignment.CENTER);

        root.getChildren().add(rect);
        root.getChildren().add(text);
        index = 0;
    }

    public void animateSetIndex(int i, Runnable onFinish) {
        double dist = (i - index) * getFullWidth();
        final double cycles = time * 100;
        final int[] yCount = {1};
        int amplitude = 35;

        rect.toFront();
        text.toFront();

        Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), t -> {
            this.translateX((dist / cycles));
            this.setRelativePosY(-amplitude * Math.sin(20 * yCount[0] / cycles / 2 / Math.PI));
            yCount[0]++;
        }));
        loop.setCycleCount((int) cycles);
        loop.setOnFinished(event -> {
            this.setRelativePosY(0);
            if(onFinish != null) onFinish.run();
        });
        index = i;
        loop.play();
    }

    public void setIndex(int i) {
        setPosX(i * WIDTH + (i * X_MARGIN * 2));
        index = i;
    }

    public void setPosX(double x) {
        rect.setLayoutX(x + X_PADDING);
        text.setLayoutX(x + X_PADDING);
    }

    public void translateX(double xOffset) {
        rect.setLayoutX(rect.getLayoutX() + xOffset);
        text.setLayoutX(text.getLayoutX() + xOffset);
    }

    public void setRelativePosY(double y) {
        double startRect = root.getScene().getHeight() / 2 - rect.getHeight() + MAX_VAL / 2;
        rect.setLayoutY(startRect + y);
        double startText = root.getScene().getHeight() / 2 + MAX_VAL / 2 + 15;
        text.setLayoutY(startText + y);
    }

    public void setColor(Color c) {
        rect.setFill(c);
    }

    public double getFullWidth() {
        return WIDTH + (X_MARGIN * 2);
    }

    public double getX_PADDING() {
        return X_PADDING;
    }

    public int val() {
        return val;
    }

    public int getIndex() {
        return index;
    }
}
