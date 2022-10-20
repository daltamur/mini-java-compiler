class Main{
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class class1 extends class2{
    public int methodTest(class1 x, class2 y, class3 z, class4 a){
        return this.methodTest(x, y, x, z);
    }
}

class class2 extends class3{}

class class3 extends class4{}

class class4{}