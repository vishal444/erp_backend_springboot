package com.test.ana.company;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.test.ana.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private long id;

    private String companyName;

    private String companyAddress;

    private double zipCode;

    private double companyPhoneNumber;

    private double gstNumber;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING) //To properly serialize and deserialize the category field, you can use the @JsonFormat annotation from the Jackson library
    private GstClass gstClass;

    private String userName;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
