package com.nbs.kilaxjapan.kilaxjapan.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nbs.kilaxjapan.kilaxjapan.Adapter.ProdutosAdapter;
import com.nbs.kilaxjapan.kilaxjapan.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toast toast;
    private long lastBackPressTime = 0;
    private ListView lisProd;
    private ArrayList<Produtos> prod = new ArrayList<>();
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference().child( "produtos" );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Produtos");
        setSupportActionBar(toolbar);

        lisProd = (ListView) findViewById(R.id.listprodutosId);

        lisProd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(MainActivity.this, ProdutosActivity.class);

                //Recuperar dados a serem passados
                Produtos produtos = prod.get( position );


                //Envia dados para ProdutosActivity
                intent.putExtra("ean", produtos.getEan());
                intent.putExtra("desc", produtos.getDescricao());
                intent.putExtra("medida", produtos.getMedidas());
                intent.putExtra("peso", produtos.getPeso());
                intent.putExtra("origem", produtos.getOrigem());
                intent.putExtra("material", produtos.getMaterial());
                intent.putExtra("precaucoes", produtos.getPrecaucoes());

                startActivity( intent );
                finish();
            }
        });

    }


    //Estancia dados dos produtos da FirebaseDatabase
    ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            prod.clear();
            for (DataSnapshot data : dataSnapshot.getChildren()) {

                Log.i("FIREBASE", data.getValue().toString());
                Produtos produto = data.getValue(Produtos.class);

                prod.add(produto);

            }

            ProdutosAdapter adaptador = new ProdutosAdapter(getApplicationContext(), prod);

            lisProd.setAdapter(adaptador);
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        ref.addValueEventListener(eventListener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        ref.removeEventListener(eventListener);
        prod.clear();
    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", 4000);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ( item.getItemId() ) {
            case R.id.item_sair:
                finish();
            case R.id.item_recarregar:
                ref.addValueEventListener(eventListener);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
