package org.student.app.mapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.student.app.dto.StudentDto;
import org.student.app.entity.StudentEntity;

@Slf4j
public class StudentMapper {
    private StudentMapper() {
    }

    public static StudentEntity toEntity(StudentDto studentDto) {
        return StudentEntity.builder()
                .id(studentDto.getId())
                .firstName(studentDto.getFirstName())
                .secondName(studentDto.getSecondName())
                .age(studentDto.getAge())
                .address(studentDto.getAddress())
                .className(studentDto.getClassName())
                .parentPhone(studentDto.getParentPhone())
                .isDeleted(Boolean.FALSE)
                .build();
    }

    public static StudentDto toDto(StudentEntity studentEntity) {
        return StudentDto.builder()
                .id(studentEntity.getId())
                .firstName(studentEntity.getFirstName())
                .secondName(studentEntity.getSecondName())
                .age(studentEntity.getAge())
                .address(studentEntity.getAddress())
                .className(studentEntity.getClassName())
                .parentPhone(studentEntity.getParentPhone())
                .build();
    }

    public static StudentEntity toUpdateStudent(StudentDto studentDto) {
        return StudentEntity.builder()
                .id(!ObjectUtils.isEmpty(studentDto.getId()) ? studentDto.getId() : null)
                .firstName(!ObjectUtils.isEmpty(studentDto.getFirstName()) ? studentDto.getFirstName() : null)
                .secondName(!ObjectUtils.isEmpty(studentDto.getSecondName()) ? studentDto.getSecondName() : null)
                .age(!ObjectUtils.isEmpty(studentDto.getAge()) ? studentDto.getAge() : null)
                .address(!ObjectUtils.isEmpty(studentDto.getAddress()) ? studentDto.getAddress() : null)
                .className(!ObjectUtils.isEmpty(studentDto.getClassName()) ? studentDto.getClassName() : null)
                .parentPhone(!ObjectUtils.isEmpty(studentDto.getParentPhone()) ? studentDto.getParentPhone() : null)
                .isDeleted(Boolean.FALSE)
                .build();
    }
}

