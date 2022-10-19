class Main{
    public static void main(String[] a) {
        System.out.println(new doWork().methodRun(new grandDaughterClass()));
    }
}

class baseClass{}

class daughterClass extends baseClass{}

class grandDaughterClass extends daughterClass{}

class doWork{

    public int methodRun(baseClass x){
        int[] a;
        int w;
        a = new int[new int[new int[5*6-12+18-(5-65*54-new int[45].length)].length].length];
        return a[1];
    }

}