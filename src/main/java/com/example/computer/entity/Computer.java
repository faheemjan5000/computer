package com.example.computer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COMPUTER")
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMPUTER_ID")
    private Integer id;
    @Column(name = "COMPUTER_NAME")
    private String name;
    @Column(name = "PROCESSOR")
    private String processor;
    @Column(name = "RAM")
    private String ram;

}
