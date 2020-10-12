package com.example.ifstudentportal.View;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.example.ifstudentportal.Model.Mahasiswa;
import com.example.ifstudentportal.Model.Wrapper;
import com.example.ifstudentportal.Presenter.LoginPresenter;
import com.example.ifstudentportal.R;
import com.google.android.material.button.MaterialButton;

import java.io.Serializable;

public class LoginActivity extends AppCompatActivity implements ILoginActivity, View.OnClickListener {
    private EditText etPassword;
    private EditText etEmail;
    private AppCompatCheckBox checkbox;
    private MaterialButton btnLogin;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        this.checkbox = findViewById(R.id.checkbox);
        this.etPassword = findViewById(R.id.et_password);
        this.etEmail = findViewById(R.id.et_email);
        this.btnLogin = findViewById(R.id.btn_login);
        this.btnLogin.setOnClickListener(this);
        this.loginPresenter = new LoginPresenter(this);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // show password
                if (isChecked)
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                else {
                    // hide password
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        String email = this.etEmail.getText().toString();
        String password = this.etPassword.getText().toString();
        Log.d("email",email);
        Log.d("pw",password);
        this.login(email,password);
    }

    @Override
    public void login(String email, String npm) {
        this.loginPresenter.login(email, npm);

    }

    @Override
    public void switchToHomeActivity(Wrapper wrapper) {
        //Log.d("info",phpSessId);
        if(wrapper.getRespCode()==200){
            Intent i = new Intent(LoginActivity.this,HomeActivity.class);
            i.putExtra("phpSessId",wrapper.getPhpSessId());
            //i.putExtra("mhs", (Serializable) wrapper.getMahasiswa());
            startActivity(i);
            finish();
        }

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void displayMahasiswaInfo(Mahasiswa mahasiswa) {

    }

    public LoginPresenter passPresenter(){
        return this.loginPresenter;
    }

}
