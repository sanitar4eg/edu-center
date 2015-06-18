package edu.netcracker.repository;

import edu.netcracker.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {

    List<Student> findByTypeIgnoreCase(String type);
}