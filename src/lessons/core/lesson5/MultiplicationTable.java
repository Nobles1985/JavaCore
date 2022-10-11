package lessons.core.lesson5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MultiplicationTable {

    public static ArrayList<ValueFile> file = new ArrayList<>();
    public static final String path = "src/lessons/core/lesson5/table.csv";
    public static final String info = "I" + ";" + "II" + ";" + "III" + ";" + "IV" + ";" + "V" + ";" +
            "VI" + ";" + "VII" + ";" + "VIII" + ";" + "IX" + ";\n";

    public static void main(String[] args) throws IOException {
        table();
        writeToFile();
        AppData data = readFromFile();
    }

    public static void table(){
        for(Integer i = 1; i < 10; i++){
            file.add(new ValueFile(i, i*2, i*3, i*4, i*5, i*6, i*7, i*8, i*9));
        }
    }

    public static void writeToFile() throws IOException {
        try(FileWriter writer = new FileWriter(path)){
            writer.write(info);
            for(ValueFile value : file){
                writer.write(value.getInfo1() + ";" + value.getInfo2() + ";" + value.getInfo3() + ";" +
                        value.getInfo4() + ";" + value.getInfo5() + ";" + value.getInfo6() + ";" + value.getInfo7() +
                        ";" + value.getInfo8() + ";" + value.getInfo9() + ";\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static AppData readFromFile() throws IOException {
        AppData data = new AppData();
        List<List<String>> array = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(path))){
            String str = reader.readLine();
            data.setHeader(str.split(";"));
            while((str = reader.readLine()) != null){
                String[] line = str.split(";");
                array.add(Arrays.asList(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[][] arr = new int[array.size()][9];
        for(int i = 0; i < array.size(); i++){
            for(int j = 0; j < array.get(i).size(); j++){
                arr[i][j] = Integer.valueOf(array.get(i).get(j));
            }
        }
        data.setData(arr);
        return data;
    }
}
