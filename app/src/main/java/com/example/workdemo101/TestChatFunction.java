package com.example.workdemo101;

import androidx.annotation.NonNull;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TestChatFunction extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView Chatgpt;
    EditText EditText;
    ImageButton SendBtn;
    List<ChatMessage> MessageList;
    Adapter adapter;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

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
        API(question);
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

    void Responce(String responce)
    {
        Chatting(responce,ChatMessage.SENT_BY_CHATGPT);
    }

    void API(String question)
    {
        JSONObject JSON1 = new JSONObject();
        try {
            JSON1.put("model" , "text-davinci-003");
            JSON1.put("prompt" , question);
            JSON1.put("max_tokens" , 4000);
            JSON1.put("temperature" , 0);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        RequestBody Rbody = RequestBody.create(JSON1.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.chatanywhere.cn/v1/completions")
                .header("Authorization" , "Bearer sk-PE9TZbeU9bTFHwZfuSuVisT7pIKIem91Zxbhp21iE4gty8Hi")
                .post(Rbody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Responce("Failed to load Response Because  "+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful())
                {
                    JSONObject JSONOb = null;
                    try {
                        JSONOb = new JSONObject(response.body().string());
                        JSONArray jsonArray = JSONOb.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        Responce(result.trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Responce("The Loading Response Failed Because "+response.body().string());
                }

            }
        });

    }
}