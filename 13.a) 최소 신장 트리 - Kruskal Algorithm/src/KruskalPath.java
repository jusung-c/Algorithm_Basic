import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class KruskalPath {
    // 내 노드의 부모 노드를 저장한 parent
    HashMap<String, String> parent = new HashMap<String, String>();

    // 각 노드별 랭크 정보
    HashMap<String, Integer> rank = new HashMap<String, Integer>();


    public static class Edge implements Comparable<Edge> {

        public int weight;
        public String nodeV;
        public String nodeU;

        public Edge(int weight, String nodeV, String nodeU) {
            this.weight = weight;
            this.nodeV = nodeV;
            this.nodeU = nodeU;
        }

        @Override
        public String toString() {
            return "(" + weight +
                    ", " + nodeV +
                    ", " + nodeU +
                    ')';
        }

        @Override
        public int compareTo(Edge edge) {
            // 간선의 가중치 기준 오름차순
            return this.weight - edge.weight;
        }
    }

    // 루트 노드 find + path compression 기법 적용
    public String find(String node) {
        /*
        // 현재 노드의 부모가 현재 노드가 아니라면
        if (parent.get(node) != node) {
            // 재귀용법으로 루트 노드를 찾는다.
            return find(parent.get(node));
        }
        */

        // 위 코드에 path compression 기법을 사용해 rank를 낮춰주자
        // 현재 노드의 부모가 현재 노드가 아니라면
        if (parent.get(node) != node) {
            // 현재 노드의 부모 노드가 부모 노드의 부모 노드를 찾게끔 parent에 설정
            //              -> 결국 루트 노드를 찾아서 부모 노드로 설정할 때까지 재귀
            //              루트 노드가 하나 있고 거기에 탐색되는 모든 노드들은 부모를 루트 노드로 가지고 있게 된다.
            parent.put(node, find(parent.get(node)));
        }

        return parent.get(node);
    }

    // nodeV와 nodeU를 연결했을 때 사이클이 안생긴다는 전제 하에 union 호출
    // 두 트리를 병합해주는 union
    public void union(String nodeV, String nodeU) {
        String root1 = find(nodeV);
        String root2 = find(nodeU);

        // union-by-rank 기법
        // nodeV, nodeU의 루트 노드 rank를 비교
        if (rank.get(root1) > rank.get(root2)) {
            // root2의 부모를 root1로
            parent.put(root2, root1);
        } else {
            // root1의 부모를 root2로
            parent.put(root1, root2);

            // 둘의 rank가 같다면
            if (rank.get(root1) == rank.get(root2)) {
                // 부모 노드로 만들어준 root2의 rank를 올려준다.
                rank.put(root2, rank.get(root2) + 1);
            }
        }
    }

    // parent, rank 초기화
    public void makeSet(String node) {
        // 개별 집합으로 이뤄지도록 초기화
        parent.put(node, node);

        // rank 0으로 초기화
        rank.put(node, 0);
    }

    public ArrayList<Edge> kruskalFunc(ArrayList<String> vertices, ArrayList<Edge> edges) {
        // 최소 신장 트리
        ArrayList<Edge> mst = new ArrayList<Edge>();

        Edge currentEdge;

        // 1. 초기화
        for (int index = 0; index < vertices.size(); index++) {
            makeSet(vertices.get(index));
        }


        // 2. 간선 weight 기반 sorting
        Collections.sort(edges);

        for (int index = 0; index < edges.size(); index++) {
            // 가중치가 낮은 순으로 간선 정보 가져오기
            currentEdge = edges.get(index);

            // 사이클이 안생기는 경우 (루트 노드가 다른 경우)
            if (find(currentEdge.nodeV) != find(currentEdge.nodeU)) {
                // union 연결
                union(currentEdge.nodeV, currentEdge.nodeU);

                // 최소신장트리에 추가
                mst.add(currentEdge);
            }

        }

        return mst;
    }

    public static void main(String[] args) {

        // 노드 정보
        ArrayList<String> vertices = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));

        // 간선 정보
        ArrayList<Edge> edges = new ArrayList<Edge>();

        edges.add(new Edge(7, "A", "B"));
        edges.add(new Edge(5, "A", "D"));
        edges.add(new Edge(7, "B", "A"));
        edges.add(new Edge(8, "B", "C"));
        edges.add(new Edge(9, "B", "D"));
        edges.add(new Edge(7, "B", "E"));
        edges.add(new Edge(8, "C", "B"));
        edges.add(new Edge(5, "C", "E"));
        edges.add(new Edge(5, "D", "A"));
        edges.add(new Edge(9, "D", "B"));
        edges.add(new Edge(7, "D", "E"));
        edges.add(new Edge(6, "D", "F"));
        edges.add(new Edge(7, "E", "B"));
        edges.add(new Edge(5, "E", "C"));
        edges.add(new Edge(7, "E", "D"));
        edges.add(new Edge(8, "E", "F"));
        edges.add(new Edge(9, "E", "G"));
        edges.add(new Edge(6, "F", "D"));
        edges.add(new Edge(8, "F", "E"));
        edges.add(new Edge(11, "F", "G"));
        edges.add(new Edge(9, "G", "E"));
        edges.add(new Edge(11, "G", "F"));


        KruskalPath kp = new KruskalPath();

        System.out.println(kp.kruskalFunc(vertices, edges));

    }
}
