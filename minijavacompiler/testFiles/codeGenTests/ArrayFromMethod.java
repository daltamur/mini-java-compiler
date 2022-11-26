class ArrayFromMethod{
    public static void main(String[] args) {
        System.out.println(new MethodRunner().getArray()[0]);
    }
}

class MethodRunner{
    public int[] getArray(){
        int[] arr;
        arr = new int[1];
        arr[0] = 1000;
        return arr;
    }
}