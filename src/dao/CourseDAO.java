package dao;

import models.Course;
import utils.ConnectionFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private ConnectionFactory conn;

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



}
