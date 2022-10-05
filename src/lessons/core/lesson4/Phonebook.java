package lessons.core.lesson4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Phonebook {

    public static void main(String[] args){
        HashMap<Integer, String> phonebook = new HashMap<>();
        add(phonebook, 1111111, "Петров");
        add(phonebook, 2222222, "Иванов");
        add(phonebook, 3333333, "Сидоров");
        add(phonebook, 4444444, "Петров");
        add(phonebook, 5555555, "Петров");
        add(phonebook, 6666666, "Антонов");
        add(phonebook, 7777777, "Иванов");
        add(phonebook, 8888888, "Сидоров");
        get(phonebook, "Петров");
        get(phonebook, "Иванов");
        get(phonebook, "Сидоров");
        get(phonebook, "Антонов");
    }

    public static void add(HashMap i, Integer phone, String surname){
        i.put(phone, surname);
    }

    public static void get(HashMap i, String surname){
        ArrayList<Integer> phone = new ArrayList<>();
        Set<Map.Entry<Integer, String>> entrySet = i.entrySet();
        for(Map.Entry<Integer, String> o: entrySet){
            if(surname.equals(o.getValue())){
                phone.add(o.getKey());
            }
        }
        System.out.println("По фамилии '" + surname + "' найдены данные номера телефонов: " + phone);
    }
}
