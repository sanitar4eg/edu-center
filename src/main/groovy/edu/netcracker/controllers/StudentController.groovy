package edu.netcracker.controllers
import edu.netcracker.model.Student
import edu.netcracker.service.StudentService
import edu.netcracker.view.StudentExportExcelView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.ModelAndView

import javax.validation.Valid

@Controller
public class StudentController {

    private static final String STUDENT_LIST = "student/list"
    private static final String STUDENT_ADD = "student/add"
    private static final String REDIRECT_STUDENT_LIST = "redirect:/student/list"
    private static final String STUDENT_EDIT = "student/edit"
    private static final String STUDENT_DELETE = "student/delete"
    private static final String STUDENT_QA = "student/qa"
    private static final String STUDENT_DEV = "student/dev"
    private static final String STUDENT_EXPORT = "student/export"
    private static final String STUDENT_IMPORT = "student/import"

    @Autowired
    StudentService studentService

    @RequestMapping(value = StudentController.STUDENT_LIST, method = RequestMethod.GET)
    public String getAll(Locale locale, Model model) {
        model.addAttribute("students", studentService.findAll())
        STUDENT_LIST
    }

    @RequestMapping(value = StudentController.STUDENT_QA, method = RequestMethod.GET)
    public String getQA(Model model) {
        model.addAttribute("students", studentService.findQA())
        STUDENT_LIST
    }

    @RequestMapping(value = StudentController.STUDENT_DEV, method = RequestMethod.GET)
    public String getDev(Model model) {
        model.addAttribute("students", studentService.findDev())
        STUDENT_LIST
    }

    @RequestMapping(value = StudentController.STUDENT_ADD, method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("student", new Student())
        STUDENT_ADD
    }


    @RequestMapping(value = StudentController.STUDENT_ADD, method = RequestMethod.POST)
    public String addSubmit(@Valid Student student, BindingResult result) {
        String target = STUDENT_ADD
        if (!result.hasErrors()) {
            target = REDIRECT_STUDENT_LIST
            studentService.saveAndFlush(student)
        }
        target
    }

    @RequestMapping(value = StudentController.STUDENT_EDIT, method = RequestMethod.GET)
    public String edit(@RequestParam(value = "id", required = true) Long id, Model model) {
        model.addAttribute("student", studentService.getOne(id))
        return STUDENT_EDIT
    }

    @RequestMapping(value = StudentController.STUDENT_EDIT, method = RequestMethod.POST)
    public String editSubmit(@Valid Student student, BindingResult result) {
        String target = STUDENT_EDIT
        if (!result.hasErrors()) {
            target = REDIRECT_STUDENT_LIST
            studentService.saveAndFlush(student)
        }
        target
    }

    @RequestMapping(value = StudentController.STUDENT_DELETE, method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) Long id) {
        studentService.delete(id)
        REDIRECT_STUDENT_LIST
    }

    @RequestMapping(value = StudentController.STUDENT_EXPORT, method = RequestMethod.GET)
    public ModelAndView export() {
        new ModelAndView(new StudentExportExcelView(), "students", studentService.findAll())
    }

    @RequestMapping(value = StudentController.STUDENT_IMPORT, method = RequestMethod.POST)
    public String handleImport(@RequestParam("file") MultipartFile file) {
        studentService.handleImport(file)
        REDIRECT_STUDENT_LIST
    }
}
