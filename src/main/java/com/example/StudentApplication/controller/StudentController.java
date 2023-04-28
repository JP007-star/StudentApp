package com.example.StudentApplication.controller;



import com.example.StudentApplication.entity.Student;
import com.example.StudentApplication.service.MailSenderService;
import com.example.StudentApplication.service.StudentService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentService service;


    @Autowired
    MailSenderService mailSenderService;

    @GetMapping("/")
    public String indexPage(HttpServletRequest request){
        return "index";
    }

    @GetMapping("/students")
    public String studentsPage(Model model){
        List<Student> studentList=service.readAllStudents();
        model.addAttribute("students",studentList);
        return "students";
    }

    @PostMapping("/createStudent")
    public String  createStudent(@RequestParam String name,@RequestParam String age,@RequestParam String fee,@RequestParam String email) throws MessagingException {
        Student student=service.createStudent(name, age, fee,email);
        mailSenderService.sendMail(email,name);
        return "redirect:/students";
    }

    @GetMapping("/readAllStudents")
    public ResponseEntity<?> readAllStudents(){
        List<Student> studentList=service.readAllStudents();
        return new ResponseEntity<>(studentList,HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<?> updateStudent(@RequestBody Student stud){
        System.out.println(stud);
        Student student= service.updateStudent(stud);
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    @RequestMapping("/deleteStudent")
    public  String deleteStudent(@RequestParam String studentId){
        service.deleteStudent(Integer.parseInt(studentId));
        return "redirect:/students";
    }

}


// CRUD    --> CREATE READ UPDATE AND DELETE
