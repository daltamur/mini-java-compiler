class GrandParentInstanceVariableChange{
    public static void main(String[] args) {
        System.out.println(new ChildClass().getGrandParentVariable());
    }
}

class GrandParentClass{
    int GrandParentVariable;
}

class ParentClass extends GrandParentClass{
    
}


class ChildClass extends ParentClass{

    public int getGrandParentVariable(){
        GrandParentVariable = 100;
        return GrandParentVariable;
    }

}