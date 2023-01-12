package com.example.picsumphotos.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.picsumphotos.R;
import com.example.picsumphotos.data.model.PictureItem;
import com.example.picsumphotos.viewmodel.PictureItemViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//public class MainActivity extends AppCompatActivity implements PictureItemsAdapter.OnClickItemInterface{
public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.RVActivity)
//    RecyclerView rvActivity;

    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

//    @BindView(R.id.btnDeleteLocalDB)
//    Button btnDeleteLocalDB;
//
//    @BindView(R.id.btnReload)
//    Button btnReload;

//    private PictureItemViewModel pictureItemViewModel;
//
//    private PictureItemsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openFragment(new HomeFragment()); //para que se abra el homeFragment apenas se abra la app

        ButterKnife.bind(this);

//        adapter = new PictureItemsAdapter(this);
//        //rvActivity.setAdapter(adapter);
//        //rvActivity.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//
//        pictureItemViewModel = new ViewModelProvider(this).get(PictureItemViewModel.class);
//
//        pictureItemViewModel.getPicturesLiveData().observe(this, pictureItems -> {
//            adapter.setPictures(pictureItems);
//        });
//
//        pictureItemViewModel.requestPictureItems();
//
//        btnDeleteLocalDB.setOnClickListener(v -> {
//            List<PictureItem> pictureItems = new ArrayList<>();
//            pictureItemViewModel.deleteAllRoomItems();
//            adapter.setPictures(pictureItems);
//        });
//
//        btnReload.setOnClickListener(v -> pictureItemViewModel.requestPictureItems());

    }

//    @Override
//    public void onClickItem(PictureItem pictureItem) {
//        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("pictureItem", pictureItem);
//        intent.putExtras(bundle);
//        startActivity(intent);
//    }

    //inflar el fragment en este activity:
    private void openFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();

    }
}