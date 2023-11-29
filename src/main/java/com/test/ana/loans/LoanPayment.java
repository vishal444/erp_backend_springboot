package com.test.ana.loans;

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
public class LoanPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double paidInstallment;

    private double loanExtraPayment;

    @Enumerated(EnumType.STRING)
    @JsonFormat(shape = JsonFormat.Shape.STRING) //To properly serialize and deserialize the category field, you can use the @JsonFormat annotation from the Jackson library
    private MonthList monthList;

    private Date date;

    private double loanOutstanding;

    @OneToOne
    @JoinColumn(name = "loan_id",referencedColumnName = "loan_id")
    private Loan loan;
    private String userName;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
