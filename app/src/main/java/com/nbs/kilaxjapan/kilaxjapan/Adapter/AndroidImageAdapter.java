package com.nbs.kilaxjapan.kilaxjapan.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AndroidImageAdapter extends PagerAdapter {
    Context mContext;
    private StorageReference storage =  FirebaseStorage.getInstance().getReference();

    public AndroidImageAdapter(Context context) {

        this.mContext = context;
    }

    @Override
    public int getCount() {
        return sliderImagesId.length;
    }

    //Diretorio das Imagem
    /*Mudar para tipo String[] para poder Armazenar
        as URL de Download das imagens; vai facilitar na
        hora de Exibir;
     */
    private String[] sliderImagesId = new String[]{
            "4560177466734(R).jpg",
            "4560177466734(Y).jpg",
            "4560177466734.jpg"
    };

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((ImageView) obj);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {
        ImageView mImageView = new ImageView(mContext);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        /*Use a Classe Glide.()..... para poder setar as imagens, usando os Strings[] para seta a
            imagem no Image View;
        */

        //Instanciando FirebaseStorage e StorageReference
        StorageReference storageRef = storage.child("/produtos/4560177466734/" + sliderImagesId[i] + ".jpg");

        //Seta a Imagem no ImageView
        Glide.with(mContext /* context */)
                .using(new FirebaseImageLoader())
                .load(storageRef)
                .into(mImageView);

        ((ViewPager) container).addView(mImageView, 0);
        return mImageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int i, Object obj) {
        ((ViewPager) container).removeView((ImageView) obj);
    }
}
