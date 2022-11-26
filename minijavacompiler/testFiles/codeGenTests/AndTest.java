class AndTest{
    public static void main(String[] args) {
        {
            if(5<10 && 100<120 && 10< 20){
                System.out.println(1);
            }else{
                System.out.println(0);
            }

            if(5<10 && 100<120 && 20< 20){
                System.out.println(1);
            }else{
                System.out.println(0);
            }

        }
    }
}