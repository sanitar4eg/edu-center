package edu.netcracker.controllers

import edu.netcracker.model.Student
import edu.netcracker.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

import javax.validation.Valid

@Controller
public class StudentController {

    @Autowired
    StudentService studentService

    @RequestMapping(value = "/student/all", method = RequestMethod.GET)
    public String hello(Locale locale, Model model) {
        model.addAttribute("students", studentService.findAll())
        "student/all"
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.GET)
    public String initForm(Model model) {
        model.addAttribute("student", new Student())
        "student/add"
    }


    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public String submitForm(@Valid Student student, BindingResult result) {
        String target = "redirect:/student/all"
        if (result.hasErrors()) {
            target = "student/add"
        }
        studentService.saveAndFlush(student)
        target
    }

    @RequestMapping(value = "/student/edit", method = RequestMethod.GET)
    public String initForm(@RequestParam(value = "id", required = true) Long id, Model model) {
        model.addAttribute("student", studentService.getOne(id))
        return "student/edit"
    }

    @RequestMapping(value = "/student/edit", method = RequestMethod.POST)
    public String submitDeleteForm(@Valid Student student, BindingResult result) {
        String target = "redirect:/student/all"
        if (result.hasErrors()) {
            target = "student/edit"
        }
        studentService.saveAndFlush(student)
        target
    }

    @RequestMapping(value = "/student/delete", method = RequestMethod.GET)
    public String submitForm(@RequestParam(value = "id", required = true) Long id) {
        studentService.delete(id)
        "redirect:/student/all"
    }
}
