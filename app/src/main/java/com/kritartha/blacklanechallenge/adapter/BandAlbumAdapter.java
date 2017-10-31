package com.kritartha.blacklanechallenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kritartha.blacklanechallenge.R;
import com.kritartha.blacklanechallenge.model.bandDetail.Discography;

import java.util.List;

/**
 * Created by kritarthaghosh on 31/10/17.
 */

public class BandAlbumAdapter extends RecyclerView.Adapter<BandAlbumAdapter.BandAlbumViewHolder> {

    private Context context;
    private List<Discography> mAlbum;
    private BandAlbumAdapter.OnItemClickListener mItemClickListener;

    public BandAlbumAdapter(Context context, List<Discography> mAlbum) {
        this.context = context;
        this.mAlbum = mAlbum;
    }

    @Override
    public BandAlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_card, parent, false);
        return new BandAlbumAdapter.BandAlbumViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BandAlbumViewHolder holder, int position) {
        Discography item = mAlbum.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvType.setText(item.getType());
        holder.tvYear.setText(item.getYear());
    }

    public void updateList(List<Discography> mAlbum) {
        this.mAlbum = mAlbum;
        notifyDataSetChanged();
    }

    public void clearList() {
        if (this.mAlbum != null) {
            this.mAlbum.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        if (mAlbum == null || mAlbum.size() == 0)
            return 0;
        return mAlbum.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Discography item);
    }

    public void setOnItemClickListener(final BandAlbumAdapter
            .OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public class BandAlbumViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        public TextView tvTitle;
        public TextView tvType;
        public TextView tvYear;

        public BandAlbumViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_name);
            tvType = (TextView) itemView.findViewById(R.id.tv_genre);
            tvYear = (TextView) itemView.findViewById(R.id.tv_country);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Discography item = mAlbum.get(getAdapterPosition());
            mItemClickListener.onItemClick(item);
        }
    }
}
