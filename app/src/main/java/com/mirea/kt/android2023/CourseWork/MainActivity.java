package com.mirea.kt.android2023.CourseWork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener( v -> {
            EditText etLogin = findViewById(R.id.etLogin);
            EditText etPassword = findViewById(R.id.etPassword);
            TextView tvMessage = findViewById(R.id.tvMessage);

            etLogin.setText("Student61399");
            etPassword.setText("ZYfm4fN");

            HashMap<String, String> requestBody = new HashMap<>();
            requestBody.put("lgn", etLogin.getText().toString());
            requestBody.put("pwd", etPassword.getText().toString());
            requestBody.put("g", "RIBO-04-21");

            HTTPPOSTRunnable HTTPPOSTRunnable = new HTTPPOSTRunnable
                    ("https://android-for-students.ru/coursework/login.php", requestBody);
            Thread th = new Thread(HTTPPOSTRunnable);
            th.start();
            try {
                th.join();
                System.out.println("response: " + HTTPPOSTRunnable.getResponseBody());

            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            try {
                JSONObject jsonResponse = new JSONObject(HTTPPOSTRunnable.getResponseBody());
                int resultCode = jsonResponse.getInt("result_code");
                if(resultCode == 1) {
                    tvMessage.setText("OK!");
                    Weather weather = new Weather(55.75, 37.62);
                }else{
                    tvMessage.setText("Неверный логин или пароль");
                }

            }catch (Exception e){
                tvMessage.setText("Ошибка в жсоне: " + e.getStackTrace() );
            }

        });
    }
}