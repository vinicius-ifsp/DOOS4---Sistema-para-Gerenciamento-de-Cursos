package models;

import java.util.*;

public class Student {
    private int semIngresso, semAtual, anoIngresso;
    private String prontuario, nome;

    private Course course;
    private List<Commentary> comments = new ArrayList<>();
    private List<Discipline> disciplines = new ArrayList<>();
    private List<Discipline> disciplinesRemaining = new ArrayList<>();

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

    public Student(int semIngresso, int anoIngresso, String prontuario, String nome, Course course) {
        this.semIngresso = semIngresso;
        // this.semAtual = semAtual;
        this.anoIngresso = anoIngresso;
        this.prontuario = prontuario;
        this.nome = nome;
        this.course = course;
    }

    public void addCommentary(Commentary commentary) {
        commentary.setStudent(this);
        comments.add(commentary);
    }

    public void addDiscipline(Discipline discipline) {

    }

    public String getPpc() {
        return course == null ? "" : course.getPpc();
    }


    public void calculateTimeToConclusion() {
//        Iterator<Map.Entry<String, Discipline>> courseDisciplines = course.getDisciplines();
//        while (courseDisciplines.hasNext()) {
//            Map.Entry<String, Discipline> disciplineEntry = courseDisciplines.next();
//            if (!disciplines.contains(disciplineEntry))
//                remaining.add(disciplineEntry.getValue());
//        }

        List<Discipline> remaining = new ArrayList<>(disciplinesRemaining);
        Collections.sort(remaining, Comparator.comparingInt(Discipline::getModule));

        int qtyRemainingSemester = 0;
        int currentModule = -1;
        int lastTimeConclusion = 0;
        int newTimeConclusion;

        for (Discipline discipline : remaining) {
            if (discipline.getModule() < semAtual) {
                if (discipline.getModule() != currentModule) {
                    lastTimeConclusion = discipline.getTimeConclusionDependenciesInSemesters();
                    qtyRemainingSemester += lastTimeConclusion + 1;
                    currentModule = discipline.getModule();
                } else if ((newTimeConclusion = discipline.getTimeConclusionDependenciesInSemesters()) > lastTimeConclusion) {
                    qtyRemainingSemester += newTimeConclusion - lastTimeConclusion;
                    lastTimeConclusion = newTimeConclusion;
                }
            }
        }

        if (qtyRemainingSemester == 0) {
            qtyRemainingSemester = course.getPeriodQty() - semAtual;
        } else {
            qtyRemainingSemester += course.getPeriodQty() - semAtual;
        }

        System.out.println(qtyRemainingSemester);

    }

    public Student(String prontuario) {
        this.prontuario = prontuario;
    }

    public void addDisciplineRemaining(Discipline d) {
        d.addStudent(this);
        disciplinesRemaining.add(d);
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
