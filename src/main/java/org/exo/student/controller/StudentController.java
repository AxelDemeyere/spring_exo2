package org.exo.student.controller;

import jakarta.validation.Valid;
import org.exo.student.model.Student;
import org.exo.student.service.StudentService;
import org.exo.student.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    private final StudentService studentService;
    private final UserService userService;

    public StudentController(StudentService studentService, UserService userService) {
        this.studentService = studentService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String home() {
        return "pages/home";
    }


    @GetMapping("/students")
    public String students(@RequestParam(value = "search", required = false) String search, Model model) {
        if (userService.checkLogin()) {
            if (search != null && !search.isEmpty()) {
                model.addAttribute("students", studentService.searchStudent(search));
            } else {
                model.addAttribute("students", studentService.getAllStudents());
            }
            return "pages/students";
        } else {
            return "auth/login";
        }
    }

    @GetMapping("/students/register")
    public String register(Model model) {
        if (userService.checkLogin()) {
            Student student = new Student();
            model.addAttribute("student", student);
            return "pages/form";
        } else {
            return "auth/login";
        }
    }

    @PostMapping(value = "/students/register/")
    public String createStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {
        if (userService.checkLogin()) {
            if (bindingResult.hasErrors()) {
                return "pages/form";
            } else {
                studentService.saveStudent(student);
            }
            return "redirect:/students";
        } else {
            return "auth/login";
        }
    }

    @RequestMapping("/students/details/{id}")
    public String details(Model model, @PathVariable("id") int id) {
        if (userService.checkLogin()) {
            Student student = studentService.getOneStudent(id);
            model.addAttribute("student", student);
            return "pages/details";
        } else {
            return "auth/login";
        }
    }

    @PostMapping(value = "/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        if (userService.checkLogin()) {
            studentService.deleteStudent(id);
            return "redirect:/students";
        } else {
            return "auth/login";
        }
    }

    @GetMapping("/students/update/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        if (userService.checkLogin()) {
            Student student = studentService.getOneStudent(id);
            model.addAttribute("student", student);
            return "pages/update";
        } else {
            return "auth/login";
        }
    }

    @PostMapping("/students/update/{id}")
    public String updateStudent(@ModelAttribute Student student) {
        if (userService.checkLogin()) {
            studentService.saveStudent(student);
            return "redirect:/students";
        } else {
            return "auth/login";
        }
    }
}
