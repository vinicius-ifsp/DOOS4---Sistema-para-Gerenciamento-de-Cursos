package views.loaders;

import models.Course;
import models.Student;
import models.StudentStatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MockSingleton {
    private static MockSingleton mockSingleton;
    private List<Course> courses = new ArrayList<>();

    private MockSingleton() {
        this.setInitialData();
    }

    public static MockSingleton getInstance() {
        if (mockSingleton == null)
            mockSingleton = new MockSingleton();
        return mockSingleton;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public boolean hasCourse(int code) {
        for (Course course: courses)
            if (course.getCode() == code)
                return true;
        return false;
    }

    public Course getCourse(int code) {
        for (Course course: courses)
            if (course.getCode() == code)
                return course;
        return null;
    }

    public Iterator<Course> getCourses() {
        return courses.iterator();
    }

    public int generateCode() {
        return courses.size() + 1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("MockSingleton{\ncourses{\n");
        for(Course course: courses)
            builder.append(course+"\n");
        return builder.append("}}").toString();
    }

    private void setInitialData() {
        Course ads = new Course(1, 6, "Tecnologia em Analise e Desenvolvimento de Sistemas", "ADS-2018", 3000, null);

        Course bsi = new Course(2, 8, "Bacharelado em Sistema de Informação", "SI-2018", 8000, null);
        courses.add(ads);
        courses.add(bsi);
        Student vinicius = new Student(1, 4, 2018, "3002454",
                "Vinicius Luiz da Silva", null, StudentStatus.GREEN);
        Student gui = new Student(1, 4, 2018, "3002412",
                "Guilherme Sigoli", null, StudentStatus.YELLOW);
        Student peco = new Student(1, 4, 2018, "3001221",
                "Pedro Cabalero", null, StudentStatus.RED);

        ads.addStudent(peco);
        bsi.addStudent(gui);
        bsi.addStudent(vinicius);\
    }
}
