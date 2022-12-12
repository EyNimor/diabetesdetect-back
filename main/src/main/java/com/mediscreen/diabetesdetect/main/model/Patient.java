package com.mediscreen.diabetesdetect.main.model;

import java.time.LocalDate;
import java.util.UUID;

import com.mediscreen.diabetesdetect.main.annotation.ExcludeFromJacocoGeneratedReport;
import com.mediscreen.diabetesdetect.main.constant.Sex;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter @Setter
@ExcludeFromJacocoGeneratedReport
public class Patient {
    
    private final UUID patientId;
    private String lastName;
    private String firstName;
    private LocalDate birthDate;
    private Sex sex;
    private String address;
    private String phone;

    public Patient() {
        this.patientId = UUID.randomUUID();
    }

    public Patient(String lastName, String firstName, LocalDate birthDate, Sex sex, String address, String phone) {
        this.patientId = UUID.randomUUID();
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setBirthDate(birthDate);
        this.setSex(sex);
        this.setAddress(address);
        this.setPhone(phone);
    }

    public Patient(String lastName, String firstName, LocalDate birthDate, String sex, String address, String phone) {
        this.patientId = UUID.randomUUID();
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setBirthDate(birthDate);
        this.setSexFromString(sex);
        this.setAddress(address);
        this.setPhone(phone);
    }

    public Patient(UUID patientId, String lastName, String firstName, LocalDate birthDate, Sex sex, String address, String phone) {
        this.patientId = patientId;
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setBirthDate(birthDate);
        this.setSex(sex);
        this.setAddress(address);
        this.setPhone(phone);
    }

    public Patient(UUID patientId, String lastName, String firstName, LocalDate birthDate, String sex, String address, String phone) {
        this.patientId = patientId;
        this.setLastName(lastName);
        this.setFirstName(firstName);
        this.setBirthDate(birthDate);
        this.setSexFromString(sex);
        this.setAddress(address);
        this.setPhone(phone);
    }
    
    public void setSexFromString(String sex) {
        switch(sex) {
            case "M": case "H": case "Homme": case "Men":
                this.sex = Sex.MEN;
                break;
            case "F": case "W": case "Femme": case "Women": case "Female":
                this.sex = Sex.WOMEN;
                break;
            case "I": case "Intersexe": case "Intersex":
                this.sex = Sex.INTERSEX;
                break;
            default:
                throw new IllegalArgumentException("Invalid Sex Value");
        }
    }

}
