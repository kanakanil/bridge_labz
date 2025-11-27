package org.example.dao;

import org.example.model.Student;
import java.util.List;

public interface StudentDAO {
    int addStudent(Student student) throws Exception;
    boolean updateStudent(Student student) throws Exception;
    List<Student> getAllStudents() throws Exception;
}

