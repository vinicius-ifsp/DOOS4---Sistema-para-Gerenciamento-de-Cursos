package models;

import java.util.*;

public class Discipline {
    private String code;
    private String name;
    private double workload;
    private Course course;
    private List<Discipline> dependencies = new ArrayList<>();

    public Discipline() {
    }

    public Discipline(String code, String name, double workload) {
        this.code = code;
        this.name = name;
        this.workload = workload;
    }

    public void addDependency(Discipline discipline) {
        if (!dependencies.contains(discipline))
            dependencies.add(discipline);
    }

    public Iterator<Discipline> getDependencies() {
        return dependencies.iterator();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWorkload() {
        return workload;
    }

    public void setWorkload(double workload) {
        this.workload = workload;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", workload=" + workload +
                '}';
    }
}
