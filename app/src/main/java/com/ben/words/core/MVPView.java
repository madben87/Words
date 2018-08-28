package com.ben.words.core;

import android.app.Activity;

import com.ben.words.util.MessageEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public interface MVPView {

    void moveToScreenWithoutBack(Class<? extends Activity> cls);
    void moveToScreenWithBack(Class<? extends Activity> cls);
    void showMessage(String str);
    void eventBusListener(MessageEvent event);
}
