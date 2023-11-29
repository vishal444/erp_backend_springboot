package com.test.ana.assets;

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
public class Assets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double capital;

    private double furniture;

    private double machinery;

    private double land;

    private double building;

    private double equipments;

    private double motorVehicles;

    private double bankDeposits;

    private double investments;

    private String userName;
    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;
}
