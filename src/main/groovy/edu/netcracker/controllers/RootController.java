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
public class RootController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/", method= RequestMethod.GET)
    public String hello(Locale locale, Model model) {
        model.addAttribute("greeting", "Hello World!");
        model.addAttribute("students", studentService.findAll());

        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String formattedDate = dateFormat.format(date);
        model.addAttribute("currentTime", formattedDate);

        return "hello";
    }

    @RequestMapping(value = "/add/student", method = RequestMethod.GET)
     public String initForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "add";
    }


    @RequestMapping(value = "/add/student",method = RequestMethod.POST)
    public String submitForm(@Valid Student student, BindingResult result) {
        if(result.hasErrors()) {
            return "add";
        }
        studentService.saveAndFlush(student);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/student",method = RequestMethod.GET)
    public String initForm(@RequestParam(value="id", required=true) Long id, Model model) {
        Student editedStudent = studentService.getOne(id);
        model.addAttribute("student", editedStudent);
        return "edit";
    }

    @RequestMapping(value = "/edit/student",method = RequestMethod.POST)
    public String submitDeleteForm(@Valid Student student, BindingResult result) {
        if(result.hasErrors()) {
            return "edit";
        }
        studentService.saveAndFlush(student);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete/student",method = RequestMethod.GET)
    public String submitForm(@RequestParam(value="id", required=true) Long id) {
        studentService.delete(id);
        return "redirect:/";
    }

}