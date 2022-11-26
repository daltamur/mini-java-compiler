class MethodArrSub{
    public static void main(String[] args) {
        System.out.println(new MethodRunner().getClass().getArr().length - new MethodRunner().getClass().getArr().length);
    }
}

class MethodRunner{
    public MethodRunner getClass(){
        return new MethodRunner();
    }

    public int[] getArr(){
        return new int[5];
    }

}