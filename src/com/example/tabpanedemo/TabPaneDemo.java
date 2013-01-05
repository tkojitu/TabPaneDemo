package com.example.tabpanedemo;

import com.example.android.apis.app.AndroidTabListener;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class TabPaneDemo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_pane_demo);
        ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        addTab();
    }

    private void addTab() {
        ActionBar bar = getActionBar();
        int count = bar.getTabCount();
        String title = "Tab-" + count;
        Tab tab = bar.newTab();
        tab.setText(title);
        tab.setTabListener(new AndroidTabListener<TabPane>(this, title, TabPane.class));
        bar.addTab(tab);
    }

    private void removeTab() {
        ActionBar bar = getActionBar();
        if (bar.getTabCount() > 0) {
            bar.removeTabAt(bar.getTabCount() - 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tab_pane_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_add:
            addTab();
            return true;
        case R.id.menu_remove:
            removeTab();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
