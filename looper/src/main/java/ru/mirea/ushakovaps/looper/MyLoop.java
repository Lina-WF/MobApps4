package ru.mirea.ushakovaps.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLoop extends Thread{
    public Handler mHandler;
    private Handler mainHandler;
    public MyLoop(Handler mainThreadHandler) {
        mainHandler = mainThreadHandler;
    }
    public void run() {
        Log.d("MyLooper", "run");
        Looper.prepare();
        mHandler = new Handler(Looper.myLooper()) {
            public void handleMessage(Message msg) {
                String data = msg.getData().getString("job");
                Log.d("MyLooper get message: ", data);

                int count = data.length();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result", String.format("The number of letters in my job %s is %d ", data, count));
                message.setData(bundle);
                mainHandler.sendMessage(message);
            }
        };
        Looper.loop();
    }
}