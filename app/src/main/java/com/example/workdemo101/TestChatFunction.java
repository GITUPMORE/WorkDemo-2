package com.example.workdemo101;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TestChatFunction extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView Chatgpt;
    EditText EditText;
    ImageButton SendBtn;
    List<ChatMessage> MessageList;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_chat_function);
        MessageList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        Chatgpt = findViewById(R.id.Chatgpt);
        EditText = findViewById(R.id.EditText);
        SendBtn = findViewById(R.id.Ibtn);

        adapter = new Adapter(MessageList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager LinerLM = new LinearLayoutManager(this);
        LinerLM.setStackFromEnd(true);
        recyclerView.setLayoutManager(LinerLM);

        SendBtn.setOnClickListener((v)->{
        String question = EditText.getText().toString().trim();
        Chatting(question,ChatMessage.SENT_BY_ME);
        EditText.setText("");
        Chatgpt.setVisibility(View.GONE);
        });
    }

    void Chatting(String message, String sentby)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MessageList.add(new ChatMessage(message, sentby));
                adapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(adapter.getItemCount());
            }
        });
    }

}