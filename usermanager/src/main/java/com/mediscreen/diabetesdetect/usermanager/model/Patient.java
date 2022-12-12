package com.mediscreen.diabetesdetect.usermanager.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
@Entity @Table(name = "user")
public class Patient implements Serializable {
    
    @Id
    @Column(name = "patient_id", nullable = false)
    private final String patientId;
    @Column(name = "lastname", nullable = false)
    private String lastName;
    @Column(name = "firstname", nullable = false)
    private String firstName;
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;
    @Column(name = "sex", nullable = false)
    private String sex;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phone_number", nullable = false)
    private String phone;

    public Patient() {
        this.patientId = UUID.randomUUID().toString();
    }

    public Patient(String lastName, String firstName, LocalDate birthDate, String sex, String address, String phone) {
        this.patientId = UUID.randomUUID().toString();
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setBirthDate(birthDate);
        this.setSex(sex);
        this.setAddress(address);
        this.setPhone(phone);
    }

    public Patient(UUID patientId, String lastName, String firstName, LocalDate birthDate, String sex, String address, String phone) {
        this.patientId = patientId.toString();
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setBirthDate(birthDate);
        this.setSex(sex);
        this.setAddress(address);
        this.setPhone(phone);
    }

    public Patient(String patientId, String lastName, String firstName, LocalDate birthDate, String sex, String address, String phone) {
        this.patientId = patientId;
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setBirthDate(birthDate);
        this.setSex(sex);
        this.setAddress(address);
        this.setPhone(phone);
    }

}
