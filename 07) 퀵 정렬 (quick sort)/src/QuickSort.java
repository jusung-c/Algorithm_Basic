import java.util.ArrayList;
import java.util.Arrays;

public class QuickSort {
    public ArrayList<Integer> sort(ArrayList<Integer> dataList) {

        // 리스트의 크기가 1이 됐을 때 return
        if(dataList.size() <= 1) {
            return dataList;
        }

        // 기준점 pivot 설정
        int pivot = dataList.get(0);

        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();

        for(int index = 1; index<dataList.size(); index++) {
            if(dataList.get(index) > pivot) {
                rightArr.add(dataList.get(index));
            } else {
                leftArr.add(dataList.get(index));
            }
        }

        // 반환할 정렬된 mergeArr
        ArrayList<Integer> mergedArr = new ArrayList<>();

        // pivot으로 분류한 왼쪽, 오른쪽 리스트 다시 각각 sort로 분류 작업 반복
        mergedArr.addAll(this.sort(leftArr));
//          mergedArr.add(pivot);
        mergedArr.addAll(Arrays.asList(pivot));
        mergedArr.addAll(this.sort(rightArr));

        return mergedArr;

    }

    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int index = 0; index < 100; index++) {
            testData.add((int)(Math.random() * 100));
        }

        QuickSort qSort = new QuickSort();
        ArrayList<Integer> mergedList = qSort.sort(testData);
        System.out.println(mergedList);
    }
}
