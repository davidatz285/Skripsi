package com.example.ifstudentportal.View;

import android.content.Context;

import com.example.ifstudentportal.Model.Mahasiswa;
import com.example.ifstudentportal.Model.Wrapper;

public interface ILoginActivity {
    void login(String email,String npm);
    void switchToHomeActivity(Wrapper wrapper);
    Context getContext();
    void displayMahasiswaInfo(Mahasiswa mahasiswa);
}
