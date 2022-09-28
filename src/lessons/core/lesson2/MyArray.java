package lessons.core.lesson2;

public class MyArray {

    static String[][] arrayFirst = {{"74", "44", "-12", "4"}, {"5", "28", "77", "15"}, {"5", "3", "11", "8"}, {"44", "-2", "7", "8"}};
    static String[][] arraySecond = {{"6", "2", "22", "24"}, {"7", "6", "13", "-14"}};
    static String[][] arrayThird = {{"66", "-12", "55", "21"}, {"23", "-50", "12", "Sale"}, {"11", "-34", "3", "18"}, {"6", "8", "15", "-2"}};
    static String[][][] allArray = {arrayFirst, arraySecond, arrayThird};
    static int lengthA = 4;
    static int lengthB = 4;

    public static void main(String[] args) {
        checkArray(allArray);
    }

    //Метод проверяет все массивы.
    public static void checkArray(String[][][] arr){
        for(int i = 0; i < arr.length; i++){
            try {
                System.out.println("Сумма элементов массива: " + sumArray(arr[i]));
            } catch (MyArraySizeException | MyArrayDataException e){
                System.out.println(e.getMessage());
            }
        }
    }

    //Проверка размера массива.
    public static void size(String[][] arr) throws MyArraySizeException{
        if(arr.length!=lengthA || arr[0].length!=lengthB) throw new MyArraySizeException();
    }

    //Метод, вычисляющий сумму элементов массива.
    public static int sumArray(String[][] arr) throws MyArrayDataException, MyArraySizeException{
        size(arr);
        int sum = 0;
        int i = 0;
        int j = 0;
        try {
            for (; i < arr.length; i++){
                for (j = 0; j < arr.length; j++){
                    sum = sum + Integer.parseInt(arr[i][j]);
                }
            }
        } catch (NumberFormatException e){
            throw new  MyArrayDataException("В позиции: " + i + ", " + j + " неверные данные!");
        }
        return sum;
    }

    public static class MyArraySizeException extends Exception{
        public MyArraySizeException(){
            super("Массив неверного размера");
        }
    }

    public static class MyArrayDataException extends Exception{
        public MyArrayDataException(String message){
            super(message);
        }
    }
}
