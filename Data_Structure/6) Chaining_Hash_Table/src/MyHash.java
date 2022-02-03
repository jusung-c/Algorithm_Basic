public class MyHash {
    public Slot[] hashTable;

    public MyHash(Integer size) {
        this.hashTable = new Slot[size];
    }

    public class Slot {
        String key;
        String value;
        Slot next;    // 링크드 리스트를 사용할 때 필요한 포인터
        Slot(String key, String value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    public int hashFunc(String key) {
        return (int)(key.charAt(0))% this.hashTable.length;
    }

    public boolean saveData(String key, String value) {
        Integer address = this.hashFunc(key);

        if(this.hashTable[address] != null) {
            // 링크드 리스트의 헤드부터 출발
            Slot findSlot = this.hashTable[address];
            // findSlot의 이전 노드. 처음엔 head
            Slot prevSlot = this.hashTable[address];

            // 링크드 리스트의 끝까지 돌면서 탐색
            while(findSlot != null) {
                // findSlot이 내가 찾는 key라면
                if(findSlot.key == key) {
                    // value 값 덮어쓰기
                    findSlot.value = value;
                    return true;
                } else {
                    // 한칸씩 옮겨주면서
                    prevSlot = findSlot;
                    findSlot = findSlot.next;
                }
            }

            // 못찾았을 경우 마지막에 Slot 생성해서 추가
            prevSlot.next = new Slot(key, value);

        } else {
            this.hashTable[address] = new Slot(key, value);
        }

        return true;
    }

    public String getData(String key) {
        Integer address = this.hashFunc(key);

        if(this.hashTable[address] != null) {
            // 링크드 리스트의 헤드부터 시작
            Slot findSlot = this.hashTable[address];

            // 링크드 리스트 끝까지 탐색하면서 찾기
            while (findSlot != null) {
                // 찾으면
                if(findSlot.key == key) {
                    return findSlot.value;
                } else {
                    // 다음 슬롯으로 이동
                    findSlot = findSlot.next;
                }

            }

            // 끝까지 탐색해도 못찾으면
            return null;

        }

        else {
            return null;
        }
    }

}