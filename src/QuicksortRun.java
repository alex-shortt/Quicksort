import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Random;

public class QuicksortRun extends Application {

    private static Group root;
    private final int NUM_BARS = 10;
    private final int MAX_VAL = 100;

    public static void main(String[] args){
        Quicksort sort = new Quicksort();

        int[] numbers = new int[20];
        Random generator = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = generator.nextInt(100);
        }

        sort.printArray(numbers);
        sort.sort(numbers);
        sort.printArray(numbers);

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        root = new Group();
        Scene scene = new Scene(root, 800, 300);
        stage.setScene(scene);

        Random r = new Random();
        BarHandler barHandler = new BarHandler(stage);

        for(int i = 0; i < NUM_BARS; i++){
            barHandler.addBar(new Bar(root, r.nextInt(MAX_VAL)));
        }

        //begin
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Quicksort");
        stage.show();
    }
}
