package org.exo.student.service;

import org.exo.student.dao.StudentRepository;
import org.exo.student.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getOneStudent(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public List<Student> searchStudent(String search) {
        return studentRepository.findAll().stream()
                .filter(student ->
                        student.getFirstName().toLowerCase().contains(search.toLowerCase()) ||
                                student.getLastName().toLowerCase().contains(search.toLowerCase()) ||
                                student.getEmail().toLowerCase().contains(search.toLowerCase())
                )
                .collect(Collectors.toList());
    }
}
