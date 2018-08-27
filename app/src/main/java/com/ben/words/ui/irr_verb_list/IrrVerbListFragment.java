package com.ben.words.ui.irr_verb_list;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ben.words.R;
import com.ben.words.core.App;
import com.ben.words.data.model.IrregularVerb;
import com.ben.words.ui.irr_verb_list.adapter.IrrVerbListAdapter;
import com.ben.words.util.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IrrVerbListFragment extends Fragment implements IrrVerbListView {

    @BindView(R.id.irrVerbList)
    RecyclerView irrVerbList;

    @Inject
    IrrVerbListPresenter presenter;

    private Unbinder unbinder;
    private IrrVerbListAdapter listAdapter;

    public IrrVerbListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_irr_verb_list, container, false);

        unbinder = ButterKnife.bind(this, view);

        App.getScreenInjector().inject(this);

        presenter.attachPresenter(this);

        listAdapter = new IrrVerbListAdapter();

        irrVerbList.setHasFixedSize(true);
        irrVerbList.setLayoutManager(new LinearLayoutManager(getContext()));
        irrVerbList.setAdapter(listAdapter);

        EventBus.getDefault().register(this);

        presenter.updateList();

        return view;
    }

    @Override
    public void updateList(List<IrregularVerb> list) {
        listAdapter.addList(list);
    }

    @Override
    public void moveToScreenWithoutBack(Class<? extends Activity> cls) {

    }

    @Override
    public void showMessage(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventBusListener(MessageEvent event) {
        switch (event.msg) {
            case MessageEvent.ADD_NEW_ITEM:
                showMessage("Verb is added");
                listAdapter.notifyDataSetChanged();
                break;
            case MessageEvent.ADD_NEW_ITEM_IS_ERROR:
                showMessage("ERROR: Verb is not added");
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.detachPresenter();
        EventBus.getDefault().unregister(this);
    }
}
