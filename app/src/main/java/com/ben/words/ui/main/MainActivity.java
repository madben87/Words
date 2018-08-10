package com.ben.words.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.ben.words.R;
import com.ben.words.core.App;
import com.ben.words.ui.add_new.AddWordActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.toolbar_actionbar)
    Toolbar toolbar;

    @Inject
    MainPresenter presenter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        App.getScreenInjector().inject(this);

        presenter.attachPresenter(this);
    }

    @OnClick(R.id.fab)
    public void click(View view) {
        //presenter.addNewWord();
        presenter.test();
    }

    @Override
    public void moveToScreenWithoutBack(Class<? extends Activity> cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showMessage(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachPresenter();
    }
}
