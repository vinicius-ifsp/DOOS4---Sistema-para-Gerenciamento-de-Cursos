package dao;

import models.Course;
import models.Discipline;
import models.Student;
import models.StudentRemainingDiscipline;
import resources.CourseSingleton;
import utils.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StudentDAO {
    private ConnectionFactory conn;

    public StudentDAO() {
        conn = new ConnectionFactory();
    }

    public List<Student> findByCourse(int code) {
        List<Student> students = new ArrayList<>();

        String sql = "SELECT semIngresso, semAtual, anoIngresso, prontuario, nome FROM aluno WHERE curso = ?";
        try (PreparedStatement stmt = conn.createStatement(sql)) {
            stmt.setInt(1, code);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                students.add(
                        new Student(rs.getInt("semIngresso"), rs.getInt("semAtual"), rs.getInt("anoIngresso"),
                                rs.getString("prontuario"), rs.getString("nome"))
                );
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void saveStudentList(List<Student> students) {
        Course course = CourseSingleton.getInstance().getCourse();
        String sql = "INSERT INTO aluno(semIngresso, semAtual, anoIngresso, prontuario, nome, curso) " +
                "VALUES (?, ?, ?, ?, ?, ?);\n";
        try (PreparedStatement stmt = conn.createStatement(sql)) {
            for (Student student : students) {
                if (!course.hasStudent(student.getProntuario())) {

                    stmt.setInt(1, student.getSemIngresso());
                    stmt.setInt(2, student.getSemAtual());
                    stmt.setInt(3, student.getAnoIngresso());
                    stmt.setString(4, student.getProntuario());
                    stmt.setString(5, student.getNome());
                    stmt.setInt(6, course.getCode());
                    stmt.addBatch();
                }
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveStudentRemainingDisciplines(List<StudentRemainingDiscipline> studentRemainingDisciplines) {
        Course course = CourseSingleton.getInstance().getCourse();
        String sql = "INSERT INTO disciplinaRestanteAluno(disciplina, aluno, atraso) " +
                "VALUES (?, ?, ?);\n";
        try (PreparedStatement stmt = conn.createStatement(sql)) {
            for (StudentRemainingDiscipline student : studentRemainingDisciplines) {
                Student s = course.getStudent(student.getStudentProntuario());
                if (!s.hasRemainingDiscipline(student.getDisciplineCode())) {
                    stmt.setString(1, student.getDisciplineCode());
                    stmt.setString(2, student.getStudentProntuario());
                    stmt.setInt(3, student.getAtraso());
                    stmt.addBatch();
                }
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
