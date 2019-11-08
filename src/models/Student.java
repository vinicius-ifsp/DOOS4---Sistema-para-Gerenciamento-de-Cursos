package models;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private int semIngresso, semAtual, anoIngresso;
    private String prontuario, nome;

    private Course course;
    private List<Commentary> comments = new ArrayList<>();

    public Student() {
    }

    public Student(int semIngresso, int semAtual, int anoIngresso, String prontuario, String nome, Course course) {
        this.semIngresso = semIngresso;
        this.semAtual = semAtual;
        this.anoIngresso = anoIngresso;
        this.prontuario = prontuario;
        this.nome = nome;
        this.course = course;
    }

    public int getSemIngresso() {
        return semIngresso;
    }

    public void setSemIngresso(int semIngresso) {
        this.semIngresso = semIngresso;
    }

    public int getSemAtual() {
        return semAtual;
    }

    public void setSemAtual(int semAtual) {
        this.semAtual = semAtual;
    }

    public int getAnoIngresso() {
        return anoIngresso;
    }

    public void setAnoIngresso(int anoIngresso) {
        this.anoIngresso = anoIngresso;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void addCommentary(Commentary commentary) {
        commentary.setStudent(this);
        comments.add(commentary);
    }

    public String getPpc() {
        return course == null ? "" : course.getPpc();
    }

    @Override
    public String toString() {
        return "Student{" +
                "semIngresso=" + semIngresso +
                ", semAtual=" + semAtual +
                ", anoIngresso=" + anoIngresso +
                ", prontuario='" + prontuario + '\'' +
                ", nome='" + nome + '\'' +
                ", course=" + (course != null ? course.getName() : null) +
                '}';
    }
}
