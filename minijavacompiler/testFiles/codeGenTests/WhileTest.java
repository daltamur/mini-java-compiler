class WhileTest{
    public static void main(String[] args) {
        System.out.println(new MethodRunner().WhileLoop());
    }
}

class MethodRunner{
    public int WhileLoop(){
        int x;
        x = 0;
        while(x<10){
            System.out.println(x);
            x = x + 1;
        }
        return x;
    }
}