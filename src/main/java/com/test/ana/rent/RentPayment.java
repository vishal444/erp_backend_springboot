package com.test.ana.rent;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class RentPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double paidRent;
    private double rentExtra;
    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING) //To properly serialize and deserialize the category field, you can use the @JsonFormat annotation from the Jackson library
    private MonthList monthList;
    private Date date;
    private double rentOutstanding;

    @OneToOne
    @JoinColumn(name = "rent_id",referencedColumnName = "rent_id")
    private Rent rent;
    private String userName;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
