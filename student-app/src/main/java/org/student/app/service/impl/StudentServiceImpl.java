package org.student.app.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.student.app.constant.StudentAppConstant;
import org.student.app.dto.StudentDto;
import org.student.app.entity.StudentEntity;
import org.student.app.exception.ApiRequestException;
import org.student.app.mapper.StudentMapper;
import org.student.app.repository.StudentRepository;
import org.student.app.service.StudentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public StudentDto searchStudentById(Long id) {

        Optional<StudentEntity> existedStudent = studentRepository.findByIdAndIsDeleted(id, Boolean.FALSE);
        if (existedStudent.isEmpty()) {
            throw new ApiRequestException(StudentAppConstant.RECORD_DOES_NOT_EXIST);
        }

        return StudentMapper.toDto(existedStudent.get());
    }

    @Override
    public List<StudentDto> searchStudentByClass(String className) {
        List<StudentEntity> students = studentRepository.findByClassNameAndIsDeleted(className, Boolean.FALSE);

        if (students.isEmpty()) {
            throw new ApiRequestException(StudentAppConstant.NO_RECORD_FOUND);
        }
        
        return students.stream().map(StudentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public StudentDto addStudent(StudentDto studentDto) {

        Optional<StudentEntity> existStudent = studentRepository.findByParentPhoneAndIsDeleted(
                studentDto.getParentPhone(), Boolean.FALSE);

        if (existStudent.isPresent()) {
            throw new ApiRequestException(String.format(StudentAppConstant.RECORD_ALREADY_EXIST, ", " +
                    studentDto.getParentPhone()));
        }
        StudentEntity studentEntity = studentRepository.save(StudentMapper.toEntity(studentDto));
        return StudentMapper.toDto(studentEntity);
    }

    @Override
    public List<StudentDto> getStudents() {
        List<StudentEntity> students = studentRepository.findAllByIsDeleted(Boolean.FALSE);
        return students.stream().map(StudentMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteStudent(Long studentId) {

        Optional<StudentEntity> existedStudent = studentRepository.findByIdAndIsDeleted(studentId, Boolean.FALSE);
        if (existedStudent.isEmpty()) {
            throw new ApiRequestException(StudentAppConstant.RECORD_DOES_NOT_EXIST);
        }

        existedStudent.get().setIsDeleted(Boolean.TRUE);
        studentRepository.save(existedStudent.get());
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {
        Optional<StudentEntity> existedStudent = studentRepository.findByIdAndIsDeleted(studentDto.getId(), Boolean.FALSE);
        if (existedStudent.isEmpty()) {
            throw new ApiRequestException(StudentAppConstant.RECORD_DOES_NOT_EXIST);
        }
        existedStudent = studentRepository.findByIdAndParentPhoneAndIsDeleted(studentDto.getId(),
                studentDto.getParentPhone(), Boolean.FALSE);

        if (existedStudent.isEmpty()) {
            throw new ApiRequestException(StudentAppConstant.RECORD_DOES_NOT_EXIST);
        }
        StudentEntity studentEntity = StudentMapper.toUpdateStudent(studentDto);

        return StudentMapper.toDto(studentRepository.save(studentEntity));
    }
}
