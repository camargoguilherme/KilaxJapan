package com.nbs.kilaxjapan.kilaxjapan.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nbs.kilaxjapan.kilaxjapan.ImageView.PhotoView;
import com.nbs.kilaxjapan.kilaxjapan.ImageView.PhotoViewAttacher;
import com.nbs.kilaxjapan.kilaxjapan.R;
import com.nbs.kilaxjapan.kilaxjapan.ImageView.ZoomableImageView;

/**
 * Created by Guilherme on 11/03/2017.
 */

public class CustonSwipeAdapper extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private String ean;
    private PhotoViewAttacher mAttacher;
    private StorageReference storage = FirebaseStorage.getInstance().getReferenceFromUrl("gs://kilax-japan.appspot.com/produtos/fotos");
    private int fotos;

    public CustonSwipeAdapper(Context context, String ean, int cont) {

        this.context = context;
        this.ean = ean;
        this.fotos = cont;
    }

    @Override
    public int getCount() {
        return 3;
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
        final PhotoView imageView = (PhotoView) view.findViewById(R.id.image_view_swipe);
        TextView textView = ( TextView ) view.findViewById(R.id.image_count);
        textView.setText(contagem);
        container.addView(view);

        if(position == 0){
            /*Instanciando StorageReference com a primeira imagem do Produto
            * na primeira posicao do SlideShow
            */
            StorageReference storageRef = storage.child( ean + ".JPG");

            //Seta a Imagem no ImageView
            Glide.with(context)
                    .using(new FirebaseImageLoader())
                    .load(storageRef)
                    .into(imageView);
            mAttacher = new PhotoViewAttacher(imageView);
        }else{
            /*Instanciando StorageReference com a segunda imagem do Produto
            * na segunda posicao do SlideShow
            */
            StorageReference storageRef = storage.child( ean + " ("+ (position+1) +").JPG");

            //Seta a Imagem no ImageView
            Glide.with(context)
                    .using(new FirebaseImageLoader())
                    .load(storageRef)
                    .into(imageView);
            mAttacher = new PhotoViewAttacher(imageView);

        }
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // no lugar do Cast (View) esta (LinearLayout)
        container.removeView((View) object);
    }
}
