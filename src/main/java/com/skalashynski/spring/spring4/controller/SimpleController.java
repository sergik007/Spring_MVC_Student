package com.skalashynski.spring.spring4.controller;

import com.skalashynski.spring.spring4.model.Student;
import com.skalashynski.spring.spring4.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "simpleController")
public class SimpleController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getStudents(Model model) {
        model.addAttribute("studentList", studentService.findAllStudents());
        return "/view/students";
    }

    @RequestMapping(value = "/add-student", method = RequestMethod.GET)
    public ModelAndView student() {
        return new ModelAndView("view/addStudent", "command", new Student());
    }

    @RequestMapping(value = "/add-student", method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("student") @Validated Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/view/addStudent";
        }
        studentService.saveStudent(student);
        return "redirect:students";
    }

    @RequestMapping(value = "/get-student-{id}", method = RequestMethod.GET)
    public String getStudent(@PathVariable String id, Model model) {
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "/view/student";
    }

    @RequestMapping(value = "/delete-student", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable Student student) {
        studentService.deleteStudent(student);
    }

    //    @ModelAttribute("webFrameworkList")
//    public List<String> getWerFrameworkList() {
//        List<String> webFrameworkList = new ArrayList<>();
//        webFrameworkList.add("Spring MVC");
//        webFrameworkList.add("Struts 1");
//        webFrameworkList.add("Struts 2");
//        webFrameworkList.add("Apache Wicket");
//        return webFrameworkList;
//    }
    @ModelAttribute("student")
    public Student createStudentModel() {
        return new Student();
    }

}

