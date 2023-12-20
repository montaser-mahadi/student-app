package org.student.app.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;


@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
//@JsonTypeName("Student")
//@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class StudentDto {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "firstName")
    @NotNull(message = "FirstName is required")
    private String firstName;

    @JsonProperty(value = "secondName")
    @NotEmpty(message = "SecondName is required")
    private String secondName;

    @JsonProperty(value = "className")
    @NotEmpty(message = "ClassName is required")
    private String className;

    @JsonProperty(value = "age")
    @NotNull(message = "Student Age should be less than 5")
    @Min(5)
    @Max(12)
    private Integer age;

    @JsonProperty(value = "parentPhone")
    @NotBlank(message = "ParentPhone is required")
    private String parentPhone;

    @JsonProperty(value = "address")
    @NotBlank(message = "Address is required")
    private String address;
}
