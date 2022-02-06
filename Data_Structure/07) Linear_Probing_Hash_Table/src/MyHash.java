public class MyHash {
    public Slot[] hashTable;

    public MyHash(Integer size) {
        this.hashTable = new Slot[size];
    }

    public class Slot {
        String key;
        String value;
        Slot(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public int hashFunc(String key) {
        return (int)(key.charAt(0))% this.hashTable.length;
    }

    public boolean saveData(String key, String value) {
        Integer address = this.hashFunc(key);

        if(this.hashTable[address] != null) {
            // 찾는 Slot이면
            if(this.hashTable[address].key == key) {
                // 값 저장
                this.hashTable[address].value = value;
                return true;
            } else {
                // 다음 슬롯으로 이동
                Integer currAddress = address + 1;
                // 그 주소를 가진 슬롯이 존재한다면
                while(this.hashTable[currAddress] != null) {
                    // 그 슬롯이 찾는 슬롯이라면
                    if(this.hashTable[currAddress].key == key) {
                        // 값 저장
                        this.hashTable[currAddress].value = value;
                    } else {
                        // 다음 슬롯으로 이동
                        currAddress++;
                        // 배열의 사이즈를 넘어서면
                        if(currAddress >= this.hashTable.length) {
                            return false;
                        }
                    }
                }

                // 그 주소를 가진 슬롯이 없다면
                this.hashTable[currAddress] = new Slot(key, value);
                return true;
            }

        } else {
            this.hashTable[address] = new Slot(key, value);
        }

        return true;
    }

    public String getData(String key) {
        Integer address = this.hashFunc(key);

        // 슬롯이 있으면
        if(this.hashTable[address] != null) {
            // 그 슬롯의 키가 찾는 키라면
            if(this.hashTable[address].key == key) {
                return this.hashTable[address].value;
            } else {
                // 아니라면 다음 슬롯으로
                Integer currAddress = address + 1;

                // 슬롯의 끝까지 이동하면서 탐색
                while(this.hashTable[currAddress] != null) {
                    // 해당 슬롯의 키가 찾는 키라면
                    if(this.hashTable[currAddress].key == key) {
                        return this.hashTable[currAddress].value;
                    } else {
                        // 다음 슬롯으로 이동
                        currAddress++;
                        if(currAddress >= hashTable.length) {
                            return null;
                        }
                    }
                }

                // 슬롯의 끝까지 이동하면서 탐색해도 없으면 null 반환
                return null;
            }

        } else {
            // 슬롯이 없으면 null
            return null;
        }
    }

}