class Main{
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class class1 extends class2{
    class4 x;
    class1 y;
    public int test(){
        x = y;
        return 0;
    }
}
class class2 extends class3{}

class class3 extends class4{}

class class4{
    class5 y;
}

class class5{}