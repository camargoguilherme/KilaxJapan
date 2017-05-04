package com.nbs.kilaxjapan.kilaxjapan.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nbs.kilaxjapan.kilaxjapan.Adapter.CustonSwipeAdapper;
import com.nbs.kilaxjapan.kilaxjapan.R;

import java.io.Serializable;

public class ProdutosActivity extends AppCompatActivity {

    private Produtos produto;
    private Toolbar toolbar;
    private ImageView image;
    private TextView EANdb;
    private TextView descrdb;
    private TextView materialdb;
    private TextView pesodb;
    private TextView medidasdb;
    private TextView origemdb;
    private TextView precaucoesdb;



    public FirebaseStorage storage = FirebaseStorage.getInstance();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        Intent intent = getIntent();
        this.produto = (Produtos) intent.getSerializableExtra("produto");

        EANdb = (TextView) findViewById(R.id.textEANdbId);
        descrdb = (TextView) findViewById(R.id.textDescdbId);
        materialdb = (TextView) findViewById(R.id.textMaterialdbId);
        pesodb = (TextView) findViewById(R.id.textPesodbId);
        medidasdb = (TextView) findViewById(R.id.textMedidasdbId);
        origemdb = (TextView) findViewById(R.id.textOrigemdbId);
        precaucoesdb = (TextView) findViewById(R.id.textPrecacoesdbId);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(produto.getDescricao());
        setSupportActionBar(toolbar);

        //Seta a Imagem no ImageView
        image = (ImageView) findViewById(R.id.produto_imagem_detalhe);


        //Setando as informaçoes dos Produtos
        EANdb.setText(produto.getEan());
        descrdb.setText(produto.getDescricao());
        materialdb.setText(produto.getMaterial());
        pesodb.setText(produto.getPeso());
        medidasdb.setText(produto.getMedidas());
        origemdb.setText(produto.getOrigem());
        precaucoesdb.setText(produto.getPrecaucoes());

        //Instanciando FirebaseStorage e StorageReference

        StorageReference storageRef = storage.getReference().child("/produtos/" + produto.getEan() + "/" +  produto.getEan() + ".jpg");

        //Seta a Imagem no ImageView
            Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(storageRef)
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent( ProdutosActivity.this, PagerViewActivity.class);
                intent.putExtra("produto", produto);
                startActivity( intent );
                finish();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_produtos, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity( new Intent( ProdutosActivity.this, MainActivity.class));
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
            case R.id.item_sair:
                finish();
            case R.id.item_voltar:
                startActivity( new Intent( ProdutosActivity.this, MainActivity.class));
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
