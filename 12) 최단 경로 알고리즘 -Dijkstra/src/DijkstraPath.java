import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class DijkstraPath {
    static class Edge implements Comparable<Edge>{
        public int distance;
        public String vertex;

        public Edge(int distance, String vertex) {
            this.distance = distance;
            this.vertex = vertex;
        }

        // System.out.println()으로 객체 자체 출력시
        public String toString() {
            return "vertex: " + this.vertex + ", distance: " + this.distance;
        }

        @Override
        public int compareTo(Edge edge) {
            return this.distance - edge.distance;
        }
    }

    public HashMap<String, Integer> dijkstraFunc(HashMap<String, ArrayList<Edge>> graph, String start) {
        HashMap<String, Integer> distances = new HashMap<String, Integer>();

        for (String key : graph.keySet()) {
            // 그래프의 key와 INF 초기화
            distances.put(key, Integer.MAX_VALUE);
        }

        // 처음 시작 노드 초기화
        distances.put(start, 0);

        // 우선순위 큐 생성
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();

        // 첫 노드와 연관된 노드들 우선순위 큐에 넣기
        priorityQueue.add(new Edge(distances.get(start), start));

        /*
            다익스트라 알고리즘 시작
         */

        // 추출한 노드, 노드의 거리,이름 정보
        Edge edgeNode;
        int currentDistance;
        String currentNode;

        // 추출한 노드와 연관된 노드 리스트
        ArrayList<Edge> nodeList;

        // 거리 계산된 변수
        int distance;

        // 연관된 노드 리스트에서 뽑은 Edge
        Edge adjacentNode;
        String adjacent;    // adjacentNode의 이름
        int weight;         // adjacentNode의 거리

        while (priorityQueue.size() > 0) {
            // 우선순위 큐에서 추출
            edgeNode = priorityQueue.poll();
            currentDistance = edgeNode.distance;
            currentNode = edgeNode.vertex;

            // 현재의 거리보다 추출한 노드의 거리가 더 크면
            // 비교할 필요도 없고 갱신할 일도 없으니까 예외 처리
            if (distances.get(currentNode) < currentDistance) {
                continue;
            }

            // 추출한 노드와 연관된 노드들 추출해서 순회하면서 갱신
            nodeList = graph.get(currentNode);
            for (int index = 0; index < nodeList.size(); index++) {
                adjacentNode = nodeList.get(index);
                adjacent = adjacentNode.vertex;
                weight = adjacentNode.distance;
                distance = currentDistance + weight;

                // 계산된 거리가 저장된 거리보다 작을 경우
                if(distance < distances.get(adjacent)) {
                    // 갱신
                    distances.put(adjacent, distance);

                    // 갱신한 노드 우선순위큐에 add
                    priorityQueue.add(new Edge(distance, adjacent));
                }
            }
        }
        return distances;
    }

    public static void main(String[] args) {

        HashMap<String, ArrayList<Edge>> graph = new HashMap<String, ArrayList<Edge>>();


        graph.put("A", new ArrayList<Edge>(Arrays.asList(new Edge(8, "B"), new Edge(1, "C"), new Edge(2, "D"))));
        graph.put("B", new ArrayList<Edge>());
        graph.put("C", new ArrayList<Edge>(Arrays.asList(new Edge(5, "B"), new Edge(2, "D"))));
        graph.put("D", new ArrayList<Edge>(Arrays.asList(new Edge(3, "E"), new Edge(5, "F"))));
        graph.put("E", new ArrayList<Edge>(Arrays.asList(new Edge(1, "F"))));
        graph.put("F", new ArrayList<Edge>(Arrays.asList(new Edge(5, "A"))));

        DijkstraPath dObject = new DijkstraPath();
//        dObject.dijkstraFunc(graph, "A");

        System.out.println(dObject.dijkstraFunc(graph, "A"));
    }
}
