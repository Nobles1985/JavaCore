package lessons.core.lesson1;

public class Student {
    private String name;                                        //Имя студента.
    private int knowFirstSubject;                               //Уровень знаний в баллах по экзаменационным предметам.
    private int knowSecondSubject;
    private int knowThirdSubject;
    private boolean passed = false;                             //Сдал ли студент сессию.

    public Student (String name, int knowFirstSubject, int knowSecondSubject, int knowThirdSubject){
        this.name = name;
        this.knowFirstSubject = knowFirstSubject;
        this.knowSecondSubject = knowSecondSubject;
        this.knowThirdSubject = knowThirdSubject;
    }

    public String getName() { return name; }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public int getKnowFirstSubject() {
        return knowFirstSubject;
    }

    public int getKnowSecondSubject() {
        return knowSecondSubject;
    }

    public int getKnowThirdSubject() {
        return knowThirdSubject;
    }

    public boolean getPassed(){
        return passed;
    }

    public String info(){                                      //Общая информация по студентам
        if(passed){
            return name + " сдал(а) экзамены.";
        } else {
            return name + " не сдал(а) сессию.";
        }
    }
}
