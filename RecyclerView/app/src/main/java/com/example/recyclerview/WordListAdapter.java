package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.databinding.ActivityMainBinding;
import com.example.recyclerview.databinding.WordlistItemBinding;

import java.util.HashMap;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

    private Context context;
    private List<String> words;
    ActivityMainBinding b;



    public WordListAdapter(Context context, List<String> words) {
        this.context = context;
        this.words = words;
    }


    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WordlistItemBinding wordlistItemBinding = WordlistItemBinding.inflate(LayoutInflater.from(context)
                , parent
                , false
        );

        //Create & return ViewHolder
        return new WordViewHolder(wordlistItemBinding , this);

    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        String word = words.get(position);

        //Bind the data with view
        holder.wordlistItemBinding.word.setText(word);

    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        WordlistItemBinding wordlistItemBinding;
        WordListAdapter mAdapter;
        public WordViewHolder(@NonNull WordlistItemBinding w, WordListAdapter adapter) {
            super(w.getRoot());
            this.wordlistItemBinding=w;
            this.mAdapter = adapter;

            wordlistItemBinding.word.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            //Get position
            int mPosition = getLayoutPosition();

            //Data at position
            String element = words.get(mPosition);

            //Change the string (add prefix)
            words.set(mPosition, "Clicked  " + element);
            //Notify adapter
            mAdapter.notifyItemChanged(mPosition);
        }

    }


}
