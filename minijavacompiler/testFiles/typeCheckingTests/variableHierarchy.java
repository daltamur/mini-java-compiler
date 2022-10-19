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
        return 0;
    }

}