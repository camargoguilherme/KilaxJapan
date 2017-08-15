package com.nbs.kilaxjapan.kilaxjapan.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nbs.kilaxjapan.kilaxjapan.Activity.Produtos;
import com.nbs.kilaxjapan.kilaxjapan.R;
import com.nbs.kilaxjapan.kilaxjapan.RecyclerView.ViewHolder;
import java.util.ArrayList;

public class ProdutosAdapter extends ArrayAdapter<Produtos> {

    private Context context;
    private ArrayList<Produtos> produtos;
    private StorageReference storage =  FirebaseStorage.getInstance()
                                                        .getReferenceFromUrl("gs://kilax-japan.appspot.com/produtos/fotos");


    public  ProdutosAdapter(Context c, ArrayList<Produtos> objects) {
        super(c, 0, objects);
        this.context = c;
        this.produtos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {

        View view;
        ViewHolder holder;

        if (convertView == null) {
            view = LayoutInflater.from(context)
                    .inflate(R.layout.lista_produtos, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        //Recuperar Elementos para Vizualiçao

        Produtos prod = produtos.get(position);
        StorageReference storageRef = storage.child(prod.getEan() + ".JPG");
        holder.descricao.setText(prod.getDescricao());
        holder.material.setText(prod.getMaterial());

        //Seta a Imagem no ImageView
        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(storageRef)
                .into(holder.imagem);
    return view;
    }
}
