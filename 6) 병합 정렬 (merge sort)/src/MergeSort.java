import java.util.ArrayList;

public class MergeSort {
    public ArrayList<Integer> mergeSplitFunc(ArrayList<Integer> dataList) {

        // 배열의 크기가 1보다 작으면 그대로 리턴
        if (dataList.size() <= 1) {
            return dataList;
        }

        int medium = dataList.size() / 2;

        ArrayList<Integer> leftArr = new ArrayList<Integer>();
        ArrayList<Integer> rightArr = new ArrayList<Integer>();

        // 배열의 크기가 1이 될때까지 재귀적으로 호출해서
        // 정렬되지 않는 배열을 끝까지 분리하기
        leftArr = mergeSplitFunc(new ArrayList<Integer>(dataList.subList(0, medium)));
        rightArr = mergeSplitFunc(new ArrayList<Integer>(dataList.subList(medium, dataList.size())));

        // mergeFunc로 정렬 + 합병
        return mergeFunc(leftArr, rightArr);
    }

    private ArrayList<Integer> mergeFunc(ArrayList<Integer> leftList, ArrayList<Integer> rightList) {
        // 반환할 병합된 리스트 (정렬된 상태)
        ArrayList<Integer> mergedList = new ArrayList<Integer>();
        Integer leftPoint = 0;
        Integer rightPoint = 0;


        // CASE 1 : left/right 둘 다 있을 때
        while (leftList.size() > leftPoint && rightList.size() > rightPoint) {
            // 왼쪽 리스트와 오른쪽 리스트의 Point 값 비교
            // 왼쪽 리스트가 더 크면 작은 오른쪽 리스트의 값을 mergeList에 추가
            if (leftList.get(leftPoint) > rightList.get(rightPoint)) {
                mergedList.add(rightList.get(rightPoint));
                rightPoint++;

            // 오른쪽 리스트가 더 크면 작은 왼쪽 리스트의 값을 mergeList에 추가
            } else {
                mergedList.add(leftList.get(leftPoint));
                leftPoint++;
            }
        }

        // CASE 2 : right 데이터가 없을 때 = left 데이터만 남아있을 때
        while (leftList.size() > leftPoint) {
            mergedList.add(leftList.get(leftPoint));
            leftPoint++;
        }

        // CASE 3 : left 데이터가 없을 때 = right 데이터만 남아있을 때
        while (rightList.size() > rightPoint) {
            mergedList.add(rightList.get(rightPoint));
            rightPoint ++;
        }

        return mergedList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int index = 0; index < 100; index++) {
            testData.add((int)(Math.random() * 100));
        }

        MergeSort mSort = new MergeSort();
        ArrayList<Integer> mergedList = mSort.mergeSplitFunc(testData);
        System.out.println(mergedList);
    }
}


// 가장 작은 단위로 분할
// 49-97-53-5-33-65-62-51

// 49-97-53-5   33-65-62-51

// 49-97    53-5    33-65   62-51

// 49  97  53  5  33  65  62  51



// merge 시작
// 49-97    5-53    33-65   51-62

// 5-49-53-97   33-51-62-65

// 5-33-49-51-53-62-65-97

