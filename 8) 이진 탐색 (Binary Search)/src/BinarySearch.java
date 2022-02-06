import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class BinarySearch {
    public boolean searchFunc(ArrayList<Integer> dataList, Integer serachData) {

        // 예외 CASE 1 - 리스트의 크기가 1이고 그 값이 찾는 값일 경우
        if (dataList.size() == 1 && serachData == dataList.get(0)) {
            return true;
        }

        // 예외 CASE 2 - 리스트의 크기가 1이고 그 값이 찾는 값이 아닐 경우
        if (dataList.size() == 1 && serachData != dataList.get(0)) {
            return false;
        }

        // 예외 CASE 3 - 리스트의 크기가 0인 경우
        if (dataList.size() == 0) {
            return false;
        }

        // mid 기준점 설정
        Integer mid = dataList.size() / 2;

        // mid 값이 찾는 값인 경우
        if (serachData == dataList.get(mid)) {
            return true;

        // mid 값이 찾는 값이 아닌 경우
        } else {
            // searchData가 mid값보다 크면 뒤쪽 subList에서 찾고
            if (serachData > dataList.get(mid)) {
                dataList = new ArrayList<>(dataList.subList(mid, dataList.size()));
                return this.searchFunc(dataList, serachData);

            // searchData가 mid값보다 작으면 앞쪽 subList에서 찾기
            } else {
                dataList = new ArrayList<>(dataList.subList(0, mid));
                return this.searchFunc(dataList, serachData);
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<Integer> testData = new ArrayList<Integer>();

        for (int index = 0; index < 100; index++) {
            testData.add((int)(Math.random() * 100));
        }

        // 이진 탐색은 꼭 정렬된 데이터에서 해야 한다.
        Collections.sort(testData);
        System.out.println(testData);

        BinarySearch bSearch = new BinarySearch();
        boolean b = bSearch.searchFunc(testData, 0);
        System.out.println(b);

    }
}