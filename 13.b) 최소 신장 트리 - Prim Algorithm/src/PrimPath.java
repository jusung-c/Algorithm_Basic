import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class PrimPath{
    public static class Edge implements Comparable<Edge> {
        public int weight;
        public String node1;
        public String node2;

        public Edge(int weight, String node1, String node2) {
            this.weight = weight;
            this.node1 = node1;
            this.node2 = node2;
        }

        @Override
        public String toString() {
            return "(" +
                    this.weight +
                    ", "+ this.node1 +
                    ", " + this.node2 +
                    ')';
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }

    }

    // 시작 노드, 간선들의 정보 인자로 받음
    public ArrayList<Edge> primFunc(String startNode, ArrayList<Edge> edges) {
        // 연결된 노드 집합
        ArrayList<String> connectedNodes = new ArrayList<String>();

        // 최소 신장 트리
        ArrayList<Edge> mst = new ArrayList<Edge>();

        Edge currentEdge;

        // 노드별로 연결된 모든 간선 정보 저장
        HashMap<String, ArrayList<Edge>> adjacentEdges = new HashMap<String, ArrayList<Edge>>();


        /*
        1. 해시맵 초기화 과정
        */

        for (int index = 0; index < edges.size(); index++) {
            currentEdge = edges.get(index);

            // 해시 값에 key 값 초기화 A~G, value는 초기화된 ArrayList.
            if (!adjacentEdges.containsKey(currentEdge.node1)) { // 중복방지
                adjacentEdges.put(currentEdge.node1, new ArrayList<Edge>());
            }
            if (!adjacentEdges.containsKey(currentEdge.node2)) { // 중복방지
                adjacentEdges.put(currentEdge.node2, new ArrayList<Edge>());
            }

        }

        // 해시맵 key값과 연관된 노드 추출
        ArrayList<Edge> currentEdgeList;
        for (int index = 0; index < edges.size(); index++) {
            currentEdge = edges.get(index);

            // key에 대응되는 value(currentEdgeList)에 각 노드별 연관된 노드 저장
            currentEdgeList = adjacentEdges.get(currentEdge.node1);
            currentEdgeList.add(new Edge(currentEdge.weight, currentEdge.node1, currentEdge.node2));
            currentEdgeList = adjacentEdges.get(currentEdge.node2);
            currentEdgeList.add(new Edge(currentEdge.weight, currentEdge.node2, currentEdge.node1));
        }

        // 연결된 노드 집합에 startNode 넣고 시작
        connectedNodes.add(startNode);

        // 연결된 노드와 연결되어 있는 노드들의 리스트 추출
        ArrayList<Edge> candidateEdgeList;
        // 연결되어 있는 노드가 없을 경우를 대비해 getOrDefault 사용
        candidateEdgeList = adjacentEdges.getOrDefault(startNode, new ArrayList<Edge>());

        // 연결되어 있는 노드들을 우선순위 큐에 넣어서 가중치 오름차순으로 정렬
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();
        for (int index = 0; index < candidateEdgeList.size(); index++) {
            priorityQueue.add(candidateEdgeList.get(index));
        }


        /*
        2. 프림 알고리즘 시작
        */

        Edge poppedEdge, adjacentEdgeNode;
        ArrayList<Edge> adjacentEdgeNodes;
        while (priorityQueue.size() > 0) {
            // 우선순위 큐에서 최소 가중치 하나 꺼내서
            poppedEdge = priorityQueue.poll();

            // 연결된 노드 리스트에 없다면 (사이클이 아니라면)
            // node1은 이미 들어가 있는 노드의 이름이고 node2 자리에 그 노드와 연결된 노드 이름이 들어있다.
            if (!connectedNodes.contains(poppedEdge.node2)) {
                // 해당 edge를 연결된 노드 리스트와 최소신장트리에 추가
                connectedNodes.add(poppedEdge.node2);
                mst.add(new Edge(poppedEdge.weight, poppedEdge.node1, poppedEdge.node2));

                // 방금 연결한 노드의 연관된 노드들을 추출
                adjacentEdgeNodes = adjacentEdges.getOrDefault(poppedEdge.node2, new ArrayList<Edge>());

                for (int index = 0; index < adjacentEdgeNodes.size(); index++) {
                    adjacentEdgeNode = adjacentEdgeNodes.get(index);

                    // 최소힙의 시간복잡도를 낮추기 위해 우선순위 큐에 이미 연결된 것들은 제외하고 넣어준다.
                    if (!connectedNodes.contains(adjacentEdgeNode.node2)) {
                        priorityQueue.add(adjacentEdgeNode);
                    }
                }
            }
        }

        return mst;
    }

    public static void main(String[] args) {

        ArrayList<Edge> myedges = new ArrayList<Edge>();
        myedges.add(new Edge(7, "A", "B"));
        myedges.add(new Edge(5, "A", "D"));
        myedges.add(new Edge(8, "B", "C"));
        myedges.add(new Edge(9, "B", "D"));
        myedges.add(new Edge(7, "D", "E"));
        myedges.add(new Edge(5, "C", "E"));
        myedges.add(new Edge(7, "B", "E"));
        myedges.add(new Edge(6, "D", "F"));
        myedges.add(new Edge(8, "E", "F"));
        myedges.add(new Edge(9, "E", "G"));
        myedges.add(new Edge(11, "F", "G"));

        PrimPath pObject = new PrimPath();

        System.out.println(pObject.primFunc("A", myedges));
    }

}
