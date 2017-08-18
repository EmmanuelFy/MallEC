package com.prcymy.mall.event;

import android.util.Log;
import android.widget.Toast;

import com.prcymy.ymy.delegates.web.event.Event;

/**
 * Created by Administrator on 2017/8/17.
 */

public class TestEvent extends Event {

    @Override
    public String execute(String parms) {
        Log.d("-------", "execute: "+getAction());
        Toast.makeText(getContext(), getAction(), Toast.LENGTH_LONG).show();
        return null;
    }
}
