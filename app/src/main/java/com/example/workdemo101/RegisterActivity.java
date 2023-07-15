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
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        TextView btn = findViewById(R.id.already);

        TextView Username = findViewById(R.id.username);
        TextView Email = findViewById(R.id.Email);
        TextView Password = findViewById(R.id.Password);
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
                        String sqlinsert = "Insert into UserInfo values('"+Username.getText().toString()+"', '"+Email.getText().toString()+"', '"+Password.getText().toString()+"')";
                        Statement st=connection.createStatement();
                        ResultSet rs = st.executeQuery(sqlinsert);
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
        String ip="192.168.0.104", port="50308" , username="WorkDemo", password="123" , databasename="WrokDemo" ;
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