package org.exo.student.service;

import org.exo.student.model.Student;
import org.exo.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


//    public Student getOneStudent(int id) {
//        return students.stream()
//                .filter(student -> student.getId() == id)
//                .findFirst()
//                .orElse(null);
//    }
//
//    public List<Student> getAllStudents() {
//        return students;
//    }
//
//    public void saveStudent(Student student) {
//        student.setId(nextId++);
//        students.add(student);
//    }
//
//    public void updateStudent(int id, Student student) {
//        for (int i = 0; i < students.size(); i++) {
//            if (students.get(i).getId() == id) {
//                students.set(i, student);
//                break;
//            }
//        }
//    }
//
//    public void deleteStudent(int id) {
//        students.removeIf(student -> student.getId() == id);
//    }
//
//    public List<Student> searchStudent(String search) {
//        return getAllStudents().stream()
//                .filter(student ->
//                        student.getFirstName().toLowerCase().contains(search.toLowerCase()) ||
//                                student.getLastName().toLowerCase().contains(search.toLowerCase()) ||
//                                student.getEmail().toLowerCase().contains(search.toLowerCase())
//                )
//                .collect(Collectors.toList());
//    }

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
