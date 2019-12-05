package models;

import java.util.*;

public class Discipline {
    private String code;
    private String name;
    private double workload;
    private int module;
    private Course course;
    private List<Discipline> dependencies = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public Discipline() {
    }

    public Discipline(String code, String name, int module) {
        this.code = code;
        this.name = name;
        this.module = module;
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

    public void addStudent(Student student) {
        if (!students.contains(student))
            students.add(student);
    }



    public int getTimeConclusionDependenciesInSemesters() {
        int sum = 0;
        for (Discipline dependency : dependencies) {
            if (dependency.hasDependency()) {
                sum += dependency.getTimeConclusionDependenciesInSemesters();
            } else {
                return 1;
            }
        }
        return sum;
    }

    public boolean hasDependency() {
        return dependencies.size() > 0 ? true : false;
    }

    public int getModule() {
        return module;
    }

    public void setModule(int module) {
        this.module = module;
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
