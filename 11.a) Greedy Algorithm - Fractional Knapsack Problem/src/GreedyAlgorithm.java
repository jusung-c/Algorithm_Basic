/*import java.util.Arrays;
import java.util.Comparator;

public class GreedyAlgorithm {
    public static class Edge implements Comparable<Edge> {
        public Integer distance;
        public String vertex;

        public Edge(Integer distance, String vertex) {
            this.distance = distance;
            this.vertex = vertex;
        }

        @Override
        public int compareTo(Edge e) {
//            return this.distance - e.distance;
            return e.distance - this.distance;
        }
    }

    public static void main(String[] args) {
        Edge edge1 = new Edge(10, "A");
        Edge edge2 = new Edge(12, "A");
        Edge edge3 = new Edge(13, "A");
        Edge[] edges = new Edge[]{edge1, edge2, edge3};
//        Arrays.sort(edges);

        // Comparator가 우선순위를 가진다.
        Arrays.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
//                return o1.distance - o2.distance;
                return o2.distance - o1.distance;
            }
        });

        for (int i = 0; i < edges.length; i++) {
            System.out.println(edges[i].distance);
        }
    }
}*/
import java.util.Arrays;
import java.util.Comparator;

public class GreedyAlgorithm {
    public void knapsackFunc(Integer[][] objectList, double capacity) {
        // 최대 가치
        double totalValue = 0.0;

        // 물건을 쪼갤 경우 비율
        double fraction = 0.0;

        // 무게당 가치 계산해서 가치 높은 순으로 정렬
        Arrays.sort(objectList, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                // 무게 / 가치 = 무게당 가치 계산
                return (o2[1] / o2[0]) - (o1[1] / o1[0]);
            }
        });

        // 정렬한 물건 리스트로 탐욕 알고리즘
        for (int index = 0; index < objectList.length; index++) {
            // 앞에서부터 하나씩 넣기 (최대 용량 내에서)
            if ((capacity - (double) objectList[index][0]) > 0) {
                capacity -= (double) objectList[index][0];
                totalValue += objectList[index][1];
                System.out.println("무게: " + objectList[index][0] + ", 가치: " + objectList[index][1]);

            // 최대 용량을 벗어나서 물건을 쪼개야 할 경우
            } else {
                fraction = capacity / objectList[index][0];

                // 일부 넣은 무게의 가치 계산
                totalValue += objectList[index][1] * fraction;

                System.out.println("무게: " + objectList[index][0] * fraction+ ", 가치: " + objectList[index][1] * fraction);
                break;
            }
        }
        System.out.println("총 담을 수 있는 가치: " + totalValue);

    }

    public static void main(String[] args) {
        Integer[][] objectList = { {10, 10}, {15, 12}, {20, 10}, {25, 8}, {30, 5} };

        GreedyAlgorithm ga = new GreedyAlgorithm();
        ga.knapsackFunc(objectList, 30.0);
    }
}
