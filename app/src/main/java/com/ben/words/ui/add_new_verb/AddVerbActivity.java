package com.ben.words.ui.add_new_verb;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ben.words.R;
import com.ben.words.core.App;
import com.ben.words.data.model.IrregularVerb;
import com.ben.words.ui.main.MainActivity;
import com.ben.words.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddVerbActivity extends AppCompatActivity implements AddVerbView {

    @BindView(R.id.fieldVerbTranslate)
    EditText fieldVerbTranslate;
    @BindView(R.id.fieldFirstForm)
    EditText fieldFirstForm;
    @BindView(R.id.fieldSecondForm)
    EditText fieldSecondForm;
    @BindView(R.id.fieldThirdForm)
    EditText fieldThirdForm;

    @Inject
    AddVerbPresenter presenter;

    private IrregularVerb inputVerb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_verb);

        ButterKnife.bind(this);
        App.getScreenInjector().inject(this);
        presenter.attachPresenter(this);

        inputVerb = new IrregularVerb();

        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.btnSaveVerb})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btnSaveVerb:
                if (fieldsValidation()) {

                    presenter.addNewItem(inputVerb);
                    moveToScreenWithoutBack(MainActivity.class);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        inputVerb = null;
        presenter.detachPresenter();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void moveToScreenWithoutBack(Class<? extends Activity> cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showMessage(String str) {

    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusListener(MessageEvent event) {
        switch (event.msg) {
            case MessageEvent.ADD_NEW_ITEM:
                showMessage("Verb is added");
                moveToScreenWithoutBack(MainActivity.class);
                break;
            case MessageEvent.ADD_NEW_ITEM_IS_ERROR:
                showMessage("ERROR: Verb is not added");
                break;
        }
    }

    private boolean fieldsValidation() {

        if (fieldVerbTranslate.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please add word", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (fieldFirstForm.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please add first form", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (fieldSecondForm.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please add second form", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (fieldThirdForm.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please add third form", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
