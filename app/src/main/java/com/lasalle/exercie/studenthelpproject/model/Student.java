package com.lasalle.exercie.studenthelpproject.model;

import java.util.Date;

public class Student {


    private  int  studentId;
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String gender;
    private  String dateOfBirth;
    public Student() {
    }




    public Student(int studentId, String firstName, String lastName, String email, String gender, String dateOfBirth) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    @Override
    public String toString() {
        return
                "StudentId = " + studentId + "\n" +
                        "FirstName = " + firstName + "\n" +
                        "LastName = " + lastName + "\n" +
                        "Email = " + email + "\n" +
                        "Gender = " + gender + "\n" +
                        "DateOfBirth = " + dateOfBirth ;

    }
}
