class ParentInstanceVariableReturn{
    public static void main(String[] args) {
        System.out.println(new ChildClass().getParentVariable());
    }
}

class ParentClass{
    int ParentVariable;
}


class ChildClass extends ParentClass{

    public int getParentVariable(){
        return ParentVariable;
    }

}