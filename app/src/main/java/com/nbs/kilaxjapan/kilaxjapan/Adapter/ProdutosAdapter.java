package com.nbs.kilaxjapan.kilaxjapan.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nbs.kilaxjapan.kilaxjapan.Activity.MainActivity;
import com.nbs.kilaxjapan.kilaxjapan.Activity.Produtos;
import com.nbs.kilaxjapan.kilaxjapan.Activity.ProdutosActivity;
import com.nbs.kilaxjapan.kilaxjapan.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class ProdutosAdapter extends ArrayAdapter<Produtos> {

    private Context context;
    private ArrayList<Produtos> produtos;
    private StorageReference storage =  FirebaseStorage.getInstance().getReference();
    private ImageView imageView;

    public ProdutosAdapter(Context c, ArrayList<Produtos> objects) {
        super(c, 0, objects);
        this.context = c;
        this.produtos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        View view = null;

        //Verifica se A lista esta preenchida
        if(produtos != null){

            //Inicializa Produtos para montagem de layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService( context.LAYOUT_INFLATER_SERVICE );

            //Monta a View a partir do xml
            view = inflater.inflate(R.layout.lista_produtos, parent, false );

            //Recuperar Elementos para Vizualiçao
            TextView textView1 = (TextView) view.findViewById(R.id.textProd1);
            TextView textView2 = (TextView) view.findViewById(R.id.textProdSub1);
            imageView = (ImageView) view.findViewById(R.id.produto_imagem_listview);
            Produtos produto = produtos.get( position );

            textView1.setText(produto.getDescricao());
            textView2.setText("Material: " + produto.getMaterial());

            //String com URL dos arquivos
            String url = "gs://kilax-japan.appspot.com";

            //Instanciando FirebaseStorage e StorageReference
            StorageReference storageRef = storage.child("/produtos/"+  produto.getEan() + "/" +  produto.getEan() + ".jpg");

            //Seta a Imagem no ImageView
            Glide.with(context /* context */)
                    .using(new FirebaseImageLoader())
                    .load(storageRef)
                    .into(imageView);
        }
        return view;
    }
}
