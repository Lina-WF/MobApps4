package ru.mirea.ushakovaps.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.zip.Inflater;

import ru.mirea.ushakovaps.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int counter = 0;
    private int avg_classes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread mainThread = Thread.currentThread();
        binding.textView.setText("Имя текущего потока: " + mainThread.getName());

        mainThread.setName("Мой номер группы: БСБО-09-21; номер по списку: 23; мой любимый фильм: Кома (2020г.)");
        binding.textView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));

        binding.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Runnable Avg_Count = new Runnable() {
                    public void run() {
                        binding.textView.append("\nСреднее значение количества пар составляет " + avg_classes);
                    }
                };

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int classes_count = Integer.parseInt(String.valueOf(binding.editClasses.getText()));
                        int days_count = Integer.parseInt(String.valueOf(binding.editDays.getText()));
                        avg_classes = (int) Math.round((double)classes_count/days_count);
                        int numberThread = counter++;
                        Log.d("ThreadProject", String.format("Запущен поток № %d студентом группы № %s номер по списку № %d", numberThread, "БСБО-09-21", 23));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime){
                            synchronized(this){
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                    binding.textView.post(Avg_Count);
                                    Log.d(MainActivity.class.getSimpleName(),"Endtime: " + endTime);
                                } catch(Exception e){
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток № " + numberThread);
                        }
                    }
                }).start();
            }
        });
    }
}