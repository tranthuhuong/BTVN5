package com.example.huongthutran.btvn_buoi5;

/**
 * Created by Huong Thu Tran on 3/17/2018.
 */

public class Student {
    public static String STUDENT_TABLENAME="STUDENTS";
    public static String STUDENT_ID="ID_STUDENT";
    public static String STUDENT_USERNAME="FNAME";
    public static String STUDENT_EMAIL="EMAIL";

    private  int id_student;
    private  String fName;
    private String email;

    public Student(){

    }
    public Student(int id_student, String fName, String email) {
        this.id_student = id_student;
        this.fName = fName;
        this.email = email;
    }

    public int getId_student() {
        return id_student;
    }

    public void setId_student(int id_student) {
        this.id_student = id_student;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String toString(){
        return fName+" - "+email;
    }
}
