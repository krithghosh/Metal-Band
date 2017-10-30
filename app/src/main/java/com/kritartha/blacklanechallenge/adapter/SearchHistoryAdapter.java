package com.kritartha.blacklanechallenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kritartha.blacklanechallenge.R;
import com.kritartha.blacklanechallenge.model.bandSearch.SearchResult;

import java.util.List;

/**
 * Created by kritarthaghosh on 30/10/17.
 */

public class SearchHistoryAdapter extends RecyclerView.Adapter<SearchHistoryAdapter.SearchHistoryViewHolder> {

    private Context context;
    private List<SearchResult> mSearchResult;
    private SearchHistoryAdapter.OnItemClickListener mItemClickListener;

    public SearchHistoryAdapter(Context context, List<SearchResult> mSearchResult) {
        this.context = context;
        this.mSearchResult = mSearchResult;
    }

    @Override
    public SearchHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_card, parent, false);
        return new SearchHistoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchHistoryViewHolder holder, int position) {
        SearchResult item = mSearchResult.get(position);
        holder.tvBandName.setText(item.getName());
        holder.tvGenre.setText(item.getGenre());
        holder.tvCountry.setText(item.getCountry());
    }

    public void updateList(List<SearchResult> mSearchResult) {
        this.mSearchResult = mSearchResult;
        notifyDataSetChanged();
    }

    public void clearList() {
        if (this.mSearchResult != null) {
            this.mSearchResult.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        if (mSearchResult == null || mSearchResult.size() == 0)
            return 0;
        return mSearchResult.size();
    }

    public interface OnItemClickListener {
        void onItemClick(SearchResult item);
    }

    public void setOnItemClickListener(final SearchHistoryAdapter
            .OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class SearchHistoryViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView tvBandName;
        public TextView tvGenre;
        public TextView tvCountry;

        public SearchHistoryViewHolder(View itemView) {
            super(itemView);
            tvBandName = (TextView) itemView.findViewById(R.id.tv_name);
            tvGenre = (TextView) itemView.findViewById(R.id.tv_genre);
            tvCountry = (TextView) itemView.findViewById(R.id.tv_country);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            SearchResult item = mSearchResult.get(getAdapterPosition());
            mItemClickListener.onItemClick(item);
        }
    }
}
