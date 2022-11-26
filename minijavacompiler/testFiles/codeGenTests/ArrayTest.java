class ArrayTest{
    public static void main(String[] args) {
        System.out.println(new MethodRunner().MakeArray());
    }
}

class MethodRunner{

    public int MakeArray(){
        int[] arr;
        int x;
        x = 0;
        arr = new int[5];
        while(x<arr.length){
            arr[x] = x;
            x = x+1;
        }
        x = 0;
        while(x < arr.length){
            System.out.println(arr[x]);
            x = x+1;
        }
        return 0;
    }

}