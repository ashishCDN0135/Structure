package com.structure.app.structuremvp.views.fragment.setting;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.structure.app.structuremvp.R;
import com.structure.app.structuremvp.databinding.FragmentProfileLayoutBinding;
import com.structure.app.structuremvp.databinding.FragmentSettingLayoutBinding;
import com.structure.app.structuremvp.interfaces.DialogClickListener;
import com.structure.app.structuremvp.views.base.BaseFragment;
import com.structure.app.structuremvp.views.fragment.profile.ProfileViewModel;

/**
 * Created by ashishthakur on 25/1/19.
 */

public class SettingFragment extends BaseFragment implements DialogClickListener {

    SettingViewModel viewModel;
    FragmentSettingLayoutBinding binding;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting_layout, container, false);
        viewModel = ViewModelProviders.of(this).get(SettingViewModel.class);
        initializeViews(binding.getRoot());
        return binding.getRoot();
    }
    @Override
    public void initializeViews(View rootView) {


    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDialogClick(int which, int requestCode) {

    }
}