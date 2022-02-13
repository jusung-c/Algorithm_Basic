import java.util.ArrayList;

public class NQueen {
    // 재귀 호출 - (N, 현재 탐색할 Row, 현재까지 선정된 퀸)
    public void dfsFunc(Integer N, Integer currentRow, ArrayList<Integer> currentCandidate)  {
        // 전체 다 탐색하면
        if (currentRow == N) {
            // 퀸의 위치 출력
            System.out.println(currentCandidate);
            return ;
        }

        // 현재 row의 첫번째 열부터 순회
        for (int index = 0; index < N; index++) {
            // 퀸의 위치로써 적합한지 판단
            if (this.isAvailable(currentCandidate, index) == true) {
                currentCandidate.add(index);

                // 다시 다음줄 탐색을 위해 재귀 호출
                this.dfsFunc(N, currentRow + 1, currentCandidate);

                // 만약 조건에 부합하는 게 없다면 (currentRow == N 조건에 부합하지 않는다면 = 출력물이 없다면)
                // 잘못된 경로이므로 가지치기를 해야한다 : Pruning (가지치기)
                currentCandidate.remove(currentCandidate.size() - 1);
            }
        }

    }

    public boolean isAvailable(ArrayList<Integer> candidate, Integer currentCol) {
        Integer currentRow = candidate.size();

        for (int index = 0; index < currentRow; index++) {

            // Promising : 해당 루트가 조건에 맞는지를 검사
            if ((candidate.get(index) == currentCol) || (Math.abs(candidate.get(index) - currentCol) == currentRow - index)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        NQueen nObject = new NQueen();
        nObject.dfsFunc(4, 0, new ArrayList<Integer>());
    }
}
