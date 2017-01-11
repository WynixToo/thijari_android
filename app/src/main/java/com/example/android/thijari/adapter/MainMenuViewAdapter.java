package com.example.android.thijari.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.thijari.R;
import com.example.android.thijari.activity.MenuItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class MainMenuViewAdapter extends RecyclerView.Adapter<MainMenuViewAdapter.ViewHolder> {

    List<MenuItem> contents = new ArrayList<>();

    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private OnItemClickListener listener;

    private Context context;

    public MainMenuViewAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<MenuItem> contents) {
        this.contents.addAll(contents);
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_CELL;
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public MenuItem getItem(int position) {
        return this.contents.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mainmenu_item, parent, false);

        return new ViewHolder(view);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        switch (getItemViewType(position)) {
////            case TYPE_HEADER:
////                break;
//            case TYPE_CELL:
//                break;
//        }
//        holder.imageView

        Picasso.with(this.context).load(contents.get(position).getMenuDrawable()).fit().into(holder.imageView);
        holder.textView.setText(contents.get(position).getMenuTitle());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.menu_img);
            textView = (TextView) itemView.findViewById(R.id.menu_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(getItem(getAdapterPosition()), getAdapterPosition());
                    }
//                    getViewPager().setCurrentItem(getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(MenuItem menuItem, int position);
    }
}