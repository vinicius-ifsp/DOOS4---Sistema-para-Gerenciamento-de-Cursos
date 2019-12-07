package models;

import resources.StudentStatus;

import java.time.LocalDateTime;
import java.util.*;

public class Student {
    private int semIngresso, semAtual, anoIngresso, timeToConclusion;
    private String prontuario, nome;

    private Course course;
    private StudentStatus status;
    private HashMap<String, StudentRemainingDiscipline> remainingDisciplines = new HashMap<>();
    private List<Commentary> comments = new ArrayList<>();

    public void addCommentary(Commentary commentary) {
        commentary.setStudent(this);
        comments.add(commentary);
    }

    public int getTimeToConclusion() {
        return timeToConclusion;
    }

    public void calculateTimeToConclusion() {
        List<StudentRemainingDiscipline> remaining = new ArrayList<>(remainingDisciplines.values());
        Collections.sort(remaining, Comparator.comparingInt(StudentRemainingDiscipline::getDisciplineModule));

        int qtyRemainingSemester = course.getPeriodQty() - semAtual + 1;
        int currentModule = -1;
        int lastTimeConclusion = 0;
        int newTimeConclusion;

        for (StudentRemainingDiscipline discipline : remaining) {
            if (discipline.getAtraso() > 0) {
                if (discipline.getDisciplineModule() != currentModule) {
                    lastTimeConclusion = calculateTimeConclusionDependencies(discipline.getDiscipline().getDependencies());
                    qtyRemainingSemester += lastTimeConclusion + 1;
                    currentModule = discipline.getDisciplineModule();
                } else if ((newTimeConclusion = calculateTimeConclusionDependencies(discipline.getDiscipline().getDependencies())) > lastTimeConclusion) {
                    qtyRemainingSemester += newTimeConclusion - lastTimeConclusion;
                    lastTimeConclusion = newTimeConclusion;
                }
            }
        }


        if (qtyRemainingSemester == (course.getPeriodQty() - semAtual + 1)) {
            status = StudentStatus.VERDE;
        } else if (qtyRemainingSemester > ((2 * course.getPeriodQty() - 2) / 2 )) {
            status = StudentStatus.VERMELHO;
        } else {
            status = StudentStatus.AMARELO;
        }

        timeToConclusion = qtyRemainingSemester;
    }

    private int calculateTimeConclusionDependencies(Iterator<Discipline> dependencies) {
        int sum = 0;
        while (dependencies.hasNext()){
            Discipline dependency = dependencies.next();
            if (remainingDisciplines.containsKey(dependency.getCode())) {
                if (dependency.hasDependency()) {
                    sum += calculateTimeConclusionDependencies(dependency.getDependencies());
                } else {
                    sum +=  1;
                }
            }
        }
        return sum;
    }

    public Student(String prontuario) {
        this.prontuario = prontuario;
    }

    public Student(int semIngresso, int anoIngresso, String prontuario, String nome, Course course) {
        this.semIngresso = semIngresso;
        this.anoIngresso = anoIngresso;
        this.prontuario = prontuario;
        this.nome = nome;
        this.course = course;
        this.semAtual = getCurrentSemester();
    }

    public Student(int semIngresso, int semAtual, int anoIngresso, String prontuario, String nome) {
        this.semIngresso = semIngresso;
        this.semAtual = semAtual;
        this.anoIngresso = anoIngresso;
        this.prontuario = prontuario;
        this.nome = nome;
    }

    public void addRemainingDiscipline(StudentRemainingDiscipline discipline) {
        if (!remainingDisciplines.containsKey(discipline.getDisciplineCode())) {
            discipline.setStudent(this);
            remainingDisciplines.put(discipline.getDisciplineCode(), discipline);
        }
    }

    public boolean hasRemainingDiscipline(String code) {
        return remainingDisciplines.containsKey(code);
    }

    public int getCurrentSemester() {
        int sem = LocalDateTime.now().getMonthValue() >= 7 ? 2 : 1;
        return (LocalDateTime.now().getYear() - anoIngresso) * 2 + (sem - semIngresso) + 1;
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

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
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
