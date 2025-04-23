package org.exo.student.service;

import org.exo.student.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final List<Student> students = new ArrayList<>(Arrays.asList(
            Student.builder()
                    .id(1)
                    .firstName("Axel")
                    .lastName("Demeyere")
                    .age(28)
                    .email("axel.demeyere@gmail.com")
                    .build(),
            Student.builder()
                    .id(2)
                    .firstName("Morgan")
                    .lastName("Boutrois")
                    .age(27)
                    .email("morgan.boutrois@gmail.com")
                    .build(),
            Student.builder()
                    .id(3)
                    .firstName("Manon")
                    .lastName("Browaeys")
                    .age(29)
                    .email("manon.browaeys@gmail.com")
                    .build()
    ));

    private int nextId = 4;

    public Student getOneStudent(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public void saveStudent(Student student) {
        student.setId(nextId++);
        students.add(student);
    }

    public void updateStudent(int id, Student student) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == id) {
                students.set(i, student);
                break;
            }
        }
    }

    public void deleteStudent(int id) {
        students.removeIf(student -> student.getId() == id);
    }

    public List<Student> searchStudent(String search) {
        return getAllStudents().stream()
                .filter(student ->
                        student.getFirstName().toLowerCase().contains(search.toLowerCase()) ||
                                student.getLastName().toLowerCase().contains(search.toLowerCase()) ||
                                student.getEmail().toLowerCase().contains(search.toLowerCase())
                )
                .collect(Collectors.toList());
    }

}
