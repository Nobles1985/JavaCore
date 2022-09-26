package lessons.core.lesson1;

public class Course {
    private String name;                                                   //Название курса.
    private Exam exam[];                                                   //Экзамены на курсе.

    public Course(String name, Exam exam[]){
        this.name = name;
        this.exam = exam;
    }

    public void doIt(Team team){                                            //Проверка на прохождение сессии студентами.
        for(Student student: team.getStudent()){
            if(exam[0].getComplexity() <= student.getKnowFirstSubject() &&
               exam[1].getComplexity() <= student.getKnowSecondSubject() &&
               exam[2].getComplexity() <= student.getKnowThirdSubject()){
                student.setPassed(true);
            }
        }
    }

    public void sessionResult(Team team){                                   //Информация о студентах, успешно сдавших сессию.
        System.out.println(team.getName() + ", " + name + ", " + "cписок студентов удачно сдавших сессию:");
        for(Student student: team.getStudent()){
            if(student.getPassed())
                System.out.println(student.getName());
        }
        System.out.println();
    }
}
