package edu.netcracker.service;

import edu.netcracker.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> findAll();

    void saveAndFlush(Student student);

    Student getOne(Long id);

    void delete(Long id);

    List<Student> findQA();

    List<Student> findDev();
}
