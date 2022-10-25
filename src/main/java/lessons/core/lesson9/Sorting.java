package lessons.core.lesson9;

import java.util.*;
import java.util.stream.Collectors;

public class Sorting {

    private static List<Course> list = new ArrayList<>();

    public static void main(String[] args){

        list.add(new Course("Основы программирования"));
        list.add(new Course("Java. Уровень 1"));
        list.add(new Course("Python. Уровень 1"));
        list.add(new Course("C#. Уровень 1"));
        list.add(new Course("Unity для начинающих"));
        list.add(new Course("Java. Уровень 2"));
        list.add(new Course("Python. Уровень 2"));
        list.add(new Course("С#. Уровень 2"));
        list.add(new Course("HTML, CSS, JavaScript."));
        list.add(new Course("Unreal Engine. Основы"));

        List<Students> listOfStudents = new ArrayList<>();
        listOfStudents.add(new Students("Анна", choiceStudent()));
        listOfStudents.add(new Students("Максим", choiceStudent()));
        listOfStudents.add(new Students("Антон", choiceStudent()));
        listOfStudents.add(new Students("Ирина", choiceStudent()));
        listOfStudents.add(new Students("Екатерина", choiceStudent()));
        listOfStudents.add(new Students("Александр", choiceStudent()));
        listOfStudents.add(new Students("Константин", choiceStudent()));

        //Задание 1.
        List<Course> choiceOfStudents = listOfStudents.stream()
                .flatMap(students -> students.getAllCourses().stream())
                .distinct()
                .collect(Collectors.toList());

        //Задание 2.
        List<Students> curiousStudents = listOfStudents.stream()
                .sorted((student1, student2) -> student2.getAllCourses().size() - student1.getAllCourses().size())
                .limit(3)
                .collect(Collectors.toList());

        //Задание 3.
        Random rm = new Random();
        String nameCourse = list.get(rm.nextInt(list.size())).getName();
        List<Students> students = listOfStudents.stream()
                .filter(student -> {
                    for(int i = 0; i < student.getAllCourses().size(); i++){
                        if (student.getAllCourses().get(i).getName().equals(nameCourse)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    public static List<Course> choiceStudent () {
        Set<Course> uniqueChoice = new HashSet<>();
        Set<Integer> amount = new HashSet<>();
        Random choiceRm = new Random();
        Random courseRm = new Random();
        for(int i = 0; i < choiceRm.nextInt(3) + 2; i++){
            amount.add(courseRm.nextInt(list.size()));
        }
        List<Integer> amountCourse = new ArrayList<>(amount);
        for(int i = 0; i < amountCourse.size(); i++){
            uniqueChoice.add(list.get(amountCourse.get(i)));
        }
        List choice = new ArrayList<>(uniqueChoice);
        return choice;
    }
}
