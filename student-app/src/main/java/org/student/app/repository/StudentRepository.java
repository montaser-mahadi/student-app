package org.student.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.student.app.entity.StudentEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByIdAndIsDeleted(Long id, Boolean aFalse);

    List<StudentEntity> findByClassNameAndIsDeleted(String className, Boolean aFalse);

    Optional<StudentEntity> findByParentPhoneAndIsDeleted(String parentPhone, Boolean aFalse);

    Optional<StudentEntity> findByIdAndParentPhoneAndIsDeleted(Long id, String parentPhone, Boolean aFalse);

    List<StudentEntity> findAllByIsDeleted(Boolean aFalse);
}
