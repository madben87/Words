package com.ben.words.core;

import android.app.Activity;

public interface MVPView {

    void moveToScreenWithoutBack(Class<? extends Activity> cls);
    void showMessage(String str);
}
