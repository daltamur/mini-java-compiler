class codeGenTest {
    public static void main(String[] a) {
        System.out.println(new codeGenTestOtherClass().testMethod(0, new codeGenTestOtherClass()));
    }
}

class codeGenGrandparent{
    public int testMethod(int w, codeGenGrandparent x) {
        while(w < 100000000){
            //System.out.println(w);
            w = w + 1;
        }
        return w;
    }
}

class  codeGenParent extends codeGenGrandparent{}

class codeGenTestOtherClass extends codeGenParent{}

