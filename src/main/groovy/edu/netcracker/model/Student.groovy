package edu.netcracker.model

import groovy.transform.CompileStatic
import groovy.transform.ToString
import org.hibernate.envers.Audited
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.*

@ToString
@CompileStatic
@Entity
@Audited
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id
    String firstName
    String lastName
    String middleName
    String type
    @Email
    @NotEmpty
    String email
    String phone
    String university
    String specialty
    String course
    @Column(name = "groupName")
    String group
}
