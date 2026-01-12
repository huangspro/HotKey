package com.example.hotkey;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText I;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main);
        I=findViewById(R.id.i);
    }

    public void input(View v) {
        if(I.getText().toString().equals(""))return;
        new Thread(() -> {
            try {
                Socket socket = new Socket("10.21.148.185", 1234);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                out.println(I.getText().toString());
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}