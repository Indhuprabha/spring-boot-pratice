package com.example.JPAdemo.Controller;


import com.example.JPAdemo.Model.Student;
import com.example.JPAdemo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStuds();

            if (students.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);

        }
    }

    @GetMapping("/students/{rno}")
    public ResponseEntity<?> getStudentById(@PathVariable int rno) {
        try {
            ResponseEntity student = studentService.getStudentsByID(rno);


            if (student == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Student not found with rno: " + rno);
            } else {
                return ResponseEntity.ok(student);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }


    @PostMapping("/students")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        try {
            studentService.addStudent(student);
            return ResponseEntity.accepted().body("Student added successfully.");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Student not added. Error: " + e.getMessage());
        }
    }


    @PostMapping("/students/all")
    public ResponseEntity<String> addAllStudents(@RequestBody List<Student> students) {
        try {
            studentService.addAllStudent(students);
            return ResponseEntity.ok("Students added successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid student data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while adding students: " + e.getMessage());
        }
    }




    @PutMapping("/students")
    public ResponseEntity<String> updateStud(@RequestBody Student student) {
        try {
            studentService.updateStud(student);
            return ResponseEntity.ok("Student updated successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid student data: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while updating the student: " + e.getMessage());
        }
    }



    @DeleteMapping("/students/{rno}")
    public String deleteStud(@PathVariable int rno){
        studentService.deleteStudByRno(rno);
        return "deleted..";
    }


    @DeleteMapping("/students/clear")
    public String clearStud(){
        studentService.clearStud();
        return "cleared student records..";

    }

    @GetMapping("/students/technology/{tech}")
    public List<Student> getStudTechnology(@PathVariable String tech){
       return studentService.getStudTechnology(tech);

    }

}
