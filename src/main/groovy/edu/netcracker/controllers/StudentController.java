package edu.netcracker.controllers;

import edu.netcracker.model.Student;
import edu.netcracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/student/all", method = RequestMethod.GET)
    public String hello(Locale locale, Model model) {
        model.addAttribute("greeting", "Hello World!");
        model.addAttribute("students", studentService.findAll());

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("currentTime", formattedDate);

        return "student/all";
    }

    @RequestMapping(value = "/student/add", method = RequestMethod.GET)
    public String initForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "student/add";
    }


    @RequestMapping(value = "/student/add", method = RequestMethod.POST)
    public String submitForm(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "student/add";
        }
        studentService.saveAndFlush(student);
        return "redirect:/student/all";
    }

    @RequestMapping(value = "/student/edit", method = RequestMethod.GET)
    public String initForm(@RequestParam(value = "id", required = true) Long id, Model model) {
        Student editedStudent = studentService.getOne(id);
        model.addAttribute("student", editedStudent);
        return "student/edit";
    }

    @RequestMapping(value = "/student/edit", method = RequestMethod.POST)
    public String submitDeleteForm(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "student/edit";
        }
        studentService.saveAndFlush(student);
        return "redirect:/student/all";
    }

    @RequestMapping(value = "/student/delete", method = RequestMethod.GET)
    public String submitForm(@RequestParam(value = "id", required = true) Long id) {
        studentService.delete(id);
        return "redirect:/student/all";
    }
}
