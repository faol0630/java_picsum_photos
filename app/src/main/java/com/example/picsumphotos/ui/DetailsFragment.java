package com.example.picsumphotos.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.picsumphotos.R;
import com.example.picsumphotos.data.model.PictureItem;

import java.util.Objects;


public class DetailsFragment extends Fragment {

    ImageView ivDetails;

    TextView tvDetailsId;

    TextView tvDetailsAuthor;

    TextView tvDetailsWidth;

    TextView tvDetailsHeight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //back arrow:
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(((AppCompatActivity) requireActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true);

        ivDetails = (ImageView) requireView().findViewById(R.id.ivDetails);
        tvDetailsId = (TextView) requireView().findViewById(R.id.tvDetailsId);
        tvDetailsAuthor = (TextView) requireView().findViewById(R.id.tvDetailsAuthor);
        tvDetailsWidth = (TextView) requireView().findViewById(R.id.tvDetailsWidth);
        tvDetailsHeight = (TextView) requireView().findViewById(R.id.tvDetailsHeight);

        Bundle bundle = getArguments();

        if (bundle != null) {
            PictureItem pictureItem = (PictureItem) bundle.getSerializable("pictureItem");
            tvDetailsId.setText(pictureItem.getId());
            tvDetailsAuthor.setText(pictureItem.getAuthor());
            tvDetailsWidth.setText(String.valueOf(pictureItem.getWidth()));
            tvDetailsHeight.setText(String.valueOf(pictureItem.getHeight()));
            Glide.with(requireContext()).
                    load(pictureItem.getImage_url()).into(ivDetails);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            requireActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
