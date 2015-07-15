package edu.netcracker.service;

import edu.netcracker.model.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface StudentService {
    List<Student> findAll();

    void saveAndFlush(Student student);

    Student getOne(Long id);

    void delete(Long id);

    List<Student> findQA();

    List<Student> findDev();

    void handleImport(MultipartFile p);

    List<Student> getStudentsHistoryAfterDate(Date date);
}
