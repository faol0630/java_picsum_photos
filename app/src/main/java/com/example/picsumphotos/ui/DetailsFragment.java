package com.example.picsumphotos.ui;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.picsumphotos.R;
import com.example.picsumphotos.data.model.PictureItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class DetailsFragment extends Fragment {

    @BindView(R.id.ivDetails)
    ImageView ivDetails;

    @BindView(R.id.tvDetailsId)
    TextView tvDetailsId;

    @BindView(R.id.tvDetailsAuthor)
    TextView tvDetailsAuthor;

    @BindView(R.id.tvDetailsWidth)
    TextView tvDetailsWidth;

    @BindView(R.id.tvDetailsHeight)
    TextView tvDetailsHeight;

    private Unbinder unbinder; //para liberar cuando se termina

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        unbinder = ButterKnife.bind(this, view); //diferente a como se hace en activity

        //back arrow:
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //esto solamente muestra la flecha

        //Bundle bundle = getIntent().getExtras();
        Bundle bundle = getArguments();

        if (bundle != null) {
            PictureItem pictureItem = (PictureItem) bundle.getSerializable("pictureItem");
            tvDetailsId.setText(pictureItem.getId());
            tvDetailsAuthor.setText(pictureItem.getAuthor());
            tvDetailsWidth.setText(String.valueOf(pictureItem.getWidth())); //parseo de int a String
            tvDetailsHeight.setText(String.valueOf(pictureItem.getHeight()));
            Glide.with(getContext()).
                    load(pictureItem.getImage_url()).into(ivDetails);
        }

        return view;
    }

    //funcionalidad del back arrow:
    //@Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
