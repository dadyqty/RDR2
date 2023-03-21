package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class TalkingActivity extends BasicActivity {

    private List<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talking);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String sex = intent.getStringExtra("sex");
        int chara_photo = intent.getIntExtra("chara_photo",R.drawable.athor);
        TextView textView = (TextView)findViewById(R.id.chat_name);
        EditText send_message = (EditText) findViewById(R.id.send_message);
        textView.setText("与"+name+"交谈");
        init_messages(name);
        RecyclerView recyclerView = findViewById(R.id.chat_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        ChatAdapter adapter = new ChatAdapter(messages,sex,chara_photo, this);
        recyclerView.setAdapter(adapter);

        Button send_button = (Button) findViewById(R.id.send_button);
        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String context = send_message.getText().toString();
                messages.add(new Message(context,true));
                adapter.notifyItemInserted(messages.size()-1);
                send_message.setText("");
                messages.add(new Message("我还没学会怎么交谈",false));
                adapter.notifyItemInserted(messages.size()-1);
                recyclerView.scrollToPosition(messages.size()-1);
            }
        });
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //隐藏键盘
                hideSoftInput(TalkingActivity.this, send_message);
                return false;
            }
        });
    }

    public static void hideSoftInput(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

    }



    private void init_messages(String name)
    {
        messages.add(new Message("Hello! My name is "+name+"\n Let's Chat!", false));
    }
}
