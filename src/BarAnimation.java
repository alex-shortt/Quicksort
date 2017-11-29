public class BarAnimation {
    public int startIndex;
    public int endIndex;
    public boolean isSwap;

    private BarAnimation(int pass_x, int pass_y, boolean isSwap) {
        startIndex = pass_x;
        endIndex = pass_y;
        this.isSwap = isSwap;
    }

    public static BarAnimation swap(int x, int y) {
        return new BarAnimation(x, y, true);
    }

    public static BarAnimation sort(int low, int high) {
        return new BarAnimation(low, high, false);
    }
}
