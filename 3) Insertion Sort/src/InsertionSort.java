import java.util.ArrayList;
import java.util.Collections;

public class InsertionSort {
    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int index = 0; index < 100; index++) {
            testData.add((int)(Math.random() * 100));
        }

        InsertionSort iSort = new InsertionSort();
        iSort.sort(testData);

        for (int index = 0; index < 100; index++) {
            System.out.println(testData.get(index));
        }
    }

    static ArrayList<Integer> sort(ArrayList<Integer> dataList) {

        for (int i = 0; i < dataList.size() - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (dataList.get(j-1) > dataList.get(j)) {
                    Collections.swap(dataList, j-1, j);
                }
            }
        }

        return dataList;
    }

}
