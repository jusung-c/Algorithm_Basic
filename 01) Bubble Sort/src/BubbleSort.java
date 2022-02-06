import java.util.ArrayList;
import java.util.Collections;

public class BubbleSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList) {

        // 최대 n-1번 반복
        for(int index = 0; index<dataList.size()-1; index++) {
            boolean swap = false;

            // 뒤에서부터 차곡차곡 정렬되니까 굳이 뒤까지 다 돌리지말고 정렬된 부분 앞쪽까지 for문으로 swqp
            for (int index2 = 0; index2 < dataList.size() - 1 - index; index2++) {
                if (dataList.get(index2) > dataList.get(index2 + 1)) {
                    Collections.swap(dataList, index2, index2 + 1);
                    swap = true;
                }
            }

            // 자리 바꿈이 한번도 발생하지 않았다면 정렬이 끝났다는 소리니까 break;
            if(swap == false) {
                break;
            }

        }
        return dataList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            testData.add((int)(Math.random() * 100));
        }
        BubbleSort bSort = new BubbleSort();
        bSort.sort(testData);

        for (int i = 0; i < 100; i++) {
            System.out.print(testData.get(i)+" ");

        }
    }
}



