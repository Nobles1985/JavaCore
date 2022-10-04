package lessons.core.lesson3.array;

import java.util.Arrays;
import java.util.Scanner;

public class AppMain {

    public static void main(String[] args){
        String[] array1 = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Integer[] array2 = {4,8,12,16,77,33,80,4};
        replace(array1);
        System.out.println(Arrays.toString(array1));
        replace(array2);
        System.out.println(Arrays.toString(array2));
    }

    public static Object replace(Object[] arr){
        Scanner sc = new Scanner(System.in);
        int a, b;
        Object buffer;
        System.out.println("Массив: " + Arrays.toString(arr));
        do{
            System.out.println("Введите номера элементов массива, в пределах от 1 до " + arr.length + ", которые необходимо поменять местами:");
            a = sc.nextInt();
            b = sc.nextInt();
            if(a < 1 || b < 1 || a > arr.length || b > arr.length)
                System.out.println("Введен неверный номер элемента!");
            else break;
        }
        while (true);
        buffer = arr[a - 1];
        arr[a - 1] = arr[b - 1];
        arr[b - 1] = buffer;
        return arr;
    }
}
