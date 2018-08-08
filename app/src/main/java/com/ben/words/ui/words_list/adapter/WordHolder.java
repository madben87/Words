package com.ben.words.ui.words_list.adapter;

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

public class WordHolder extends RecyclerView.ViewHolder implements AdapterView.OnClickListener {

    @BindView(R.id.cardListItem)
    CardView cardListItem;
    @BindView(R.id.partOfSpeechCard)
    TextView partOfSpeechCard;
    @BindView(R.id.wordCard)
    TextView wordCard;
    @BindView(R.id.translateCard)
    TextView translateCard;
    @BindView(R.id.transcriptCard)
    TextView transcriptCard;

    private ItemClick itemClick;

    public WordHolder(@NonNull View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        cardListItem.setPreventCornerOverlap(false);
        cardListItem.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        itemClick.onItemClick(view, this.getLayoutPosition());
    }

    public void setOnItemClickListener(ItemClick listener) {
        this.itemClick = listener;
    }
}
