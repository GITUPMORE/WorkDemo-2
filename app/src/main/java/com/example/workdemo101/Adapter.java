package com.example.workdemo101;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Myview> {

    List<ChatMessage> messageList;
    public Adapter(List<ChatMessage> messageList)
    {
        this.messageList=messageList;
    }

    @NonNull
    @Override
    public Myview onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View chatv = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatui,null);
        Myview mview = new Myview(chatv);
        return mview;
    }

    @Override
    public void onBindViewHolder(@NonNull Myview holder, int position)
    {
        ChatMessage message = messageList.get(position);
        if(message.getSentby().equals(ChatMessage.SENT_BY_ME))
        {
            holder.LeftChatText.setVisibility(View.GONE);
            holder.RightChatText.setVisibility(View.VISIBLE);
            holder.RightText.setText(message.getMessage());
        }
        else
        {
            holder.RightChatText.setVisibility(View.GONE);
            holder.LeftChatText.setVisibility(View.VISIBLE);
            holder.LeftText.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class Myview extends RecyclerView.ViewHolder{
        LinearLayout LeftChatText;
        LinearLayout RightChatText;
        TextView LeftText, RightText;

        public Myview(@NonNull View itemView) {
            super(itemView);
            LeftChatText = itemView.findViewById(R.id.LeftChat);
            RightChatText = itemView.findViewById(R.id.RightChat);
            LeftText = itemView.findViewById(R.id.LeftChatText);
            RightText= itemView.findViewById(R.id.RightChatText);
        }
    }
}
