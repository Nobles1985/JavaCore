package lessons.core.lesson1;

public class MainApp {

    public static void main(String[] args){

        Student[] student = new Student[4];

        student[0] = new Student("Петр", 7, 9, 8);
        student[1] = new Student("Анна", 8, 6, 9);
        student[2] = new Student("Станислав", 5, 10, 8);
        student[3] = new Student("Марта", 9, 4, 4);

        Exam[] exam = new Exam[3];

        exam[0] = new Exam("Физика", 7);
        exam[1] = new Exam("История", 6);
        exam[2] = new Exam("Биология", 8);

        Team session = new Team("Группа 154", student);
        Course first = new Course("Первый курс", exam);

        first.doIt(session);
        first.sessionResult(session);
        session.showStudentResults();
    }
}
