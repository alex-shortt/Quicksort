import javafx.stage.Stage;

import java.util.ArrayList;

public class BarHandler {
    private ArrayList<Bar> bars = new ArrayList<>();
    private Stage stage;

    public BarHandler(Stage passStage){
        stage = passStage;
    }

    public void addBar(Bar bar){
        bar.setIndex(bars.size());
        bars.add(bar);
        stage.setWidth((bar.getX_PADDING() * 2) + (bars.size() * bar.getFullWidth()));
    }

    public void switchBars(int x , int y){
        bars.get(x).animateSetIndex(y);
        bars.get(y).animateSetIndex(x);

        Bar temp = bars.get(x);
        bars.set(x, bars.get(y));
        bars.set(y, temp);
    }
}
