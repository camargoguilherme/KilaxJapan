package com.nbs.kilaxjapan.kilaxjapan.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nbs.kilaxjapan.kilaxjapan.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Guilherme on 11/03/2017.
 */

public class CustonSwipeAdapper extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private String ean;
    private StorageReference storage =  FirebaseStorage.getInstance().getReference();

    public CustonSwipeAdapper(Context context, String ean) {

        this.context = context;
        this.ean = ean;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //String com URL dos arquivos

        String contagem = (position+1)+"/"+getCount();

        layoutInflater = ( LayoutInflater ) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate( R.layout.swipe_layout, container, false );
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view_swipe);
        TextView textView = ( TextView ) view.findViewById(R.id.image_count);
        textView.setText(contagem);
        container.addView(view);

        if(position == 0){
            /*Instanciando StorageReference com a primeira imagem do Produto
            * na primeira posicao do SlideShow
            */
            StorageReference storageRef = storage.child("/produtos/"+  ean + "/" + ean + "(R).jpg");


            //Seta a Imagem no ImageView
            Glide.with(context)
                    .using(new FirebaseImageLoader())
                    .load(storageRef)
                    .into(imageView);
        }else if(position == 1){
            /*Instanciando StorageReference com a segunda imagem do Produto
            * na segunda posicao do SlideShow
            */
            StorageReference storageRef = storage.child("/produtos/"+  ean + "/" + ean + "(Y).jpg");


            //Seta a Imagem no ImageView
            Glide.with(context)
                    .using(new FirebaseImageLoader())
                    .load(storageRef)
                    .into(imageView);
        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // no lugar do Cast (View) esta (LinearLayout)
        container.removeView((View) object);
    }
}
