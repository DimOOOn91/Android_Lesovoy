package com.example.module06main.fragments;


import android.support.v4.app.Fragment;

import com.example.module06main.MainActivity;

public abstract class BaseFragment extends Fragment {

    public void addFragment(Fragment fragment) {
        ((MainActivity) getActivity()).addFragment(fragment);
    }
    public void replaceFragment(Fragment fragment) {
        ((MainActivity) getActivity()).replaceFragment(fragment);
    }

}
