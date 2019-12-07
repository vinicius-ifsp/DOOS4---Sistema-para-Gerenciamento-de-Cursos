package dao;

import models.Course;
import models.Discipline;
import models.Student;
import models.StudentRemainingDiscipline;
import utils.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DisciplineDAO {
    private ConnectionFactory conn;

    public DisciplineDAO() {
        conn = new ConnectionFactory();
    }

    public List<Discipline> findByCourse(int code) {
        List<Discipline> disciplines = new ArrayList<>();

        String sql = "SELECT code, nome, workload, modulo FROM disciplina WHERE curso = ?";
        try (PreparedStatement stmt = conn.createStatement(sql)) {
            stmt.setInt(1, code);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                disciplines.add(
                        new Discipline(rs.getString("code"), rs.getString("nome"),
                                rs.getDouble("workload"), rs.getInt("modulo"))
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    public List<StudentRemainingDiscipline> findAllStudentRemainingDiscipline() {
        List<StudentRemainingDiscipline> studentRemainingDisciplines = new ArrayList<>();

        String sql = "SELECT * FROM disciplinaRestanteAluno";
        try (PreparedStatement stmt = conn.createStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                studentRemainingDisciplines.add(
                        new StudentRemainingDiscipline(
                                rs.getInt("atraso"),
                                new Discipline(rs.getString("disciplina")),
                                new Student(rs.getString("aluno"))
                        )
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentRemainingDisciplines;
    }
}
