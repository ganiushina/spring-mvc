package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.model.Student;
import com.geekbrains.spring.mvc.repositories.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsService {
    private StudentsRepository studentsRepository;

    @Autowired
    public void setStudentsRepository(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<Student> getAllStudents() {
        return studentsRepository.findAll();
    }

    public Student saveOrUpdateStudent(Student student) {
        return studentsRepository.saveOrUpdateStudent(student);
    }

    public Student findById(Long id) {
        return studentsRepository.findById(id);
    }
}
