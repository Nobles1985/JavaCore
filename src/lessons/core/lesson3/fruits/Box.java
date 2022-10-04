package lessons.core.lesson3.fruits;

public class Box<T extends Fruit> {
    T[] box;

    public Box(T[] box){
        this.box = box;
    }

    public T[] getBox() {
        return box;
    }

//Метод определения веса коробки.
    public Float getBoxWeight(){
        Float boxWeight = 0.0f;
        for(T i: getBox()){
            if(i != null){
                Float weight = i.getWeight();
                boxWeight = boxWeight + weight;
            }
        }
        return boxWeight;
    }

//Метод сравнения коробок по весу.
    public Boolean compare(Box<?> box){
        if(this.getBoxWeight().intValue() == box.getBoxWeight().intValue()){
            return true;
        } else
            return false;
    }

//Метод для подсчета количество фруктов в коробке.
    private int amountFruit(){
        int amount = 0;
        for(T i: getBox()){
            if(i != null) amount++;
        }
        return amount;
    }

//Метод для пересыпания фруктов из одной коробки в другую.
    public void emptyFruit(Box<T> box){
        System.out.println("В текущей коробке " + box.amountFruit() + " фруктов\nВ новой коробке " + this.amountFruit() + " фруктов");
        for(int i = 0; i < box.getBox().length; i++){
            if(box.getBox()[i] != null){
                this.getBox()[amountFruit()] = box.getBox()[i];
                box.getBox()[i] = null;
            }
        }
        System.out.println("После пересыпания фруктов в новую коробку:");
        System.out.println("В текущей коробке " + box.amountFruit() + " фруктов\nВ новой коробке " + this.amountFruit() + " фруктов");
    }

//Методы добавления фруктов в пустую коробку.
    public static void inToBox(Apple[] a, int b){
        for(int i = 0; i < b; i++) a[i] = new Apple();
    }

    public static void inToBox(Orange[] a, int b){
        for(int i = 0; i < b; i++) a[i] = new Orange();
    }
}
