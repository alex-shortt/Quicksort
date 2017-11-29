import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Stack;

public class BarHandler {
    private ArrayList<Bar> bars = new ArrayList<>();
    private ArrayList<Bar> graphicalBars = new ArrayList<>();
    private Stage stage;

    final Color NORMAL_COLOR = Color.rgb(173, 216, 230);
    final Color PIVOT_COLOR = Color.rgb(255, 165, 0);
    final Color SORTED_COLOR = Color.rgb(230, 52, 29);
    final Color HIGHLITED_COLOR = Color.rgb(0, 128, 0);

    Stack<BarAnimation> animationStack = new Stack<>();

    public BarHandler(Stage passStage) {
        stage = passStage;
    }

    public void addBar(Bar bar) {
        bar.setIndex(bars.size());
        bar.setColor(NORMAL_COLOR);
        bars.add(bar);
        graphicalBars.add(bar);
        stage.setWidth((bar.getX_PADDING() * 2) + (bars.size() * bar.getFullWidth()));
    }

    public void switchBars(int x, int y) {
        if(x == y) return;
        System.out.println(bars.get(x).val() + " :: " + bars.get(y).val());
        animationStack.add(BarAnimation.swap(x, y));

        Bar temp = bars.get(x);
        bars.set(x, bars.get(y));
        bars.set(y, temp);
    }

    public void setPivot(int i) {
        for (Bar bar : bars) {
            bar.setColor(NORMAL_COLOR);
        }
        bars.get(i).setColor(PIVOT_COLOR);
    }

    private void sort(int left, int right) {
        if (left < right) {
            animationStack.add(BarAnimation.sort(left, right));
            int loc = partition(left, right);
            sort(left, loc - 1);
            sort(loc + 1, right);
        }
    }

    private int partition(int left, int right) {
        int pivot = bars.get(right).val(); //bars.get(right).val()
        int trail = left - 1;
        int temp;

        for (int walker = left; walker <= right - 1; walker++) {
            if (bars.get(walker).val() <= pivot) { //bars.get(walker).val()
                trail++;
                //swapping values
                switchBars(walker, trail);
                //end swapping values
            }
        }
        switchBars(right, trail + 1);
        return trail + 1;
    }

    public void sort() {
        sort(0, bars.size() - 1); //0, bars.size() - 1
    }

    BarAnimation currentRange = null;

    public void doQueue() {
        if (animationStack.size() <= 0){
            if(currentRange != null){
               for(int i= currentRange.startIndex; i <= currentRange.endIndex; ++i) {
                       graphicalBars.get(i).setColor(NORMAL_COLOR);
                   }
               }
            return;
        }
            

        BarAnimation anim = animationStack.remove(0);
        if (anim.isSwap) {
            graphicalBars.get(anim.startIndex).animateSetIndex(anim.endIndex, null);
            graphicalBars.get(anim.endIndex).animateSetIndex(anim.startIndex, () -> doQueue());

            Bar temp = graphicalBars.get(anim.startIndex);
            graphicalBars.set(anim.startIndex, graphicalBars.get(anim.endIndex));
            graphicalBars.set(anim.endIndex, temp);
        } else {
            if (currentRange != null) {
                for(int i= currentRange.startIndex; i <= currentRange.endIndex; ++i) {
                    graphicalBars.get(i).setColor(NORMAL_COLOR);
                }
            }
            currentRange = anim;
            for(int i= currentRange.startIndex; i <= currentRange.endIndex-1; ++i) {
                graphicalBars.get(i).setColor(HIGHLITED_COLOR);
            }
            graphicalBars.get(anim.endIndex).setColor(PIVOT_COLOR);
            doQueue();
        }
    }

    public void printBars() {
        System.out.print("{ ");
        for (int i = 0; i < bars.size() - 1; i++) {
            System.out.print(bars.get(i).val() + ", ");
        }
        System.out.println(bars.get(bars.size() - 1).val() + " }");
    }
}