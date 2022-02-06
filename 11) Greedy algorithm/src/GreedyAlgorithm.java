import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class GreedyAlgorithm {
    public void coinFuc(Integer price, ArrayList<Integer> coinList) {
        // 총 동전 개수
        Integer totalCoinCount = 0;
        Integer coinNum = 0;
        ArrayList<Integer> details = new ArrayList<>();

        for (int index = 0; index < coinList.size(); index++) {
            coinNum = price / coinList.get(index);

            // 총 동전 개수 카운트
            totalCoinCount += coinNum;

            // 남은 가격 갱신
            price -= coinNum * coinList.get(index);

            details.add(coinNum);
            System.out.println(coinList.get(index) + "원" + coinNum + "개");

        }

        System.out.println("총 동전 개수 : " + totalCoinCount);

    }

    public static void main(String[] args) {
        GreedyAlgorithm gObject = new GreedyAlgorithm();
        ArrayList<Integer> coinlist = new ArrayList<Integer>(Arrays.asList(500, 100, 50, 1));
        gObject.coinFuc(4720, coinlist);
    }

}
