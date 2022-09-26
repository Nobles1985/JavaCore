package lessons.core.lesson1;

public class Team {
    private String name;                               //Название учебной группы.
    private Student student[];                         //Список студентов.

    public Team (String name, Student student[]) {
        this.name = name;
        this.student = student;
    }

    public String getName() { return name; }

    public Student[] getStudent() {
        return student;
    }

    public void showStudentResults(){                          // Вывод информации по всем студентам.
        for(int i = 0; i < student.length; i++){
            System.out.println(student[i].info());
        }
    }
}
