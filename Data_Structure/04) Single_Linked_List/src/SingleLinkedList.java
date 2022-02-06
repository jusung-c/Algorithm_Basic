public class SingleLinkedList<T> {
    public Node<T> head = null;

    public class Node<T> {
        T data;
        Node<T> next = null;     // 다음 노드를 가리키는 포인터

        public Node(T data) {
            this.data = data;
        }
    }

    public void addNode(T data) {
        // 첫 번째 노드가 null이면
        if(head == null) {
            // 바로 head에 노드 생성
            head = new Node<T>(data);
        } else {
            // null이 아니면 head를 node에 담아준다.
            Node<T> node = this.head;

            // node의 next가 null이 아니면 다음 노드가 있다는 뜻
            // 다음 노드가 없을 때까지 현재 노드를 다음 노드로 바꿔주기
            while (node.next != null) {
                node = node.next;
            }

            // 끝 node까지 다 가면 그 다음 노드에 새로운 노드 생성
            node.next = new Node<T>(data);
        }
    }

    public void printAll() {
        if(head != null) {
            Node<T> node = this.head;
            System.out.println(node.data);

            // 노드 끝까지
            while(node.next != null) {
                // 다음 노드 지정
                node = node.next;
                // 값 출력
                System.out.println(node.data);
            }
        }
    }

    // data 값 가지고 있는 node 찾기
    public Node<T> search(T data) {
        if(head == null) {
            return null;
        } else {
            Node<T> node = this.head;

            // 링크드 리스트의 끝까지 탐색
            while (node!= null) {
                // 노드의 데이터를 찾은 경우
                if(node.data == data) {
                    return node;
                } else { // 못찾으면 다음 노드로 이동
                    node = node.next;
                }
            }

            // 끝까지 돌려도 못찾았을 경우 head == null이 되니까 null 반환
            return null;
        }
    }

    public void addNodeInside(T data, T isData) {
        // 먼저 isData 값을 가지고 있는 노드를 찾는다.
        Node<T> searchedNode = this.search(isData);

        // 리턴 받은 노드 객체가 null이면
        if(searchedNode == null) {
            // isData를 가지고 있는 노드가 없으므로 그냥 맨 마지막에 추가
            this.addNode(data);
        } else {
            // 반환 받은 노드의 다음 노드를 nextNode에 담기
            Node<T> nextNode = searchedNode.next;

            // 리턴 받은 노드 다음 노드를 새로운 노드(data)로 설정(생성)
            searchedNode.next = new Node<T>(data);

            // 새로 만든 노드의 다음 노드를 기존 노드의 다음 노드로 지정
            searchedNode.next.next = nextNode;
        }
    }

    // 삭제 성공하면 true, 실패하면 null return
    public boolean delNode(T isData) {
        if(this.head == null) {
            return false;
        } else {
            Node<T> node = this.head;
            // head가 삭제할 노드라면
            if(node.data == isData) {
                // head의 다음 노드를 head로 지정
                this.head = this.head.next;
                return true;
            } else {
                // 노드 끝까지 탐색
                while(node.next != null) {
                    // 다음 노드의 데이터가 삭제해야할 데이터라면
                    if(node.next.data == isData) {
                        // 노드의 다음 노드를 노드의 다음 다음 노드로 설정
                        node.next = node.next.next;
                        return true;
                    }

                    // 다음 노드로 이동
                    node = node.next;
                }

                // 노드의 끝까지 가도 삭제할 데이터가 없을 때 false 반환
                return false;
            }
        }

    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> LL = new SingleLinkedList<Integer>();
        LL.addNode(1);
        LL.addNode(2);
        LL.addNode(3);
        LL.addNode(4);
        LL.addNode(5);

        LL.delNode(3);
        LL.delNode(1);

        LL.printAll();
    }
}