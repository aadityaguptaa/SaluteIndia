package com.army.saluteindia.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.army.saluteindia.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link maholla_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class maholla_fragment extends Fragment {


    public maholla_fragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maholla_fragment, container, false);
    }
}