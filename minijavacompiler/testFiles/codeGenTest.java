class codeGenTest {
    public static void main(String[] a) {
        System.out.println('?');
    }
}

class codeGenTestOtherClass{
    char x;
    public char testMethod(char w){
        int r;
        char y;
        char z;
        boolean t;
        boolean testVal;
        boolean testVal2;
        testVal = true;
        testVal2 = false;
        t = testVal && testVal2 && true && false && true;
        r = 1 + 2 * 3 - 1000 - 67;
        while(testVal){
            r = 1 + 2 * 3 - 1000 - 67;
            testVal = false;
        }
        y = x;
        z = 'a';
        y = z;
        z = y;
        return z;
    }

}

