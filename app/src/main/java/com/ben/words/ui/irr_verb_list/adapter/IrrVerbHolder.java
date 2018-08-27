package com.ben.words.ui.irr_verb_list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.ben.words.R;
import com.ben.words.core.ItemClick;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IrrVerbHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {

    @BindView(R.id.irrVerbListItem)
    CardView irrVerbListItem;
    @BindView(R.id.labelForTranslate)
    TextView labelForTranslate;
    @BindView(R.id.translateIrrCard)
    TextView translateIrrCard;
    @BindView(R.id.labelForFirst)
    TextView labelForFirst;
    @BindView(R.id.firstFormCard)
    TextView firstFormCard;
    @BindView(R.id.labelForSecond)
    TextView labelForSecond;
    @BindView(R.id.secondFormCard)
    TextView secondFormCard;
    @BindView(R.id.labelForThird)
    TextView labelForThird;
    @BindView(R.id.thirdFormCard)
    TextView thirdFormCard;

    private ItemClick itemClick;

    public IrrVerbHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onClick(View view) {
        itemClick.onItemClick(view, this.getLayoutPosition());
    }

    public void setOnItemClickListener(ItemClick listener) {
        this.itemClick = listener;
    }
}
