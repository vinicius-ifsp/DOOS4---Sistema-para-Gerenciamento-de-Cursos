package models;

import java.util.*;


public class Course {
    private int code, periodQty;
    private String name, ppc;
    private double workload;

    private HashMap<String, Student> students = new HashMap<>();
    private HashMap<String, Discipline> disciplines = new HashMap<>();

    public Course() {
    }

    public Course(int code, int periodQty, String name, String ppc, double workload, HashMap<String, Discipline> disciplines) {
        this.code = code;
        this.periodQty = periodQty;
        this.name = name;
        this.ppc = ppc;
        this.workload = workload;
        if (disciplines != null)
            this.disciplines = disciplines;
    }

    public void addDiscipline(Discipline discipline) {
        discipline.setCourse(this);
        disciplines.put(discipline.getCode(), discipline);
    }

    public boolean hasDiscipline(String code) {
        return disciplines.containsKey(code);
    }

    public Iterator<Map.Entry<String, Discipline>> getDisciplines() {
        return disciplines.entrySet().iterator();
    }

    public void addStudent(Student student) {
        if (!students.containsKey(student.getProntuario())) {
            student.setCourse(this);
            students.put(student.getProntuario(), student);
        }
    }

    public void addRemainingDisciplines(List<StudentRemainingDiscipline> studentRemainingDisciplineList) {
        for (StudentRemainingDiscipline discipline : studentRemainingDisciplineList) {
            if (disciplines.get(discipline.getDisciplineCode()) != null) {
                Student s = students.get(discipline.getStudentProntuario());
                if (s.getProntuario().equals("3002412")) {
                    System.out.println();
                }
                s.addRemainingDiscipline(
                        new StudentRemainingDiscipline(disciplines.get(discipline.getDisciplineCode()), null));
            }
        }
    }

    public boolean hasStudent(String prontuario) {
        return students.containsKey(prontuario);
    }

    public Iterator<Map.Entry<String, Student>> getStudents() {
        return students.entrySet().iterator();
    }

    public Discipline getDiscipline(String code) {
        return disciplines.get(code);
    }

    public Student getStudent(String code) {
        return students.get(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPeriodQty() {
        return periodQty;
    }

    public void setPeriodQty(int periodQty) {
        this.periodQty = periodQty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPpc() {
        return ppc;
    }

    public void setPpc(String ppc) {
        this.ppc = ppc;
    }

    public double getWorkload() {
        return workload;
    }

    public void setWorkload(double workload) {
        this.workload = workload;
    }

    public void setDisciplines(HashMap<String, Discipline> disciplines) {
        for (Map.Entry<String, Discipline> disciplineEntry : disciplines.entrySet())
            disciplineEntry.getValue().setCourse(this);
        this.disciplines = disciplines;
    }

    public void addStudents(List<Student> studentList) {
        for (Student student : studentList)
            addStudent(student);
    }

    @Override
    public String toString() {
        return "Course{" +
                "code=" + code +
                ", periodQty=" + periodQty +
                ", name='" + name + '\'' +
                ", ppc='" + ppc + '\'' +
                ", workload=" + workload +
                ", students=" + students +
                ", disciplines=" + disciplines +
                '}';
    }
}
