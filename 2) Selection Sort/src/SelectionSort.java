import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class SelectionSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList) {

        // 최솟값
        int lowest;

        for (int stand = 0; stand<dataList.size() - 1; stand++) {
            // 기준을 최솟값으로 잡고
            lowest = stand;

            // 기준+1 부터 끝까지 최솟값 탐색해서 갱신
            for (int index = stand; index < dataList.size() - 1; index++) {
                if (dataList.get(lowest) > dataList.get(index)) {
                    lowest = index;
                }
            }

            // 최솟값고 기준값 위치 swap
            Collections.swap(dataList, lowest, stand);
        }

        return dataList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int i = 0; i < 100; i++) {
            testData.add((int)(Math.random() * 100));
        }
        SelectionSort sSort = new SelectionSort();
        sSort.sort(testData);

        for (int i = 0; i < 100; i++) {
            System.out.println(testData.get(i)+" ");
        }
    }
}
