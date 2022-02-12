import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class PrimPath {

    // Edge만으로는 어디서부터 어디로 갔는지는 표현이 안된다.
    // mst 경로를 별도로 표시하기 위해 Path 클래스 작성
    // mst는 Path 객체를 저장해서 전체 최소 신장 트리를 이해한다.
    class Path {
        public String node1;
        public String node2;
        public int weight;

        public Path(String node1, String node2, int weight) {
            this.node1 = node1;
            this.node2 = node2;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" +
                    this.node1 +
                    ", " + this.node2 +
                    ", " + this.weight +
                    ')';
        }
    }

    // 그래프로 간선 정보를 표현할 때에는 Edge 사용
    class Edge implements Comparable<Edge> {

        public String node;
        public int weight;

        public Edge(String node, int weight) {
            this.node = node;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "(" +
                    this.node +
                    ", " + this.weight +
                    ')';
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }


    public ArrayList<Path> improvedPrimFunc(HashMap<String, HashMap<String, Integer>> graph, String startNode) {
        // mst에서 어디서 어디까지 이동했는 지를 알기 위해 Path 클래스 타입으로 mst를 표현한다.
        ArrayList<Path> mst = new ArrayList<Path>();

        // key값 오름차순 우선순위 큐.
        PriorityQueue<Edge> keys = new PriorityQueue<Edge>();

        Edge edgeObject, poppedEdge, linkedEdge;
        HashMap<String, String> mstPath = new HashMap<String, String>();
        HashMap<String, Edge> keysObjects = new HashMap<String, Edge>();
        HashMap<String, Integer> linkedEdges;
        Integer totalWeight = 0;


        // 다익스트라 알고리즘처럼 startNode 제외하고 나머지 INF로 초기화
        for (String key : graph.keySet()) {
            // 우선순위 큐에서 업데이트를 할 때에는 MinHeap 구조를 무너뜨리지 않기 위해
            // 특정 데이터를 remove한 후 add해주는 방법으로 업데이트해준다.

            if (key == startNode) {
                // 우선순위 큐의 타입이 Edge이므로 초기화할 Edge 만들어서 넣어준다 - startNode는 0으로
                edgeObject = new Edge(key, 0);

                // 연결되는 노드들의 정보를 알기 위해 matPath 저장 - startNode는 자신의 노드로만 접근하는 상태라서 해당 key값으로 초기화
                mstPath.put(key, key);
            } else {
                // 우선순위 큐의 타입이 Edge이므로 초기화할 Edge 만들어서 넣어준다 - 나머지는 INF로
                edgeObject = new Edge(key, Integer.MAX_VALUE);

                // 연결되는 노드들의 정보를 알기 위해 matPath 저장 - 나머지는 연결되지 않았으니까 null로 초기화
                mstPath.put(key, null);
            }

            // 우선 순위 큐에 [node:key]구조의 Edge 객체들을 전부 넣기
            keys.add(edgeObject);

            // 각각의 키 마다 edgeObject를 저장해두고 필요할 때 쓴다.
            keysObjects.put(key, edgeObject);
        }


        // 우선순위 큐가 빌때까지 반복 = 전체 노드를 한번씩 다 순회
        while (keys.size() > 0) {
            // 가장 키 값이 작은 노드 추출
            poppedEdge = keys.poll();

            // keys에서 추출했으니까 keysObjects에서도 강제 삭제 해주기
            keysObjects.remove(poppedEdge.node);

            // new Path(추출한 노드와 연결되어 있는 다른 node, 추출한 노드, 가중치) - 처음의 경우 new Path(A, A, 0)
            mst.add(new Path(mstPath.get(poppedEdge.node), poppedEdge.node, poppedEdge.weight));

            // 최소 신장 트리의 총 가중치
            totalWeight += poppedEdge.weight;

            // 그래프에서 추출한 노드와 연결된 노드들의 해시맵
            linkedEdges = graph.get(poppedEdge.node);

            // 추출한 노드와 연결된 노드들을 순회
            for (String adjacent : linkedEdges.keySet()) {
                // keysObjects에는 하나씩 추출해서 삭제해줬기 때문에 아직 추출이 안된 key들이 들어있다. - 첨에는 시작 노드 A빼고 다 들어있을 것이다.
                if (keysObjects.containsKey(adjacent)) {
                    // 연관된 노드들을 하나씩 가져오기 - 처음엔 A와 연관된 B, D
                    linkedEdge = keysObjects.get(adjacent); // 처음엔 INF가 들어있을 것이다

                    // 연관된 노드의 가중치와 비교 - 처음엔 A-B 가중치 7과 INF 비교 -> 더 작으니까 업데이트
                    if (linkedEdges.get(adjacent) < linkedEdge.weight) {
                        linkedEdge.weight = linkedEdges.get(adjacent); // 7로 업데이트

                        // 연결한 경로 추가 - 처음엔 mstPath.put(B, A)
                        mstPath.put(adjacent, poppedEdge.node);

                        // 우선순위 큐에 특정 데이터 업데이트 -> 삭제하고 추가하는 방식으로 업데이트
                        keys.remove(linkedEdge);
                        keys.add(linkedEdge);
                    }
                }
            }

        }

        System.out.println(totalWeight);
        return mst;
    }

    public static void main(String[] args) {
        HashMap<String, HashMap<String, Integer>> mygraph = new HashMap<String, HashMap<String, Integer>>();

        HashMap<String, Integer> edges;
        edges = new HashMap<String, Integer>();
        edges.put("B", 7);
        edges.put("D", 5);
        mygraph.put("A", edges);

        edges = new HashMap<String, Integer>();
        edges.put("A", 7);
        edges.put("D", 9);
        edges.put("C", 8);
        edges.put("E", 7);
        mygraph.put("B", edges);

        edges = new HashMap<String, Integer>();
        edges.put("B", 8);
        edges.put("E", 5);
        mygraph.put("C", edges);

        edges = new HashMap<String, Integer>();
        edges.put("A", 5);
        edges.put("B", 9);
        edges.put("E", 7);
        edges.put("F", 6);
        mygraph.put("D", edges);

        edges = new HashMap<String, Integer>();
        edges.put("B", 7);
        edges.put("C", 5);
        edges.put("D", 7);
        edges.put("F", 8);
        edges.put("G", 9);
        mygraph.put("E", edges);

        edges = new HashMap<String, Integer>();
        edges.put("D", 6);
        edges.put("E", 8);
        edges.put("G", 11);
        mygraph.put("F", edges);

        edges = new HashMap<String, Integer>();
        edges.put("E", 9);
        edges.put("F", 11);
        mygraph.put("G", edges);

        PrimPath pObject = new PrimPath();

        System.out.println(pObject.improvedPrimFunc(mygraph, "A"));

    }
}
