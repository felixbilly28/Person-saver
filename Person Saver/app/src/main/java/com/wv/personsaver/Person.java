package com.wv.personsaver;


import android.os.Build;

import java.time.LocalDate;
import java.util.Date;

import static java.time.LocalDate.now;

public class Person  {
    private String fname;
    private String lname;
    private String gender;
    public int yob;


    public Person(String fname, String lname,
                  String gender, int yob){
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.yob = yob;
    }

    public Person() {
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getYob() {
        return yob;
    }

    public void setYob(int yob) {
        this.yob = yob;
    }


    @Override
    public String toString() {
        return "Person{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", gender='" + gender + '\'' +
                ", yob=" + yob +
                '}';
    }


}