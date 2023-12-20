package org.student.app.service;


import org.student.app.dto.StudentDto;

import java.util.List;

public interface StudentService {

    StudentDto addStudent(StudentDto studentDto);

    List<StudentDto> getStudents();

    StudentDto searchStudentById(Long id);

    List<StudentDto> searchStudentByClass(String className);

    void deleteStudent(Long id);

    StudentDto updateStudent(StudentDto studentDto);

}
