import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, String> map1 = new HashMap();
        map1.put(1, "사과");
        map1.put(2, "바나나");
        map1.put(3, "포도");

        HashMap<String, String> map2 = new HashMap();
        map2.put("DaveLee", "01033334444");
        map2.put("Dave", "01032221111");
        map2.put("David", "0104445555");

        map1.get(2);   // 바나나
        map2.get("Dave");    // 01032221111
    }
}
