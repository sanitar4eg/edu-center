package edu.netcracker.model
import groovy.transform.CompileStatic
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.*
import javax.validation.constraints.Size

@CompileStatic
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    String firstName
    @Size(min = 2, max = 16)
    String lastName
    String middleName
    @Email
    @NotEmpty
    String email
    String phone
    String university
    String specialty
    String course
    @Column(name = "groupe")
    String group


}