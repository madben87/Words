package com.ben.words.ui.add_new_word;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ben.words.R;
import com.ben.words.core.App;
import com.ben.words.data.model.PartOfSpeech;
import com.ben.words.data.model.Translate;
import com.ben.words.data.model.Word;
import com.ben.words.ui.main.MainActivity;
import com.ben.words.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddWordActivity extends AppCompatActivity implements AddWordView {

    private static final int DIALOG = 1;

    /*@BindView(R.id.fieldPartsOfSpeech)
    EditText fieldPartsOfSpeech;*/
    @BindView(R.id.fieldWord)
    EditText fieldWord;
    @BindView(R.id.fieldTranscription)
    EditText fieldTranscription;
    @BindView(R.id.fieldTranslate)
    TextView fieldTranslate;
    @BindView(R.id.spinner)
    Spinner spinner;

    private EditText dialogAddTranslate;

    @Inject
    public AddWordPresenter presenter;

    private Word inputWord;
    private String partOfSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        ButterKnife.bind(this);
        App.getScreenInjector().inject(this);
        presenter.attachPresenter(this);

        inputWord = new Word();

        final ArrayAdapter<PartOfSpeech> adapter = new ArrayAdapter<>(this, R.layout.spinner_row, R.id.part_row, PartOfSpeech.values());

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapter.getItem(i) != null) {
                    partOfSpeech = Objects.requireNonNull(adapter.getItem(i)).getValue();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.btnAddTranslate, R.id.btnSaveWord})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.btnAddTranslate:
                showDialog(DIALOG);
                break;
            case R.id.btnSaveWord:
                if (fieldsValidation()) {
                    //inputWord.setPartsOfSpeech(fieldPartsOfSpeech.getText().toString());
                    inputWord.setPartsOfSpeech(partOfSpeech);
                    inputWord.setValue(fieldWord.getText().toString());
                    if (!fieldTranscription.getText().toString().isEmpty()) {
                        inputWord.setTranscription(fieldTranscription.getText().toString());
                    }
                    presenter.addNewItem(inputWord);
                    moveToScreenWithoutBack(MainActivity.class);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        inputWord = null;
        presenter.detachPresenter();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Custom dialog");

        View view = getLayoutInflater()
                .inflate(R.layout.add_translate_dialog, null);

        adb.setView(view);

        dialogAddTranslate = (EditText) view.findViewById(R.id.dialogAddTranslate);

        CardView dialogBtnSave = (CardView) view.findViewById(R.id.dialogBtnSave);

        dialogBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogAddTranslate.getText().toString().isEmpty()) {
                    showMessage("Please add translate");
                }else {
                    Translate translate = new Translate();
                    translate.setValue(dialogAddTranslate.getText().toString());
                    inputWord.addTranslate(translate);
                    updateTranslates();
                    dialogAddTranslate.setText("");
                    dismissDialog(DIALOG);
                }
            }
        });

        return adb.create();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
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
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusListener(MessageEvent event) {
        switch (event.msg) {
            case MessageEvent.ADD_NEW_ITEM:
                showMessage("Word is added");
                moveToScreenWithoutBack(MainActivity.class);
                break;
            case MessageEvent.ADD_NEW_ITEM_IS_ERROR:
                showMessage("ERROR: Word is not added");
                break;
        }
    }

    private boolean fieldsValidation() {
        /*if (fieldPartsOfSpeech.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please add part of speech", Toast.LENGTH_SHORT).show();
            return false;
        }*/

        if (partOfSpeech.isEmpty()) {
            Toast.makeText(this, "Please add part of speech", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (fieldWord.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please add value", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (fieldTranslate.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please add translation", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateTranslates() {
        if (inputWord != null && inputWord.getTranslates() != null && inputWord.getTranslates().size() > 0) {
            fieldTranslate.setText(inputWord.getTranslateValue());
        }
    }
}
