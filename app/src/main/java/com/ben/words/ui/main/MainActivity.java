package com.ben.words.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ben.words.R;
import com.ben.words.core.App;
import com.ben.words.core.SharedManager;
import com.ben.words.data.model.PartOfSpeech;
import com.ben.words.service.DBSyncIntentService;
import com.ben.words.ui.irr_verb_list.IrrVerbListFragment;
import com.ben.words.ui.main.adapter.MainPagerAdapter;
import com.ben.words.ui.words_list.WordsListFragment;
import com.ben.words.util.MessageEvent;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView, NavigationView.OnNavigationItemSelectedListener {

    private static final int RC_SIGN_IN = 123;
    @BindView(R.id.toolbar_actionbar)
    Toolbar toolbar;
    @BindView(R.id.mainViewPager)
    ViewPager mainViewPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;

    private TextView userName;
    private TextView userEmail;
    private ImageView userAvatar;

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

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new WordsListFragment(), "Words list");
        pagerAdapter.addFragment(new IrrVerbListFragment(), "Irregular Verbs");
        mainViewPager.setAdapter(pagerAdapter);
        mainViewPager.setCurrentItem(App.getAppInstance().getMainPageState());

        mainViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                App.getAppInstance().setMainPageState(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);
        userName = (TextView) header.findViewById(R.id.userName);
        userEmail = (TextView) header.findViewById(R.id.userEmail);
        userAvatar = (ImageView) header.findViewById(R.id.userAvatar);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            userName.setText(auth.getCurrentUser().getDisplayName());
            userEmail.setText(auth.getCurrentUser().getEmail());
        } else {
            // not signed in
        }

        //Intent intent = new Intent(this, DBSyncIntentService.class);
        //startService(intent);
    }

    @OnClick(R.id.fab)
    public void click(View view) {
        presenter.addNewItem(mainViewPager.getCurrentItem());
    }

    @Override
    public void onBackPressed() {
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void moveToScreenWithoutBack(Class<? extends Activity> cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void moveToScreenWithBack(Class<? extends Activity> cls) {
        Intent intent = new Intent(this, cls);
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
                //showMessage("Word is added");
                break;
            case MessageEvent.ADD_NEW_ITEM_IS_ERROR:
                //showMessage("ERROR: Word is not added");
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    userName.setText(user.getDisplayName());
                    userEmail.setText(user.getEmail());
                }
                // ...
            } else {
                showMessage(String.valueOf(requestCode));
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            showMessage("Sign Out complete");
                        }
                    });
        } else if (id == R.id.nav_login) {

            List<AuthUI.IdpConfig> providers = Arrays.asList(
                    new AuthUI.IdpConfig.EmailBuilder().build(),
                    new AuthUI.IdpConfig.GoogleBuilder().build(),
                    new AuthUI.IdpConfig.FacebookBuilder().build());

            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .build(),
                    RC_SIGN_IN);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getAppInstance().setMainPageState(mainViewPager.getCurrentItem());
        presenter.detachPresenter();
        EventBus.getDefault().unregister(this);
        Intent intent = new Intent(this, DBSyncIntentService.class);
        startService(intent);
    }

    enum Action {

        WORD(0), VERB(1);

        private int action;

        Action(int action) {
            this.action = action;
        }

        public int getAction() {
            return action;
        }
    }
}
