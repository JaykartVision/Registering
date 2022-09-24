package com.jvline.registering;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class WindowEnter extends AppCompatActivity
{
    private Button buttonName;
    private TextView myName1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.window_enter);
        buttonName = findViewById(R.id.button_name);
        String name = getIntent().getStringExtra("name");
        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                window_dialog(name);
            }
        };
        buttonName.setOnClickListener(onClickListener);
    }
    private void window_dialog(String num)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Приветствие");
        builder.setMessage(num);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}