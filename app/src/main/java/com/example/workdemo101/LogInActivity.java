package com.example.workdemo101;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        TextView btn = findViewById(R.id.TextViewSignup);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInActivity.this,RegisterActivity.class));
            }
        });

        TextView Email = findViewById(R.id.Email1);
        TextView Password = findViewById(R.id.Password1);
        Button loginbtn = findViewById(R.id.btnlogin);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String password = Password.getText().toString();

                if(email.equals("123@123.com") && password.equals("123123"))
                {
                    Toast.makeText(LogInActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), Chat.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(LogInActivity.this, "Please enter the correct password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

