public class DoubleLinkedList<T> {
    public Node<T> head = null;
    public Node<T> tail = null;

    public class Node<T> {
        T data;
        Node<T> prev = null;
        Node<T> next = null;

        public Node(T data) {
            this.data = data;
        }
    }

    // 노드의 끝에 데이터 추가하기
    public void addNode(T data) {
        if(this.head == null) {
            this.head = new Node<T>(data);
            this.tail = this.head;
        } else {
            Node<T> node = this.head;

            // 노드의 끝까지 이동
            while (node.next != null) {
                node = node.next;
            }

            // 끝 노드의 다음 노드로 데이터를 가진 새로운 노드 생성
            node.next = new Node<T>(data);

            // 생성한 노드의 prev를 끝 노드로 지정
            node.next.prev = node;

            // 지금 생성한 노드를 tail로 지정
            this.tail = node.next;
        }
    }

    public void printAll() {
        if(this.head != null) {
            Node<T> node = this.head;
            System.out.println(node.data);

            // 노드의 끝까지 탐색
            while(node.next != null) {
                node = node.next;
                System.out.println(node.data);
            }
        }
    }

    // head 부터 특정 노드를 찾는 메서드
    public T searchFromHead(T isData) {
        // 비어있으면 null 반환
        if(this.head == null) {
            return null;
        } else {
            Node<T> node = this.head;

            // 노드 끝까지 데이터 일치하는 노드 탐색
            while(node != null) {
                if(node.data == isData) {
                    return node.data;
                } else {
                    node = node.next;
                }
            }

            // 못찾으면 null 반환
            return null;
        }
    }

    public T searchFromTail(T isData) {
        // 비어있으면 null 반환
        if(this.head == null) {
            return null;
        } else {
            Node<T> node = this.tail;

            // 노드 끝까지 데이터 일치하는 노드 탐색
            while(node != null) {
                if(node.data == isData) {
                    return node.data;
                } else {
                    node = node.prev;
                }
            }

            // 못찾으면 null 반환
            return null;
        }
    }

    // 추가
    public boolean insertToFront(T existedData, T addData) {
        // 비어 있으면 head에 데이터 추가
        if (this.head == null) {
            this.head = new Node<T>(addData);
            this.tail = this.head;
            return true;
        }
        // head가 찾는 데이터 값일 경우
        else if (this.head.data == existedData) {
            // 새로운 노드 newHead로 생성
            Node<T> newHead = new Node<T>(addData);

            // 새로운 노드의 다음 노드를 현재 헤드 노드로 설정
            newHead.next = this.head;

            // 헤드를 새로운 노드로 설정
            this.head = newHead;

            // 찾았으니까 true
            return true;
        } else {
            // node에 헤드 담기
            Node<T> node = this.head;

            // 노드 끝까지 탐색
            while (node != null) {
                // 찾는 데이터 값을 찾으면
                if (node.data == existedData) {
                    // 찾은 노드의 이전 노드를 변수 nodePrev로 설정
                    Node<T> nodePrev = node.prev;

                    // next 설정
                    nodePrev.next = new Node<T>(addData);
                    nodePrev.next.next = node;

                    // prev 설정
                    nodePrev.next.prev = nodePrev;
                    node.prev = nodePrev.next;

                    // 찾았으니까 true
                    return true;
                } else {
                    node = node.next;
                }
            }

            // 못찾았으니까 false
            return false;
        }
    }
}