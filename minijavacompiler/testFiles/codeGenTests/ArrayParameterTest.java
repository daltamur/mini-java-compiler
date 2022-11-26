class ArrayParameterTest{
    public static void main(String[] args) {
        System.out.println(new MethodRunner().MakeArray(new int[5]));
    }
}

class MethodRunner {

    public int MakeArray(int[] arr) {
        int x;
        x = 0;
        while (x < arr.length) {
            arr[x] = x;
            x = x + 1;
        }
        x = 0;
        while (x < arr.length) {
            System.out.println(arr[x]);
            x = x + 1;
        }
        return 0;
    }
}
