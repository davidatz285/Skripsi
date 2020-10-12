package com.example.ifstudentportal.Presenter;

import android.content.Context;
import android.view.View;

import com.example.ifstudentportal.Model.Mahasiswa;
import com.example.ifstudentportal.Model.Scrapper;
import com.example.ifstudentportal.Model.Wrapper;
import com.example.ifstudentportal.View.IHomeActivity;
import com.example.ifstudentportal.View.ILoginActivity;

public class LoginPresenter {
    protected View view;
    private ILoginActivity iLoginActivity;
    private IHomeActivity iHomeActivity;
    protected Scrapper scrapper;


    public LoginPresenter(ILoginActivity iLoginActivity) {
        this.iLoginActivity = iLoginActivity;
        this.scrapper = new Scrapper(iLoginActivity.getContext(),this);
    }

//    public LoginPresenter(IHomeActivity iHomeActivity) {
//        this.iHomeActivity = iHomeActivity;
//        this.scrapper = new Scrapper(iHomeActivity.getContext(),this);
//    }

    public void login(String email, String npm){
        this.scrapper.login(email,npm);
    }

    public void switchToHomeActivity(Wrapper wrapper) {
        this.iLoginActivity.switchToHomeActivity(wrapper);
    }

    public void getMahasiswaInfo(String phpSessId) {
        this.scrapper.getMahasiswaInfo(phpSessId);
    }

    public void displayMahasiswaInfo(Mahasiswa mahasiswa) {
        this.iLoginActivity.displayMahasiswaInfo(mahasiswa);
    }
}
