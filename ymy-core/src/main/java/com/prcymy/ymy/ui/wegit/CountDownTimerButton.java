package com.prcymy.ymy.ui.wegit;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/9/8.
 */

public class CountDownTimerButton extends AppCompatButton implements View.OnClickListener{

    private Context context;
    private OnClickListener onClickListener;
    private Timer timer;
    private TimerTask timerTask;
    private long duration = 60000;   //总长时间
    private long temp_duration;
    private String clickBefor = "获取验证码";
    private String clickAfter = "s";

    public CountDownTimerButton(Context context) {
        super(context);
        this.context =context;
        setOnClickListener(this);
    }

    public CountDownTimerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context =context;
        setOnClickListener(this);
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            CountDownTimerButton.this.setText(temp_duration/1000+clickAfter);
            temp_duration -= 1000;
            if (temp_duration < 0){ //倒计时结束
                CountDownTimerButton.this.setEnabled(true);
                CountDownTimerButton.this.setText(clickBefor);
                stopTimer();
            }
        }
    };


    public void setOnClickListener(OnClickListener onClickListener) {
        if (onClickListener instanceof CountDownTimerButton) {
            super.setOnClickListener(onClickListener);
        }else{
            this.onClickListener = onClickListener;
        }

    }

    @Override
    public void onClick(View view) {
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
        startTimer();
    }

    //计时开始
    private void startTimer(){
        temp_duration = duration;
        CountDownTimerButton.this.setEnabled(false);
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x01);
            }
        };
        timer.schedule(timerTask, 0, 1000);//调度分配，延迟0秒，时间间隔为1秒
    }

    //计时结束
    private void stopTimer(){
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }
}
