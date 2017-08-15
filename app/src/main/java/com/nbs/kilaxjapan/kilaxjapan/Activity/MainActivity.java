package com.nbs.kilaxjapan.kilaxjapan.Activity;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
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
import com.nbs.kilaxjapan.kilaxjapan.Adapter.RecyclerViewAdapter;
import com.nbs.kilaxjapan.kilaxjapan.R;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private final int tempo = 4000;
    private Context context;
    private Produtos produtos;
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
        ref.addValueEventListener(eventListener);
        context = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);

        recyclerView.setAdapter(new RecyclerViewAdapter(prod, this));

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layout);
        */

        lisProd = (ListView) findViewById(R.id.listprodutosId);

        lisProd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =  new Intent(MainActivity.this, ProdutosActivity.class);

                //Recuperar dados a serem passados
                produtos = prod.get( position );

                /*Envia 'produtos' para ProdutosActivity
                  pelo metodo putExtra()
                */
                intent.putExtra("produto", produtos);
                startActivity( intent );
                finish();
            }
        });

    }


    /*Classe ValueEventListener "ouve" mudanças no banco de dados
        No metodo onDataChange()

     */
    ValueEventListener eventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            prod.clear();
            for (DataSnapshot data : dataSnapshot.getChildren()) {
                Produtos produto = data.getValue(Produtos.class);

                prod.add(produto);

            }

            ProdutosAdapter adaptador = new ProdutosAdapter(getApplicationContext(), prod);

            lisProd.setAdapter(adaptador);

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            Toast.makeText(context, "Não foi possivel conectar a Rede", Toast.LENGTH_SHORT);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //ref.addValueEventListener(eventListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //ref.removeEventListener(eventListener);
        //prod.clear();
    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - tempo) {
            toast = Toast.makeText(this, "Pressione o Botão Voltar novamente para fechar o Aplicativo.", tempo);
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
        int id = item.getItemId();

        switch ( id ) {
            case R.id.item_sair:
                finish();
            case R.id.item_recarregar:
                ref.addValueEventListener(eventListener);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action

        } else
            if (id == R.id.nav_gallery) {

        } else
            if (id == R.id.nav_slideshow) {

        } else
            if (id == R.id.nav_manage) {

        } else
            if (id == R.id.nav_share) {

        } else
            if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
