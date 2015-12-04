package com.epages.epagesshopping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//activity class to show ePages objects in fragments
public class ProductsPagerViewActivity extends ActionBarActivity {
    MyPageAdapter pageAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        //call super constructor
        super.onCreate(savedInstanceState);

        //select layout
        setContentView(R.layout.products_pager_view_activity);

        List<Fragment> fragments = getFragments();

        pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager)findViewById(R.id.product_pager_view);
        pager.setAdapter(pageAdapter);

    }
    //get fragments for view pager object
    private List<Fragment> getFragments(){
        List<Fragment> fList = new ArrayList<Fragment>();

        //TODO: get products with rest count results and create fragments with JSON object content
        JSONObject testJSONObject = new JSONObject("{\"name\":\"test product 123\",\"id\":\"1234567\"}");

        //fList.add(FirstFragment.newInstance(0, "Page # 1 with seperate fragment class"));
        fList.add(ProductsPagerViewFragment.newInstance(testJSONObject));
        fList.add(ProductsPagerViewFragment.newInstance(testJSONObject));
        fList.add(ProductsPagerViewFragment.newInstance(testJSONObject));

        return fList;
    }

    private class MyPageAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;

        public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }
        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }
}
