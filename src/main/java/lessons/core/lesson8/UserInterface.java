package lessons.core.lesson8;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {

    private final Controller controller = new Controller();
    public void runApplication() {
        Scanner sc = new Scanner(System.in);
        while(true){
            String choice = null;
            do{
            System.out.println("Введите: 1 - Получить погоду на 1 день, " +
                    "2 - Получить погоду на следующие 5 дней, " + "3 - получить данные из базы данных, " +
                    "выход (exit) - завершить работу");
            choice = sc.nextLine();
            checkIsExit(choice);
            if (choice.equals("3")){
                try {
                    notifyController(choice);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }} while (choice.equals("3"));
            System.out.println("Введите название города на русском языке");
            String city = sc.nextLine();
            setCity(city);
            try {
                validateUserInput(choice);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
            try {
                notifyController(choice);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCity(String city){
        AppGlobalState.getInstance().setSelectCity(city);
    }

    private void checkIsExit(String choice) {
        if (choice.toLowerCase().equals("выход") || choice.toLowerCase().equals("exit")) {
            System.out.println("Завершаю работу");
            System.exit(0);
        }
    }

    private void validateUserInput(String userInput) throws IOException{
        if (userInput == null || userInput.length() != 1) {
            throw new IOException("Некорректный ввод: ожидается одна цифра, а введено " + userInput);
        }
        int answer = 0;
        try {
            answer = Integer.parseInt(userInput);
        } catch (NumberFormatException e) {
            throw new IOException("Некорректный ввод: значение не является числом!");
        }
    }

    private void notifyController(String input) throws IOException{
        controller.onUserInput(input);
    }
}
