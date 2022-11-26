class MethodOverLoad{
    public static void main(String[] args) {
        {
            System.out.println(new MethodRunner().testMethod(1, 2));
            System.out.println(new MethodRunner().testMethod(2,2,2));
            System.out.println(new MethodRunner().testMethod());
        }
    }
}

class MethodRunner{

    public int testMethod(int x, int y){
        return x - y;
    }

    public int testMethod(int x, int y, int z){
        return x*y*z;
    }
    
    public char testMethod(){
        return 'a';
    }
    
    

}