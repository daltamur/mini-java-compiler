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
        int[] testArray;
        boolean testVal;
        boolean testVal2;
        testVal = true;
        testVal2 = false;
        testArray = new int[new int[4].length];
        t = testVal && testVal2 && true && false && true;
        r = 1 + 2 * 3 - 1000 - 67;
        //z = this.testMethod('a');
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

