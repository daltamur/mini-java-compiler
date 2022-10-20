class Main{
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class class1 extends class2{
    public int methodTest(int a, class2 y){
        int x;
        x = this.methodTest('a', y);
        return this.methodTestInherited(x, y);
    }
}

class class2 extends class3{}

class class3 extends class4{}

class class4{
    public int methodTestInherited(int a, class4 y){
        int x;
        return this.methodTestInherited(x, y);
    }
}