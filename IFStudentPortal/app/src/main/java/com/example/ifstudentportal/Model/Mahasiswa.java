package com.example.ifstudentportal.Model;

public class Mahasiswa {
    private String nama;
    private String npm;
    private String email;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNpm() {
        return npm;
    }

    public void setNpm(String npm) {
        this.npm = npm;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Mahasiswa() {
    }

    public Mahasiswa(String nama, String npm) {
        this.nama = nama;
        this.npm = npm;
        this.email = npm.concat("@student.unpar.ac.id");
    }

    public String getEmailAddress() {
        if (npm.matches("[2]{1}[0]{1}\\d{8}") && Integer.parseInt(npm.substring(0,4)) < 2017){
            return npm.substring(4, 6) + npm.substring(2, 4) + npm.substring(7, 10) + "@student.unpar.ac.id";
        } else {
            return npm + "@student.unpar.ac.id";
        }
    }
}
