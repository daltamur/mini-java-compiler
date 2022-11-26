class IfBranch{
    public static void main(String[] args) {
        {
            System.out.println(new MethodRunner().IfStatement(0));
            System.out.println(new MethodRunner().IfStatement(1));
        }
    }
}

class MethodRunner{
    
    public int IfStatement(int x){
        int returned;
        if(x<1){
            returned = 1;
        }else{
            returned = 0;
        }
        return returned;
    }
    
    
}