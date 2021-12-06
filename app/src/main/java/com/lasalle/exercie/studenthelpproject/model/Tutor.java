package com.lasalle.exercie.studenthelpproject.model;

import java.util.Date;

public class Tutor {


    private  int tutorId;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String gender;
    private String dateOfBirth;
    private  String skill;


    public Tutor() {
    }

    public Tutor(int tutorId, String firstName, String lastName, String email, String gender, String dateOfBirth, String skill) {
        this.tutorId = tutorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.skill = skill;

    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return
                "TutorId = " + tutorId + "\n" +
                "FirstName = " + firstName + " " + lastName + "\n" +
                "Email = " + email + "\n" +
                "Gender " + gender + "\n" +
                "DateOfBirth = " + dateOfBirth ;


    }
}
