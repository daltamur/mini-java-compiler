class MatrixScreen{
    public static void main(String[] args) {
        System.out.println(new TS().Start(100, 100));
    }
}

class TS{

    public int Start(int length, int width){
        boolean onEvenRow;
        boolean curColIsEven;
        int curRow;
        int curCol;
        onEvenRow = true;
        curColIsEven = true;
        curRow = 0;
        curCol = 0;
        while(curRow < length){
            while(curCol < width){
                if(curColIsEven && onEvenRow){
                    System.out.println('1');
                }else{
                    System.out.println('0');
                }
                curCol = curCol + 1;
                if(curColIsEven){
                    curColIsEven = false;
                }else{
                    curColIsEven = true;
                }
            }

            curCol = 0;
            curColIsEven = true;
            if(onEvenRow){
                onEvenRow = false;
            }else{
                onEvenRow = true;
            }
            curRow = curRow + 1;
        }
        return 0;
    }


}