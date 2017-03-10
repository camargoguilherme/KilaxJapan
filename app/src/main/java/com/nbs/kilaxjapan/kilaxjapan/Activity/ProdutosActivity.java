package com.nbs.kilaxjapan.kilaxjapan.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nbs.kilaxjapan.kilaxjapan.R;
import com.squareup.picasso.Picasso;

public class ProdutosActivity extends AppCompatActivity {

    private String ean;
    private String descr;
    private TextView EANdb;
    private TextView descrdb;
    private TextView materialdb;
    private TextView pesodb;
    private TextView medidasdb;
    private TextView origemdb;
    private TextView precaucoesdb;
    private Toolbar toolbar;

    private ImageView image;

    public StorageReference storage =  FirebaseStorage.getInstance().getReference();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);

        final Intent intent = getIntent();
        ean = intent.getExtras().getString("ean");
        descr = intent.getExtras().getString("desc");

        EANdb = (TextView) findViewById(R.id.textEANdbId);
        descrdb = (TextView) findViewById(R.id.textDescdbId);
        materialdb = (TextView) findViewById(R.id.textMaterialdbId);
        pesodb = (TextView) findViewById(R.id.textPesodbId);
        medidasdb = (TextView) findViewById(R.id.textMedidasdbId);
        origemdb = (TextView) findViewById(R.id.textOrigemdbId);
        precaucoesdb = (TextView) findViewById(R.id.textPrecacoesdbId);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(descr);
        setSupportActionBar(toolbar);

        //Seta a Imagem no ImageView
        image = (ImageView) findViewById(R.id.produto_imagem_detalhe);

        //Setando as informaçoes dos Produtos
        EANdb.setText(ean);
        descrdb.setText(descr);
        materialdb.setText(intent.getExtras().getString( "material" ));
        pesodb.setText(intent.getExtras().getString( "peso" ));
        medidasdb.setText(intent.getExtras().getString( "medida" ));
        origemdb.setText(intent.getExtras().getString( "origem" ));
        precaucoesdb.setText(intent.getExtras().getString( "precaucoes" ));

        //String com URL dos arquivos
        String url = "gs://kilax-japan.appspot.com";

        //Instanciando FirebaseStorage e StorageReference

        StorageReference storageRef = storage.child("/produtos/" + ean + "/" +  ean + ".jpg");

        //Seta a Imagem no ImageView
        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(storageRef)
                .into(image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String imagem = null;
                if(imagem == null){
                    Toast.makeText(getApplicationContext(), "Imagens ainda não disponiveis.\n Por favor, aguarde!", Toast.LENGTH_LONG).show();

                }else {
                    Picasso.with(getApplicationContext()).load(imagem).into(image);
                }
            }
        });
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
