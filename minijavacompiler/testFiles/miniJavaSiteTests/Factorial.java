
class Factorial{
    public static void main(String[] a){
	System.out.println(new Fac().ComputeFac(10));
    }
}

class Fac {

    public int ComputeFac(int num){
		int num_aux ;
		int[] test;
		test = new int[new int[num_aux].length];
		if (num<5+6 < 1+7+1+test.length+9&&num < 1&&num.length < 1)
		    num_aux = 1;
		else
		    num_aux = num * (this.ComputeFac(num-1)) ;
		return num_aux;
    }

}
