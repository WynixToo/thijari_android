package com.example.android.thijari.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.android.thijari.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class TestRecyclerViewAdapter extends RecyclerView.Adapter<TestRecyclerViewAdapter.ViewHolder> {

    List<Object> contents = new ArrayList<>();
    private Context context;

    //    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;

    public TestRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void addAll(List<Object> contents) {
        this.contents.addAll(contents);
    }

    @Override
    public int getItemViewType(int position) {
//        switch (position) {
//            case 0:
//                return TYPE_HEADER;
//            default:
        return TYPE_CELL;
//        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

//        switch (viewType) {
//            case TYPE_HEADER: {
//                view = LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.news_header, parent, false);
//                return new RecyclerView.ViewHolder(view) {
//                };
//            }
//            case TYPE_CELL: {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_card_small, parent, false);
        return new ViewHolder(view);
//                {
//                };
//            }
//        }
//        return null;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        switch (getItemViewType(position)) {
////            case TYPE_HEADER:
////                break;
//            case TYPE_CELL:
//                break;
//        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView title;
        public TextView datetime;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.new_image);
            datetime = (TextView) itemView.findViewById(R.id.new_datetime);
            title = (TextView) itemView.findViewById(R.id.new_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    getViewPager().setCurrentItem(getAdapterPosition());
                }
            });
        }
    }
}