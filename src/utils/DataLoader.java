package utils;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Course;
import models.Student;
import views.loaders.MockSingleton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataLoader {

    public static ObservableList<Student> loadStudents(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            ObservableList<Student> students = FXCollections.observableArrayList();;
            String strCurrentLine;

            Iterator<Course> courses = MockSingleton.getInstance().getCourses();
            while ((strCurrentLine = br.readLine()) != null) {
                System.out.println("String Current Line: " + strCurrentLine);
                String[] data = strCurrentLine.split(",");
                Course course = new Course();
                while(courses.hasNext()){
                    if(courses.next().getPpc().equals(data[5])){
                        course = courses.next();
                        break;
                    }
                }
                students.add(new Student(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]), data[3], data[4], course, null));
            }
            return students;
        } catch (IOException e) {
            throw (e);
        }
    }
}