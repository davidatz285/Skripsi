package com.example.ifstudentportal.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ifstudentportal.Model.Mahasiswa;
import com.example.ifstudentportal.Model.Wrapper;
import com.example.ifstudentportal.Presenter.LoginPresenter;
import com.example.ifstudentportal.R;

public class HomeActivity extends AppCompatActivity implements ILoginActivity{
    protected TextView tvNama;
    protected TextView tvEmail;
    protected TextView tvNPM;
    protected LoginPresenter loginPresenter;
    protected Mahasiswa mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        this.tvNama = findViewById(R.id.tv_nama);
        this.tvEmail = findViewById(R.id.tv_email);
        this.tvNPM = findViewById(R.id.tv_npm);
        Bundle bundle = getIntent().getExtras();
        String phpSessId = bundle.getString("phpSessId");
        //this.mahasiswa = (Mahasiswa) bundle.getSerializable("mhs");
        this.loginPresenter = new LoginPresenter(this);
        loginPresenter.getMahasiswaInfo(phpSessId);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void displayMahasiswaInfo(Mahasiswa mahasiswa) {
        Log.d("homesiswa",this.mahasiswa.getNama());
        this.tvNama.setText(tvNama.getText()+" "+mahasiswa.getNama());
        this.tvEmail.setText(tvEmail.getText()+" "+mahasiswa.getEmail());
        this.tvNPM.setText(tvNPM.getText()+" "+mahasiswa.getNpm());
    }
    @Override
    public void login(String email, String npm) {

    }

    @Override
    public void switchToHomeActivity(Wrapper wrapper) {

    }
}
