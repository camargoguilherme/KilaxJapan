package com.nbs.kilaxjapan.kilaxjapan.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nbs.kilaxjapan.kilaxjapan.Activity.Produtos;
import com.nbs.kilaxjapan.kilaxjapan.R;
import com.nbs.kilaxjapan.kilaxjapan.RecyclerView.ViewHolder;

import java.util.List;

/**
 * Created by Guilherme on 12/05/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    private List<Produtos> produtos;
    private Context context;
    private StorageReference storage =  FirebaseStorage.getInstance().getReferenceFromUrl("gs://kilax-japan.appspot.com/produtos/fotos");

    public RecyclerViewAdapter(List<Produtos> produtos, Context context){
        this.produtos = produtos;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        //Inicializa Produtos para montagem de layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( context.LAYOUT_INFLATER_SERVICE );

        //Monta a View a partir do xml
        View view = inflater.inflate(R.layout.lista_produtos, parent, false );
        ViewHolder recyclerViewHolder = new ViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        Produtos produto = produtos.get(position);

        viewHolder.descricao.setText(produto.getDescricao());
        viewHolder.material.setText(produto.getMaterial());

        /*Instanciando StorageReference com a primeira imagem do Produto
            * na primeira posicao do SlideShow
            */

        StorageReference storageRef = storage.child(produto.getEan() + ".JPG");


        //Seta a Imagem no ImageView

        final long ONE_MEGABYTE = 1024 * 1024;

        //download file as a byte array
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                viewHolder.imagem.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return produtos.size();
    }
}
