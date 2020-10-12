package com.example.ifstudentportal.Model;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.ifstudentportal.Presenter.LoginPresenter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;


public class Scrapper {
    private String phpSessId;
    private String ciSession;
    private Mahasiswa mahasiswa;
    protected Context context;
    protected LoginPresenter loginPresenter;

    private final String BASE_URL = "https://studentportal.unpar.ac.id/";
    private final String LOGIN_URL = BASE_URL + "C_home/sso_login";
    private final String SSO_URL = "https://sso.unpar.ac.id/login";
    private final String JADWAL_URL = BASE_URL + "jadwal/kuliah";
    private final String NILAI_URL = BASE_URL + "nilai";
    private final String TOEFL_URL = BASE_URL + "nilai/toefl";
    private final String LOGOUT_URL = BASE_URL + "logout";
    private final String HOME_URL = BASE_URL + "home";

    public Scrapper(Context context, LoginPresenter loginPresenter) {
        this.context = context;
        this.loginPresenter = loginPresenter;
        this.mahasiswa  = new Mahasiswa();
    }


    public void login(String email, String npm) {
        new Login().execute(email, npm);
    }

    public void getMahasiswaInfo(String phpSessId) {
        new GetMahasiswaInfo().execute(phpSessId);
    }

    private class Login extends AsyncTask<String, String, Wrapper> {
        public void init() throws IOException {
            Connection baseConn = Jsoup.connect(BASE_URL);
            baseConn.timeout(0);
            baseConn.validateTLSCertificates(false);
            baseConn.method(Connection.Method.GET);
            baseConn.execute();
            Log.d("init", "init");
        }

        @Override
        protected Wrapper doInBackground(String... params) {
            String result = "";
            Wrapper wrapper = null;
            mahasiswa.setEmail(params[0]);
            mahasiswa.setNpm(params[0].substring(0,10));
            try {
                //init();

//                String user = logged_mhs.getEmailAddress();
                Connection.Response resp = Jsoup.connect(LOGIN_URL).data("Submit", "Login").method(Connection.Method.POST).execute();
                Document doc = resp.parse();
                Log.d("doc", "test log");
                String lt = doc.select("input[name=lt]").val();
                String execution = doc.select("input[name=execution]").val();
                String jsessionid = resp.cookie("JSESSIONID");
                /* SSO LOGIN */
                Connection loginConn = Jsoup.connect(SSO_URL + ";jsessionid=" + jsessionid + "?service=" + LOGIN_URL);
                loginConn.cookies(resp.cookies());
                loginConn.data("username", params[0]);
                loginConn.data("password", params[1]);
                loginConn.data("lt", lt);
                loginConn.data("execution", execution);
                loginConn.data("_eventId", "submit");
                loginConn.data("submit", "");
                loginConn.validateTLSCertificates(false);
                loginConn.method(Connection.Method.POST);
                resp = loginConn.execute();
                int respCode = resp.statusCode();
                Log.d("status",respCode+"");
                //iLoginActivity.sendInfo(resp.statusCode()+"");
                if (resp.body().contains(params[0])) {
                    Map<String, String> phpsessid = resp.cookies();
                    result = phpsessid.get("ci_session");
                    phpSessId = result;
                }
                wrapper = new Wrapper(result, respCode, mahasiswa);

//
            } catch (IOException e) {
                e.printStackTrace();
            }

            return wrapper;
        }


        @Override
        protected void onPostExecute(Wrapper wrapper) {
            super.onPostExecute(wrapper);
            //Log.d("s", phpSessId);
            loginPresenter.switchToHomeActivity(wrapper);
        }
    }

    private class GetMahasiswaInfo extends AsyncTask<String,String,Mahasiswa>{

        @Override
        protected Mahasiswa doInBackground(String... params) {
            try{
                Connection.Response resp = Jsoup.connect(HOME_URL).cookie("ci_session", params[0]).method(Connection.Method.GET).execute();
                Document doc = resp.parse();
                Log.d("login", "login");
                String nama = doc.select("div[class=namaUser d-none d-lg-block mr-3]").text();
                mahasiswa.setNama(nama);        //.substring(0, nama.indexOf(mahasiswa.getEmailAddress())));
            }catch (IOException e){
                e.printStackTrace();
            }
            return mahasiswa;
        }

        @Override
        protected void onPostExecute(Mahasiswa mahasiswa) {
            super.onPostExecute(mahasiswa);
            Log.d("mahasiswa",mahasiswa.getEmail()+" "+mahasiswa.getNama());
            loginPresenter.displayMahasiswaInfo(mahasiswa);
        }
    }




}
