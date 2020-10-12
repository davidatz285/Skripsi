package com.example.ifstudentportal.Model;

public class Wrapper {
    private Mahasiswa mahasiswa;
    private String phpSessId;

    private int respCode;

    public Wrapper(String phpSessId, int respCode,Mahasiswa mahasiswa) {
        this.phpSessId = phpSessId;
        this.respCode = respCode;
        this.mahasiswa = mahasiswa;
    }

    public void setPhpSessId(String phpSessId) {
        this.phpSessId = phpSessId;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public String getPhpSessId() {
        return phpSessId;
    }

    public int getRespCode() {
        return respCode;
    }
}
