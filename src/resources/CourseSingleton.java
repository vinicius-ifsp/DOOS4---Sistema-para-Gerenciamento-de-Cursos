package resources;

import models.Course;

public class CourseSingleton {
    private static CourseSingleton courseSingleton;
    private Course course;

    private CourseSingleton() {}

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
