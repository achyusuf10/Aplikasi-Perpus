package com.example.crudmysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private EditText etUsername,etPwd;
    private String usr,pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btn_login);
        etUsername = findViewById(R.id.et_usrname);
        etPwd = findViewById(R.id.et_pwd);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usr = etUsername.getText().toString();
                pwd = etPwd.getText().toString();
                if(usr.trim().equals("")) {
                    etUsername.setError("Nama harus diisi");
                }else if(pwd.trim().equals("")){
                    etPwd.setError("Password harus diisi");
                }else{
                    if(usr.equals("admin")&&pwd.equals("1234")){
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Username atau Password Salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}