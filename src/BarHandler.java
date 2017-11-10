import javafx.stage.Stage;

import java.util.ArrayList;

public class BarHandler {
    private ArrayList<Bar> bars = new ArrayList<>();
    private Stage stage;

    public BarHandler(Stage passStage){
        stage = passStage;
    }

    public void addBar(Bar bar){
        bar.setPosIndex(bars.size());
        bars.add(bar);
        stage.setWidth((bar.getX_PADDING() * 2) + (bars.size() * bar.getFullWidth()));
    }
}
