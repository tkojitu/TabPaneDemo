package com.example.tabpanedemo;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabPane extends Fragment {
    private TopFrag top = new TopFrag();
    private BottomFrag bottom = new BottomFrag();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_pane, container, false);
        addFrags();
        return view;
    }

    private void addFrags() {
        FragmentManager mgr = getChildFragmentManager();
        FragmentTransaction tx = mgr.beginTransaction();
        if (mgr.findFragmentByTag("top") == null) {
            tx.add(R.id.tab_layout, top, "top");
        }
        if (mgr.findFragmentByTag("bottom") == null) {
            tx.add(R.id.tab_layout, bottom, "bottom");
        }
        tx.commit();
    }

    public void showHideFindPane() {
        if (bottom.isHidden()) {
            getChildFragmentManager().beginTransaction().show(bottom).commit();
        } else {
            getChildFragmentManager().beginTransaction().hide(bottom).commit();
        }
    }
}