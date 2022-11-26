class ArrayFromGrandparentTest{
    public static void main(String[] args) {
        System.out.println(new MethodRunner().MakeArray());
    }
}

class Grandparent{
    int[] arr;
}

class Parent extends Grandparent{
}

class MethodRunner extends Parent{
    public int MakeArray() {
        int x;
        arr = new int[5];
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
