class MethodWithParentTypeParams{
    public static void main(String[] args) {
        System.out.println(new MethodRunner().getValue(new ChildType()));
    }
}

class MethodRunner{
    public int getValue(GrandParentType curClass){
        int placeHolder;
        placeHolder = curClass.setZ(200);
        return curClass.getZ();
    }
}

class ChildType extends ParentType{
    int x;

    public int getX(){
        return x;
    }

    public int setx(int val){
        x = val;
        return 0;
    }
}

class ParentType extends GrandParentType{
    int y;
}

class GrandParentType{
    int z;

    public int getZ(){
        return z;
    }

    public int setZ(int val){
        z = val;
        return 0;
    }
}