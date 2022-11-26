class InstanceVariableChange{
    public static void main(String[] args) {
        System.out.println(new MethodRunner().runChange(10));
    }
}


class MethodRunner{
    int x;
    
    public int runChange(int changeVal){
        int placeHolder;
        placeHolder = this.changeVar(changeVal);
        return x;
    }
    
    public int changeVar(int changeVal){
        x = changeVal;
        return x;
    }
    
}