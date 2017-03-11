package com.nbs.kilaxjapan.kilaxjapan.Adapter;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nbs.kilaxjapan.kilaxjapan.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Guilherme on 11/03/2017.
 */

public class CustonSwipeAdapper extends PagerAdapter {

    private String imagem ;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustonSwipeAdapper(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 47;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        String uri = imagem+(position+1)+".jpg";
        String contagem = (position+1)+"/"+getCount();

        layoutInflater = ( LayoutInflater ) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate( R.layout.swipe_layout, container, false );
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view_swipe);
        TextView textView = ( TextView ) view.findViewById(R.id.image_count);
        textView.setText(contagem);
        Picasso.with(context).load(uri).into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
