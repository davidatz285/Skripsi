package com.example.ifstudentportal.View;

import android.content.Context;

import com.example.ifstudentportal.Model.Mahasiswa;

public interface IHomeActivity {
    Context getContext();
    void displayMahasiswaInfo(Mahasiswa mahasiswa);
}
