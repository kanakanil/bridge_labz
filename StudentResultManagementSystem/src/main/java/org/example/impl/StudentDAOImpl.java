package org.example.dao.impl;

import org.example.dao.StudentDAO;
import org.example.model.Student;
import org.example.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public int addStudent(Student student) throws Exception {
        String sql = "INSERT INTO students (name, course, email, phone) VALUES (?, ?, ?, ?)";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getCourse());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getPhone());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    @Override
    public boolean updateStudent(Student student) throws Exception {
        String sql = "UPDATE students SET name=?, course=?, email=?, phone=? WHERE studentId=?";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setString(2, student.getCourse());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getPhone());
            ps.setInt(5, student.getStudentId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("studentId"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getString("email"),
                        rs.getString("phone")
                ));
            }
        }
        return list;
    }
}

