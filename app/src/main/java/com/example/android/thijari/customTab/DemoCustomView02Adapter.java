package com.example.android.thijari.customTab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.thijari.R;


/**
 * Created by Shinichi Nishimura on 2015/07/22.
 */
public class DemoCustomView02Adapter
        extends RecyclerTabLayout.Adapter<DemoCustomView02Adapter.ViewHolder> {

    private DemoImitationLoopPagerAdapter mAdapater;

    public DemoCustomView02Adapter(ViewPager viewPager) {
        super(viewPager);
        mAdapater = (DemoImitationLoopPagerAdapter) mViewPager.getAdapter();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_custom_view02_tab, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        Drawable drawable = loadIconWithTint(holder.imageView.getContext(),
//                mAdapater.getImageResourceId(position));
        holder.imageView.getLayoutParams().width = 100;
        holder.imageView.getLayoutParams().height = 100;
        holder.imageView.requestLayout();
        holder.imageView.setImageResource(mAdapater.getImageResourceId(position));
        if (position == getCurrentIndicatorPosition()) {
            holder.imageView.getLayoutParams().width = 220;
            holder.imageView.getLayoutParams().height = 220;
            holder.imageView.requestLayout();
            holder.imageView.setImageResource(mAdapater.getSelectedImageResourceId(position));
        }
//        holder.imageView.setSelected(position == getCurrentIndicatorPosition());
    }

//    private Drawable loadIconWithTint(Context context, @DrawableRes int resourceId) {
//        Drawable icon = ContextCompat.getDrawable(context, resourceId);
//        ColorStateList colorStateList = ContextCompat
//                .getColorStateList(context, R.color.custom_view02_tint);
//        icon = DrawableCompat.wrap(icon);
//        DrawableCompat.setTintList(icon, colorStateList);
//        return icon;
//    }

    @Override
    public int getItemCount() {
        return mAdapater.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getViewPager().setCurrentItem(getAdapterPosition());
                }
            });
        }
    }
}
