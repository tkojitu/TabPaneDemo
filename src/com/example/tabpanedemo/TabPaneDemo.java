package com.example.tabpanedemo;

import java.util.HashSet;

import com.example.android.apis.app.AndroidTabListener;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

public class TabPaneDemo extends Activity {
    HashSet<AndroidTabListener<TabPane>> listeners = new  HashSet<AndroidTabListener<TabPane>>();

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
        AndroidTabListener<TabPane> listener =
                new AndroidTabListener<TabPane>(this, title, TabPane.class);
        listeners.add(listener);
        tab.setTabListener(listener);
        bar.addTab(tab);
    }

    private void removeTab() {
        ActionBar bar = getActionBar();
        if (bar.getTabCount() > 0) {
            bar.removeTabAt(bar.getTabCount() - 1);
        }
    }

    private void showHide() {
        ActionBar bar = getActionBar();
        Tab tab = bar.getSelectedTab();
        TabPane pane = findTabPane(tab);
        pane.showHideFindPane();
    }

    private TabPane findTabPane(Tab tab) {
        for (AndroidTabListener<TabPane> listener : listeners) {
            Fragment frag = listener.getFragment(tab);
            if (frag != null) {
                return (TabPane) frag;
            }
        }
        return null;
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
        case R.id.menu_show_hide:
            showHide();
        default:
            return super.onOptionsItemSelected(item);
        }
    }
}
