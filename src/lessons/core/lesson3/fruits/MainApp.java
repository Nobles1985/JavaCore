package lessons.core.lesson3.fruits;

public class MainApp {

    public static void main(String[] args){

        Apple[] apples1 = new Apple[40];
        Apple[] apples2 = new Apple[40];
        Box.inToBox(apples1, 20);
        Box.inToBox(apples2, 6);
        Box<Apple> appleBox1 = new Box<>(apples1);
        Box<Apple> appleBox2 = new Box<>(apples2);

        Orange[] oranges1 = new Orange[40];
        Orange[] oranges2 = new Orange[40];
        Box.inToBox(oranges1, 20);
        Box.inToBox(oranges2, 4);
        Box<Orange> orangeBox1 = new Box<>(oranges1);
        Box<Orange> orangeBox2 = new Box<>(oranges2);

        if(orangeBox1.compare(appleBox1)){
            System.out.println("У коробок одинаковый вес");
        } else {
            System.out.println("У коробок разный вес");
        }

        if(orangeBox2.compare(appleBox2)){
            System.out.println("У коробок одинаковый вес");
        } else {
            System.out.println("У коробок разный вес");
        }

        System.out.println("\nКоробки с яблоками");
        appleBox2.emptyFruit(appleBox1);
        System.out.println("\nКоробки с апельсинами");
        orangeBox2.emptyFruit(orangeBox1);
    }
}
