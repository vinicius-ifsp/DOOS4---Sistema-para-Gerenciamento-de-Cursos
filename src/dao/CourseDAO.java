package dao;

import models.Course;
import utils.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private ConnectionFactory conn;
    private DisciplineDAO disciplineDAO;

    public CourseDAO() {
        conn = new ConnectionFactory();
    }

    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT code, qtdPeriodos, nome, ppc, workload FROM curso";
        try (PreparedStatement stmt = conn.createStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                courses.add(
                        new Course(rs.getInt("code"), rs.getInt("qtdPeriodos"), rs.getString("nome"),
                                rs.getString("ppc"), rs.getDouble("workload"), null)
                );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public boolean save(Course course) {
        boolean result = false;
        int lastCode = -1;
        String sql = "INSERT INTO curso(nome, qtdPeriodos, ppc, workload) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.createStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, course.getName());
            stmt.setInt(2, course.getPeriodQty());
            stmt.setString(3, course.getPpc());
            stmt.setDouble(4, course.getWorkload());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                lastCode = rs.getInt(1);
                result = true;
            }
            rs.close();

            if (course.getQtyDisciplines() > 0) {
                course.setCode(lastCode);
                disciplineDAO = new DisciplineDAO();
                result = disciplineDAO.save(course.getDisciplines(), lastCode);
            }

        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
}
