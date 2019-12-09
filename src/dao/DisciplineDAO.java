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
import java.util.*;

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

    public Map<String, Double> findDisciplinesWithMostReproof(){
        Map<String, Double> mostReproofDisciplines = new HashMap<>();

        String sql = "SELECT disciplina, count(*) count FROM disciplinaRestanteAluno " +
                "group by disciplina order by count desc";
        try (PreparedStatement stmt = conn.createStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            int count = 0;

            while (rs.next() && count < 2){
                count++;
                mostReproofDisciplines.put(rs.getString("disciplina"), rs.getDouble("count"));}
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mostReproofDisciplines;
    }

    public boolean save(Iterator<Map.Entry<String, Discipline>> disciplines) {
        Course course = CourseSingleton.getInstance().getCourse();
        String sql = "INSERT INTO disciplina(code, nome, workload, modulo, curso) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.createStatement(sql)) {
            while (disciplines.hasNext()) {
                Discipline d = disciplines.next().getValue();
                if (!course.hasDiscipline(d.getCode())) {
                    stmt.setString(1, d.getCode());
                    stmt.setString(2, d.getName());
                    stmt.setDouble(3, d.getWorkload());
                    stmt.setInt(4, d.getModule());
                    stmt.setInt(5, course.getCode());
                    stmt.addBatch();
                }
            }
            stmt.executeBatch();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
