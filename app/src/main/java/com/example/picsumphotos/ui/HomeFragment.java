package com.example.picsumphotos.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.picsumphotos.R;
import com.example.picsumphotos.data.model.PictureItem;
import com.example.picsumphotos.viewmodel.PictureItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements PictureItemsAdapter.OnClickItemInterface {

    Button btnDeleteLocalDB;

    Button btnReload;

    RecyclerView rvActivity;

    private PictureItemViewModel pictureItemViewModel;

    private PictureItemsAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        btnDeleteLocalDB = (Button) requireView().findViewById(R.id.btnDeleteLocalDB);
        btnReload = (Button) requireView().findViewById(R.id.btnReload);
        rvActivity = (RecyclerView) requireView().findViewById(R.id.RVActivity);

        adapter = new PictureItemsAdapter(this);

        rvActivity.setAdapter(adapter);
        rvActivity.setLayoutManager(new LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false));

        pictureItemViewModel = new ViewModelProvider(this).get(PictureItemViewModel.class);

        pictureItemViewModel.getPicturesLiveData().observe(requireActivity(), adapter::setPictures);

        pictureItemViewModel.requestPictureItems();

        btnDeleteLocalDB.setOnClickListener(v -> {
            List<PictureItem> pictureItems = new ArrayList<>();
            pictureItemViewModel.deleteAllRoomItems();
            adapter.setPictures(pictureItems);
        });

        btnReload.setOnClickListener(v -> pictureItemViewModel.requestPictureItems());
    }

    @Override
    public void onClickItem(PictureItem pictureItem) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("pictureItem", pictureItem);
        Fragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, detailsFragment, "details_fragment")
                .addToBackStack("details_fragment").commit();


    }

}
