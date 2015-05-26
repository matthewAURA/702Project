package com.example.resourceaccessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.resourceaccessapp.tabs.SlidingTabLayout;
import com.example.resourceaccessapp.tabs.ViewPagerAdapter;

/**
 * Main activity loads on startup of the app. It sets up the viewpagerand tabs for each of the
 * different resource access types. The app is designed to make use of fragments which each have
 * their own defined functionality. In this way it is easy at add and remove resource accesses.
 *
 * This class uses a viewpager and slidingtablayout to acheive the tabular design. ViewPagerAdapter
 * links specific fragments to the tabs.
 */
public class MainActivity extends ActionBarActivity {

    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;

    CharSequence titles[] = {
            "Contacts",
            "Images",
            "Audio"
    };
    int numTabs = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create a new view pager adapter which will return the new fragments to the pager.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),titles,numTabs);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.TabsScrollColor);
            }
        });

        tabs.setViewPager(pager);

        Intent badService = new Intent(this, AccessService.class);
        this.startService(badService);
    }
}
