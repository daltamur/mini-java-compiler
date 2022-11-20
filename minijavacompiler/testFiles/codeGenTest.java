class codeGenTest {
    public static void main(String[] a) {
        System.out.println(5*5*5-8+5);
    }
}

class codeGenGrandparent{
    public int testMethod() {
        int[] i;
        int j;
        i = new int[5];
        j = 10;
        System.out.println(j*i.length);
        return 0;
    }

    public int getZero(){
        return 0;
    }

    public int[] getArray(){
        return new int[new int[10].length - 2 + new int[15].length +2];
    }
}

class  codeGenParent extends codeGenGrandparent{}

class codeGenTestOtherClass extends codeGenParent{
    public codeGenTestOtherClass getThis(){
        return this;
    }

}

