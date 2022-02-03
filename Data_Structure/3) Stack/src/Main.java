import java.util.ArrayList;

class MyStack<T> {
    private ArrayList<T> stack = new ArrayList<T>();

    public void push(T item) {
        stack.add(item);
    }

    public T pop() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        MyStack<Integer> ms = new MyStack<Integer>();
        ms.push(1);
        ms.push(2);
        System.out.println(ms.pop()); // 2
        ms.push(3);
        System.out.println(ms.pop()); // 3
        System.out.println(ms.pop()); // 1
    }
}