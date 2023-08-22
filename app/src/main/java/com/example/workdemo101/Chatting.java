package com.example.workdemo101;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Chatting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        TextView ID = findViewById(R.id.userid);
        TextView Gender = findViewById(R.id.Gender);
        TextView Birth = findViewById(R.id.Birth);
        Button registerbtn = findViewById(R.id.btnofResigter);
    }

    @SuppressLint("NewApi")
    public Connection connection() {
        Connection conn = null;
        String ip = "192.168.0.104", port = "50308", username = "WorkDemo", password = "123", databasename = "WrokDemo";
        StrictMode.ThreadPolicy tp = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(tp);
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String connectionurl = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";databasename=" + databasename + ";User=" + username + ";password=" + password + ";";
            conn = DriverManager.getConnection(connectionurl);
        } catch (Exception exception) {
            Log.e("error", exception.getMessage());
        }
        return conn;
    }
}