class codeGenTest {
    public static void main(String[] a) {
        {
            System.out.println(7+8*47-(new int[new int[5].length-2].length*4+7)*234-9);
        }
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
    int testInt;
    public codeGenTestOtherClass getThis(){
        return this;
    }

    public int doAction(int i){
        int x;
        if(i<10){
            testInt = i;
            System.out.println(testInt);
            x = this.doAction(i+1);
        }else{
            testInt = i;
        }
        return testInt;
    }

    public int getTestInt(){
        return testInt;
    }

    public int changeInt(int x){
        testInt = x;
        return 0;
    }

}

