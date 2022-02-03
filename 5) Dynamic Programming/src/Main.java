/*

// recursive call 활용
class Factorial {
    static int f(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }

        return f(n - 1) + f(n - 2);
    }

    public static void main(String[] args) {
        Factorial fObject = new Factorial();
        System.out.println(fObject.f(10));
    }
}

*/

// 동적 계획법 활용
class Dynamic {
    static int d(int n) {
        Integer[] cache = new Integer[n + 1];
        cache[0] = 0;
        cache[1] = 1;

        for (int index = 2; index < n + 1; index++) {
            cache[index] = cache[index - 1] + cache[index - 2];
        }
        return cache[n];
    }

    public static void main(String[] args) {
        Dynamic dObject = new Dynamic();
        System.out.println(dObject.d(10));
    }
}