public class Factorial {
    public static void main(String[] args) {
        Factorial fObject = new Factorial();

        System.out.println(fObject.factorialFunc(5));
    }

    public int factorialFunc(int i) {
        if(i==1) {
            return i;
        }

        return i * this.factorialFunc(i - 1);

    }

}


/*
public class Factorial {
    public static void main(String[] args) {
        Factorial fObject = new Factorial();

        System.out.println(fObject.factorialFunc(5));
    }

    public int factorialFunc(int i) {
        if(i>1) {
            return i * this.factorialFunc(i - 1);
        } else {
            return i;
        }
    }

}
*/


/*

// 1, 2, 3의 조합으로 나타내는 방법
public class Factorial {
    public static void main(String[] args) {
        Factorial fObject = new Factorial();

        System.out.println(fObject.f(5));
    }

    public int f(int n) {
        if(n==1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 4;
        }

        return this.f(n-1) + this.f(n-2) + this.f(n-3);
    }

}

*/
