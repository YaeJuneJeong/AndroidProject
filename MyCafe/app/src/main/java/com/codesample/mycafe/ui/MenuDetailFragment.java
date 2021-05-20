package com.codesample.mycafe.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.codesample.mycafe.MainViewModel;
import com.codesample.mycafe.databinding.FragmentHomeBinding;
import com.codesample.mycafe.databinding.FragmentMenuDetailBinding;


public class MenuDetailFragment extends Fragment {

    private MainViewModel viewModel;
    private FragmentMenuDetailBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        binding= FragmentMenuDetailBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getSelected().observe(getViewLifecycleOwner(), menu->{
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(menu.name);
        });
    }
}
