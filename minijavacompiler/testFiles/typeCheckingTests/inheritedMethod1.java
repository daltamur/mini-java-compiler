class Main{
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class class1{
    public int methodTest(int a, class2 y){
        int x;
        x = this.methodTest(x, y);
        return this.methodTest(x, y);
    }
}

class class2 extends class3{}

class class3 extends class4{}

class class4{}