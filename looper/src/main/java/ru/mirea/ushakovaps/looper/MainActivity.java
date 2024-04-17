package ru.mirea.ushakovaps.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.view.View;
import android.os.Message;
import android.util.Log;

import ru.mirea.ushakovaps.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: " + msg.getData().getString("result"));
            }
        };
        MyLoop myLooper = new MyLoop(mainThreadHandler);
        myLooper.start();
        binding.editText.setText("Мой номер по списку №23");
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain();
                Bundle bundle = new Bundle();
                int age = Integer.parseInt(String.valueOf(binding.editAge.getText()));
                String job = String.valueOf(binding.editJob.getText());
                bundle.putString("job", job);
                msg.setData(bundle);
                myLooper.mHandler.sendMessageDelayed(msg, age * 1000);
            }
        });
    }
}