package org.exo.student.controller;

import org.exo.student.model.Student;
import org.exo.student.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @RequestMapping("/students")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "pages/students";
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

    @RequestMapping("/students/register")
    public String register(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "pages/register";
    }

    @RequestMapping(value = "/students/register/create", method = RequestMethod.POST)
    public String createStudent(Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @RequestMapping("/students/details/{id}")
    public String details(Model model, @PathVariable("id") int id) {
        model.addAttribute("student", studentService.getOneStudent(id));
        return "pages/details";
    }

    @RequestMapping(value = "/students/delete/{id}", method = RequestMethod.POST)
    public String deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }

    @GetMapping("/students/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Student student = studentService.getOneStudent(id);
        model.addAttribute("student", student);
        return "pages/update";
    }

    @PostMapping("/students/update/{id}")
    public String updateStudent(@PathVariable("id") int id, @ModelAttribute Student student) {
        student.setId(id);
        studentService.updateStudent(id, student);
        return "redirect:/students";
    }

    @GetMapping("/students/search")
    public String searchStudent(@RequestParam(value = "search", required = false) String search, Model model) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("students", studentService.searchStudent(search));
        } else {
            model.addAttribute("students", studentService.getAllStudents());
        }
        return "pages/students";
    }
}
