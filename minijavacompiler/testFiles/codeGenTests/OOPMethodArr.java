class OOPMethodArr{
    public static void main(String[] args) {
        System.out.println(new MethodRunner().getClassVar().getArr().length - new MethodRunner().getClassVar().getArr().length + new MethodRunner().getClassVar().getArr().length * new MethodRunner().getClassVar().getArr().length * new MethodRunner().getClassVar().getArr().length + (new MethodRunner().getClassVar().getArr().length) - new MethodRunner().getClassVar().getArr().length * new MethodRunner().getClassVar().getArr().length);
    }
}

class MethodRunner{
    public MethodRunner getClassVar(){
        return new MethodRunner();
    }

    public int[] getArr(){
        return new int[5];
    }

}