package edu.netcracker.service.impl

import edu.netcracker.model.Student
import edu.netcracker.repository.StudentJpaRepository
import edu.netcracker.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StudentServiceImpl implements StudentService {

    @Autowired
    StudentJpaRepository repository;

    @Override
    public List<Student> findAll() {
        repository.findAll()
    }

    @Override
    public void saveAndFlush(Student student) {
        repository.saveAndFlush(student)
    }

    @Override
    public Student getOne(Long id) {
        repository.getOne(id)
    }

    @Override
    public void delete(Long id) {
        repository.delete(id)
    }
}
