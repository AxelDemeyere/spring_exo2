package org.exo.student.controller;

import jakarta.validation.Valid;
import org.exo.student.model.Student;
import org.exo.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/")
    public String home() {
        return "pages/home";
    }


    @GetMapping("/students")
    public String students(@RequestParam(value = "search", required = false) String search, Model model) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("students", studentService.searchStudent(search));
        } else {
            model.addAttribute("students", studentService.getAllStudents());
        }
        return "pages/students";
    }

    @GetMapping("/students/register")
    public String register(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "pages/form";
    }

    @PostMapping(value = "/students/register/")
    public String createStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "pages/form";
        } else {
            if (student.getId() != 0) {
                studentService.updateStudent(student.getId(), student);
            } else {
                studentService.saveStudent(student);
            }
        }
        return "redirect:/students";
    }

    @RequestMapping("/students/details/{id}")
    public String details(Model model, @PathVariable("id") int id) {
        model.addAttribute("student", studentService.getOneStudent(id));
        return "pages/details";
    }

    @PostMapping(value = "/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/students/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Student student = studentService.getOneStudent(id);
        model.addAttribute("student", student);
        return "pages/form";
    }

    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable("id") int id, @ModelAttribute Student student) {
        student.setId(id);
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }


}
