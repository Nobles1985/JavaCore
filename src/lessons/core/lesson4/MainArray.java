package lessons.core.lesson4;

import java.util.*;

public class MainArray {

    public static void main(String[] args){
        ArrayList<String> arrayString = new ArrayList<>
                (Arrays.asList("Лето","Осень","Зима","Весна","Лето","День","Ночь","Осень","Лето","День","Туман"));
        HashSet<String> set = new HashSet<>(arrayString);
        System.out.println(set);
        HashMap<String, Integer> mapArray = new HashMap<>();
        for(String a: arrayString){
            mapArray.merge(a, 1, Integer::sum);
        }
        System.out.println();
        for(Map.Entry<String, Integer> entry: mapArray.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
