public class Quicksort {
    private static void sort(int[] list, int left, int right){
        if(left<right){
            int loc = partition(list,left,right);
            sort(list,left,loc-1);
            sort(list,loc+1,right);
        }
    }

    private static int partition(int[] list, int left, int right){
        int pivot = list[right];
        int trail = left-1;
        int temp;

        for(int walker = left;walker<=right-1;walker++){
            if(list[walker]<=pivot){
                trail++;
                //swapping values
                temp = list[walker];
                list[walker] = list[trail];
                list[trail] = temp;
                //end swapping values
            }
        }
        //swapping values
        temp = list[right];
        list[right] = list[trail+1];
        list[trail+1] = temp;
        //end swapping values
        return trail+1;
    }

    public static void sort(int[] list) {
        sort(list,0, list.length -1);
    }

    public void printArray(int[] numbers){
        System.out.print("{ ");
        for(int i = 0; i < numbers.length - 1; i++){
            System.out.print(numbers[i] + ", ");
        }
        System.out.println(numbers[numbers.length - 1] + " }");
    }
}
