package com.ben.words.ui.words_list.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ben.words.R;
import com.ben.words.core.ItemClick;
import com.ben.words.data.model.Word;

import java.util.ArrayList;
import java.util.List;

public class WordsListAdapter extends RecyclerView.Adapter<WordHolder> {

    private List<Word> wordList;
    private boolean detailMode;

    public WordsListAdapter() {
        wordList = new ArrayList<>();
    }

    public void addList(List<Word> list) {
        this.wordList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WordHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.word_list_item, viewGroup, false);
        return new WordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WordHolder holder, final int i) {

        Word word = wordList.get(i);

        holder.translateCard.setVisibility(View.GONE);
        holder.transcriptCard.setVisibility(View.GONE);

        holder.partOfSpeechCard.setText(word.getPartsOfSpeech());
        holder.wordCard.setText(word.getValue());
        holder.translateCard.setText(word.getTranslateValue());
        holder.transcriptCard.setText(word.getTranscription());

        /*holder.cardListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!detailMode) {
                    holder.translateCard.setVisibility(View.VISIBLE);
                    holder.transcriptCard.setVisibility(View.VISIBLE);
                    detailMode = true;
                }else {
                    holder.translateCard.setVisibility(View.GONE);
                    holder.transcriptCard.setVisibility(View.GONE);
                    detailMode = false;
                }
            }
        });*/

        holder.setOnItemClickListener(new ItemClick() {
            @Override
            public void onItemClick(View view, int position) {
                if (!detailMode) {
                    holder.translateCard.setVisibility(View.VISIBLE);
                    holder.transcriptCard.setVisibility(View.VISIBLE);
                    detailMode = true;
                }else {
                    holder.translateCard.setVisibility(View.GONE);
                    holder.transcriptCard.setVisibility(View.GONE);
                    detailMode = false;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
