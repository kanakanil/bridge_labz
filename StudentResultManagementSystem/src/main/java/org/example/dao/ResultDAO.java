package org.example.dao;

import org.example.model.Result;
import java.util.List;

public interface ResultDAO {
    int addResult(Result result) throws Exception;
    boolean updateResult(Result result) throws Exception;
    List<Result> getAllResults() throws Exception;
    Result getByStudentId(int studentId) throws Exception;
}

