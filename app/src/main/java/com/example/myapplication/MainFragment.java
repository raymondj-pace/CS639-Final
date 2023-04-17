package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        // Inflate the layout for this fragment
        View fragmentMain = inflater.inflate(R.layout.fragment_main, container, false);

        // Code here to populate list

        return fragmentMain;
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragmentDirections.ActionMainToAddFunds action = MainFragmentDirections.actionMainToAddFunds(null);
                NavHostFragment.findNavController(MainFragment.this).navigate(action);
            }
        });

        view.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragmentDirections.ActionMainToDeduction action = MainFragmentDirections.actionMainToDeduction(null);
                NavHostFragment.findNavController(MainFragment.this).navigate(action);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }
}
