package com.nbs.kilaxjapan.kilaxjapan.Activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nbs.kilaxjapan.kilaxjapan.Adapter.CustonSwipeAdapper;
import com.nbs.kilaxjapan.kilaxjapan.R;

public class PagerViewActivity extends AppCompatActivity {

    public static Produtos produtos;
    private ViewPager viewPager;
    private CustonSwipeAdapper custonSwipeAdapper;
    private String ean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        this.produtos = (Produtos) intent.getSerializableExtra("produto");
        ean = produtos.getEan();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_view);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        custonSwipeAdapper = new CustonSwipeAdapper(this, ean);
        viewPager.setAdapter(custonSwipeAdapper);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent( PagerViewActivity.this, ProdutosActivity.class);
        intent.putExtra("produto", produtos);
        startActivity( intent);
        finish();
    }
}
