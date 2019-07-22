package com.example.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton send;
    EditText message_input;
    LinearLayout linear;
    CardView writing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        setTitle("Чат");
        writing.setVisibility(View.GONE);

        message_input.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);

        message_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (message_input.getText().length() == 150) {
                    message_input.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                } else {
                    message_input.getBackground().setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View mes = getLayoutInflater().inflate(R.layout.message, null);
                TextView user_text = mes.findViewById(R.id.mes_text);
                if (message_input.getText().length() != 0){
                    user_text.setText(message_input.getText().toString());
                    message_input.setText("");
                    linear.addView(mes, 300, user_text.getMaxHeight());
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Пустое сообщение")
                            .setMessage("Вы ничего не ввели... Введите сообщение и попробуйте снова!")
                            .setCancelable(true)
                            .setOnCancelListener(new DialogInterface.OnCancelListener() {
                                public void onCancel(DialogInterface dialog) {
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("Я попытаюсь",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                final View answ = getLayoutInflater().inflate(R.layout.answer, null);
                final TextView bot_answer = answ.findViewById(R.id.mes_answ);
                if (user_text.getText().toString().contains("Найди")){

                }
                if (user_text.getText().toString().contains("Привет")){
                    writing.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bot_answer.setText("Привет");
                            linear.addView(answ);
                            writing.setVisibility(View.GONE);
                        }
                    }, 2000);
                } else if (user_text.getText().toString().contains("Как дел")){
                    writing.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bot_answer.setText("Отлично, а ты как?");
                            linear.addView(answ);
                            writing.setVisibility(View.GONE);
                        }
                    }, 2000);
                } else if (user_text.getText().equals("Что дел")){
                    writing.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bot_answer.setText("Можно сказать ничего! А ты?");
                            linear.addView(answ);
                            writing.setVisibility(View.GONE);
                        }
                    }, 2000);
                } else if (user_text.getText().equals("")){
                    //Хранить пустым
                } else {
                    writing.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bot_answer.setText("Я тебя не понял... Прости");
                            linear.addView(answ);
                            writing.setVisibility(View.GONE);
                        }
                    }, 2000);
                }
            }
        });
    }

    public void initData(){
        send = findViewById(R.id.send);
        message_input = findViewById(R.id.message_input);
        linear = findViewById(R.id.linear);
        writing = findViewById(R.id.writing);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.delete:
                linear.removeAllViews();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
