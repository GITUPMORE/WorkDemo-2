package com.example.workdemo101;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView btn = findViewById(R.id.already);

        TextView name = findViewById(R.id.username);
        TextView email = findViewById(R.id.Email);
        TextView Pass = findViewById(R.id.Password);
        Button registerbtn = findViewById(R.id.btnofResigter);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LogInActivity.class));
            }
        });

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connection connection= connection();

                try {
                    if (connection != null)
                    {
                        String sqlinsert = "Insert into UserInfo_Tab values('"+name.getText().toString()+"', '"+email.getText().toString()+"', '"+Pass.getText().toString()+"')";
                    }
                }

                catch (Exception exception)
                {
                    Log.e("Error", exception.getMessage());
                }
            }
        });
    }
    @SuppressLint("NewApi")
    public Connection connection(){
        Connection conn = null;
        String ip="10.162.55.120", port="50068" , username="Demo", password="koria", databasename="WorkDemo";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionurl = "jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+databasename+";User="+username+";password="+password+";";
            conn = DriverManager.getConnection(connectionurl);
        }
        catch (Exception exception)
        {
            Log.e("error", exception.getMessage());
        }
        return conn;
    }
}