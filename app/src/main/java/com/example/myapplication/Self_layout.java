package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class Self_layout extends LinearLayout {

    public Self_layout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
/*        Button bt0 = (Button) findViewById(R.id.titleBar_left_btn);
        bt0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity)getContext()).finish();
            }
        });*/
        Button bt1 = (Button) findViewById(R.id.titleBar_right_btn);
        bt1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Quit");
                dialog.setMessage("确定退出吗？");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Activity_contorller.end_the_application();
                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(context, "Canceled!", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });
    }
}

