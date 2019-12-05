package resources;

import models.Course;
import utils.DataLoader;

import java.io.IOException;

public class CourseSingleton {
    private static CourseSingleton courseSingleton;
    private Course course;

    private CourseSingleton() {
        course = new Course();
        try {
            course.setDisciplines(DataLoader.loadDisciplines(null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static CourseSingleton getInstance() {
        if (courseSingleton == null)
            courseSingleton = new CourseSingleton();
        return courseSingleton;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
