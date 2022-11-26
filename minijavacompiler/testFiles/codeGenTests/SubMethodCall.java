class SubMethodCall{
    public static void main(String[] args) {
        System.out.println(new MethodRunner().getOne() - new MethodRunner().getTwo());
    }
}

class MethodRunner{

    public int getOne(){
        return 1;
    }

    public int getTwo(){
        return 2;
    }

}