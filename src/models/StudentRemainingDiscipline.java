package models;

public class StudentRemainingDiscipline {
    private int atraso;
    private Discipline discipline;
    private Student student;

    public StudentRemainingDiscipline(Discipline discipline, Student student) {
        this.discipline = discipline;
        this.student = student;
    }

    public StudentRemainingDiscipline(int atraso, Discipline discipline, Student student) {
        this.atraso = atraso;
        this.discipline = discipline;
        this.student = student;
    }

    public int getAtraso() {
        return student.getCurrentSemester() - discipline.getModule() >
                0 ? student.getCurrentSemester() - discipline.getModule() : 0;
    }

    public String getDisciplineCode() {
        return discipline.getCode();
    }

    public String getStudentProntuario() {
        return student.getProntuario();
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getDisciplineModule() {
        return discipline.getModule();
    }
}