package com.nbs.kilaxjapan.kilaxjapan.Activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.nbs.kilaxjapan.kilaxjapan.Adapter.CustonSwipeAdapper;
import com.nbs.kilaxjapan.kilaxjapan.R;

public class PagerViewActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private CustonSwipeAdapper custonSwipeAdapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_view);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        custonSwipeAdapper = new CustonSwipeAdapper(this);
        viewPager.setAdapter(custonSwipeAdapper);

    }
}
