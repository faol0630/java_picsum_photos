package com.example.picsumphotos.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.picsumphotos.R;
import com.example.picsumphotos.data.model.PictureItem;
import com.example.picsumphotos.viewmodel.PictureItemViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PictureItemsAdapter.OnClickItemInterface{

    @BindView(R.id.RVActivity)
    RecyclerView rvActivity;

    @BindView(R.id.btnLoad)
    Button btnLoad;

    private PictureItemViewModel pictureItemViewModel;

    private PictureItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        btnLoad.setOnClickListener(v -> pictureItemViewModel.requestPictureItems());

        adapter = new PictureItemsAdapter(this);
        rvActivity.setAdapter(adapter);
        rvActivity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        pictureItemViewModel = new ViewModelProvider(this).get(PictureItemViewModel.class);

        pictureItemViewModel.getPicturesLiveData().observe(this, pictureItems -> {
            adapter.setPictures(pictureItems);
        });

    }

    @Override
    public void onClickItem(PictureItem pictureItem) {
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("pictureItem", pictureItem);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}