package com.example.workdemo101;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


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
            public void onClick(View view)
            {
                String email = Email.getText().toString();
                String pass = Password.getText().toString();
                Connection connection = connection();

                if(TextUtils.isEmpty(email))
                {
                    Email.setError("Email is Required!");
                    return;
                }

                if(TextUtils.isEmpty(pass))
                {
                    Password.setError("Password is Required!");
                    return;
                }

                try {
                    if (connection != null)
                    {
                        String sql="select * from UserInfo where Email = '" + Email.getText().toString() + "'";
                        Statement st=connection.createStatement();
                        ResultSet rs = st.executeQuery(sql);

                        while(rs.next())
                        {
                            if (email.equals(rs.getString("Email")) && pass.equals(rs.getString("Password")))
                            {
                                startActivity(new Intent(LogInActivity.this,Chat.class));
                            }
                            else
                            {
                                Toast.makeText(LogInActivity.this, "Invalid", Toast.LENGTH_LONG);
                            }
                        }
                    }
                }
                catch (Exception exception){

                }

            }
        });
}
    @SuppressLint("NewApi")
    public Connection connection(){
        Connection conn = null;
        String ip="10.162.55.120", port="50308" , username="WorkDemo", password="123" , databasename="WrokDemo" ;
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


