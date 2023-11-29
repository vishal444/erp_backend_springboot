package com.test.ana.salary;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.ana.employees.Employee;
import com.test.ana.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salary_id")
    private long id;
    private double salaryReceived;
    private Date date;
    private double salaryExtra;
    private double salaryOutstanding;
    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING) //To properly serialize and deserialize the category field, you can use the @JsonFormat annotation from the Jackson library
    private MonthList monthList;
    @OneToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "employee_id")
    private Employee employee;
    private String userName;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
