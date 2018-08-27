package com.ben.words.ui.irr_verb_list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ben.words.R;
import com.ben.words.core.ItemClick;
import com.ben.words.data.model.IrregularVerb;

import java.util.ArrayList;
import java.util.List;

public class IrrVerbListAdapter extends RecyclerView.Adapter<IrrVerbHolder> {

    private List<IrregularVerb> list;
    private boolean detailMode;

    public IrrVerbListAdapter() {
        this.list = new ArrayList<>();
    }

    public void addList(List<IrregularVerb> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IrrVerbHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.irr_verb_list_item, viewGroup, false);
        return new IrrVerbHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final IrrVerbHolder holder, int i) {

        IrregularVerb verb = list.get(i);

        holder.firstFormCard.setVisibility(View.GONE);
        holder.secondFormCard.setVisibility(View.GONE);
        holder.thirdFormCard.setVisibility(View.GONE);

        holder.labelForFirst.setVisibility(View.GONE);
        holder.labelForSecond.setVisibility(View.GONE);
        holder.labelForThird.setVisibility(View.GONE);

        holder.translateIrrCard.setText(verb.getTranslate().getValue());
        holder.firstFormCard.setText(verb.getFirstForm());
        holder.secondFormCard.setText(verb.getSecondForm());
        holder.thirdFormCard.setText(verb.getThirdForm());

        holder.setOnItemClickListener(new ItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                if (!detailMode) {
                    holder.firstFormCard.setVisibility(View.VISIBLE);
                    holder.secondFormCard.setVisibility(View.VISIBLE);
                    holder.thirdFormCard.setVisibility(View.VISIBLE);

                    holder.labelForFirst.setVisibility(View.VISIBLE);
                    holder.labelForSecond.setVisibility(View.VISIBLE);
                    holder.labelForThird.setVisibility(View.VISIBLE);
                    detailMode = true;
                }else {
                    holder.firstFormCard.setVisibility(View.GONE);
                    holder.secondFormCard.setVisibility(View.GONE);
                    holder.thirdFormCard.setVisibility(View.GONE);

                    holder.labelForFirst.setVisibility(View.GONE);
                    holder.labelForSecond.setVisibility(View.GONE);
                    holder.labelForThird.setVisibility(View.GONE);
                    detailMode = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
