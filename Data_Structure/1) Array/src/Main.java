public class Main {
    public static void main(String[] args) {

        // 문자열.indexof(String key)
        //     : 문자 key가 해당 문자열에 있으면 해당 문자의 위치 (index 값)을 리턴하고,
        //       없으면 -1 을 리턴함

        String dataset[] = {
                "Braund, Mr. Owen Harris",
                "Cumings, Mrs. John Bradley (Florence Briggs Thayer)",
                "Heikkinen, Miss. Laina",
        };

        Integer count = 0;

        for (Integer item=0; item < dataset.length; item++) {
            if (dataset[item].indexOf("M") >= 0) {
                // System.out.println(dataset[item]);
                count++;
            }
        }

        System.out.println(count); // 3
    }
}
