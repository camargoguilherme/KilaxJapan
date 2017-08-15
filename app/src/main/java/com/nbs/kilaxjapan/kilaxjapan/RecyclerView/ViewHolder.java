package com.nbs.kilaxjapan.kilaxjapan.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nbs.kilaxjapan.kilaxjapan.R;

/**
 * Created by Guilherme on 12/05/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    public TextView descricao;
    public TextView material;
    public ImageView imagem;

    public ViewHolder(View view) {
        super(view);
        descricao = (TextView) view.findViewById(R.id.textProd1);
        material = (TextView) view.findViewById(R.id.textProdSub1);
        imagem = (ImageView) view.findViewById(R.id.produto_imagem_listview);
    }
}
