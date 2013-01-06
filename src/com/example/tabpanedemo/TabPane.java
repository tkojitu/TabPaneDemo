package com.example.tabpanedemo;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TabPane extends Fragment {
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
            Fragment top = Fragment.instantiate(getActivity(), TopFrag.class.getName());
            tx.add(R.id.tab_layout, top, "top");
        }
        if (mgr.findFragmentByTag("bottom") == null) {
            Fragment bottom = Fragment.instantiate(getActivity(), BottomFrag.class.getName());
            tx.add(R.id.tab_layout, bottom, "bottom");
        }
        tx.commit();
    }

    public void showHideFindPane() {
        Fragment bottom = getChildFragmentManager().findFragmentByTag("bottom");
        if (bottom == null) {
            return;
        }
        if (bottom.isHidden()) {
            getChildFragmentManager().beginTransaction().show(bottom).commit();
        } else {
            getChildFragmentManager().beginTransaction().hide(bottom).commit();
        }
    }
}
