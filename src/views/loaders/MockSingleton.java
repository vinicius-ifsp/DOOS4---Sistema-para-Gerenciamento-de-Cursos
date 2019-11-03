package views.loaders;

import models.Course;
import models.Discipline;

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

    @Override
    public String toString() {
        return "MockSingleton{" +
                "courses=" + courses +
                '}';
    }

    private void setInitialData() {
        Course ads = new Course(1, 6, "Tecnologo em Analise e Desenvolvimento de Sistemas", "ADS-2018", 3000, null);
        Discipline doo = new Discipline("DOOS4",  "Desenvolvimento Orientado a Objetos", 100);
        ads.addDiscipline(doo);
        Discipline poo = new Discipline("POOS3",  "Programação Orientado a Objetos", 100);
        ads.addDiscipline(poo);
        doo.addDependency(poo);

        Iterator<Discipline> dooDependencies = doo.getDependencies();
        System.out.println("DOO Dependencies:");
        while(dooDependencies.hasNext())
            System.out.println(dooDependencies.next());

        Course bsi = new Course(2, 8, "Bacharelado em Sistema de Informação", "SI-2018", 8000, null);
        bsi.addDiscipline(new Discipline("ICC1", "Introdução a Ciencia da Computação I", 120));
        bsi.addDiscipline(new Discipline("ICC2", "Introdução a Ciencia da Computação II", 130));

        courses.add(ads);
        courses.add(bsi);
    }

}
