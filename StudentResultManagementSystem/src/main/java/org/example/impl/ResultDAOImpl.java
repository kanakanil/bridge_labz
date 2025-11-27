package org.example.dao.impl;

import org.example.dao.ResultDAO;
import org.example.model.Result;
import org.example.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ResultDAOImpl implements ResultDAO {

    @Override
    public int addResult(Result result) throws Exception {
        String sql = "INSERT INTO results (studentId, marks, grade) VALUES (?, ?, ?)";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, result.getStudentId());
            ps.setInt(2, result.getMarks());
            ps.setString(3, result.getGrade());

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
    public boolean updateResult(Result result) throws Exception {
        String sql = "UPDATE results SET marks=?, grade=? WHERE studentId=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, result.getMarks());
            ps.setString(2, result.getGrade());
            ps.setInt(3, result.getStudentId());

            return ps.executeUpdate() > 0;
        }
    }

    @Override
    public List<Result> getAllResults() throws Exception {
        List<Result> list = new ArrayList<>();
        String sql = "SELECT * FROM results";

        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Result(
                        rs.getInt("resultId"),
                        rs.getInt("studentId"),
                        rs.getInt("marks"),
                        rs.getString("grade")
                ));
            }
        }
        return list;
    }

    @Override
    public Result getByStudentId(int studentId) throws Exception {
        String sql = "SELECT * FROM results WHERE studentId=?";
        try (Connection conn = JDBCUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Result(
                            rs.getInt("resultId"),
                            rs.getInt("studentId"),
                            rs.getInt("marks"),
                            rs.getString("grade")
                    );
                }
            }
        }
        return null;
    }
}

