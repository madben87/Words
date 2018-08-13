package com.ben.words.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.ben.words.R;
import com.ben.words.core.App;
import com.ben.words.ui.add_new.AddWordActivity;
import com.ben.words.ui.main.adapter.MainPagerAdapter;
import com.ben.words.ui.words_list.WordsListFragment;
import com.ben.words.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;
import javax.inject.Singleton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.toolbar_actionbar)
    Toolbar toolbar;
    @BindView(R.id.mainViewPager)
    ViewPager mainViewPager;

    @Inject
    MainPresenter presenter;

    private MainPagerAdapter pagerAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        App.getScreenInjector().inject(this);

        presenter.attachPresenter(this);

        EventBus.getDefault().register(this);

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new WordsListFragment(), "Words list");
        mainViewPager.setAdapter(pagerAdapter);
    }

    @OnClick(R.id.fab)
    public void click(View view) {
        presenter.addNewWord();
        //presenter.test();
    }

    @Override
    public void moveToScreenWithoutBack(Class<? extends Activity> cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showMessage(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void eventBusListener(MessageEvent event) {
        switch (event.msg) {
            case MessageEvent.ADD_NEW_ITEM:
                showMessage("Word is added");
                break;
            case MessageEvent.ADD_NEW_ITEM_IS_ERROR:
                showMessage("ERROR: Word is not added");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachPresenter();
        EventBus.getDefault().unregister(this);
    }
}
