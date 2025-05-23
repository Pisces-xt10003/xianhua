package com.example.xianhua.adapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

public class AdPagerAdapter extends PagerAdapter {
    private Context mContext;
    private int[] mAdImages;

    public AdPagerAdapter(Context context, int[] adImages) {
        mContext = context;
        mAdImages = adImages;
    }

    @Override
    public int getCount() {
        return mAdImages.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(mAdImages[position]);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}