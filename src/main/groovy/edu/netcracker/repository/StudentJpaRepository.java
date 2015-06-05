package edu.netcracker.repository;

import edu.netcracker.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentJpaRepository extends JpaRepository<Student, Long> {

}