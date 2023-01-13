package com.example.picsumphotos.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.picsumphotos.R;
import com.example.picsumphotos.data.model.PictureItem;
import com.example.picsumphotos.viewmodel.PictureItemViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements PictureItemsAdapter.OnClickItemInterface {

    @BindView(R.id.btnDeleteLocalDB)
    Button btnDeleteLocalDB;

    @BindView(R.id.btnReload)
    Button btnReload;

    @BindView(R.id.RVActivity)
    RecyclerView rvActivity;

    private Unbinder unbinder; //para liberar cuando se termina

    private PictureItemViewModel pictureItemViewModel;

    private PictureItemsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        unbinder = ButterKnife.bind(this, view); //diferente a como se hace en activity

        adapter = new PictureItemsAdapter(this);

        rvActivity.setAdapter(adapter);
        rvActivity.setLayoutManager(new LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false));

        pictureItemViewModel = new ViewModelProvider(this).get(PictureItemViewModel.class);

        pictureItemViewModel.getPicturesLiveData().observe(requireActivity(), pictureItems -> {
            adapter.setPictures(pictureItems);
        });

        pictureItemViewModel.requestPictureItems();

        btnDeleteLocalDB.setOnClickListener(v -> {
            List<PictureItem> pictureItems = new ArrayList<>();
            pictureItemViewModel.deleteAllRoomItems();
            adapter.setPictures(pictureItems);
        });

        btnReload.setOnClickListener(v -> pictureItemViewModel.requestPictureItems());

        return view;//esta linea dejarla de ultima para que el adapter acepte el parametro this.
    }

    @Override
    public void onClickItem(PictureItem pictureItem) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("pictureItem", pictureItem);
        Fragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, detailsFragment, "details_fragment")
                .addToBackStack("details_fragment").commit();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



}
