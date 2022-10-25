package lessons.core.lesson9;

import lessons.core.lesson9.interfaces.Student;

import java.util.ArrayList;
import java.util.List;

public class Students implements Student {

    private String name;
    private List<Course> courses = new ArrayList<>();

    public Students(String name, List<Course> courses){
        this.name = name;
        this.courses = courses;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Course> getAllCourses() {
        return courses;
    }
}
