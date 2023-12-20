package org.student.app.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.student.app.dto.StudentDto;
import org.student.app.service.StudentService;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("api/v1/students")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class StudentController {

    private final StudentService studentService;

    // @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping
    public ResponseEntity<List<StudentDto>> getStudents() {
        return ResponseEntity.ok(studentService.getStudents());
    }

    //  @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@Valid @RequestBody StudentDto studentDto) {
        log.info("Student: {}", studentDto);
        StudentDto response = studentService.addStudent(studentDto);
        return ResponseEntity.ok(response);
    }


    //@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping(value = "{id}")
    public ResponseEntity<StudentDto> getById(@PathVariable(value = "id") Long id) {
        log.info("Student Id:{}", id);
        StudentDto response = studentService.searchStudentById(id);
        return ResponseEntity.ok(response);
    }

    // @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @GetMapping(value = "/class-name/{className}")
    public ResponseEntity<List<StudentDto>> getByClassName(@PathVariable(value = "className") String className) {
        log.info("Student className:{}", className);
        List<StudentDto> response = studentService.searchStudentByClass(className);
        return ResponseEntity.ok(response);
    }

    // @Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id) {
        log.info("Student Id:{}", id);
        studentService.deleteStudent(id);
        return ResponseEntity.ok(null);
    }

    //@Operation(security = {@SecurityRequirement(name = BEARER_KEY_SECURITY_SCHEME)})
    @PutMapping
    public ResponseEntity<StudentDto> updateStudent(@Valid @RequestBody StudentDto studentDto) {
        log.info("update Student{}", studentDto);
        StudentDto response = studentService.updateStudent(studentDto);
        return ResponseEntity.ok(response);
    }
}