package lessons.core.lesson1;

public class Exam {
    private String name;                                          //Название экзаменационного предмета.
    private int complexity;                                       //Сложность экзаменна в баллах.

    public Exam(String name, int complexity){
        this.name = name;
        this.complexity = complexity;
    }

    public int getComplexity() {
        return complexity;
    }
}
