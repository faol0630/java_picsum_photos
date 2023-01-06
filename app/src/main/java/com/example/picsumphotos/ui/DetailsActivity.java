package com.example.picsumphotos.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.picsumphotos.R;
import com.example.picsumphotos.data.model.PictureItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        //back arrow:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //esto solamente muestra la flecha

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            PictureItem pictureItem = (PictureItem) bundle.getSerializable("pictureItem");
            tvDetailsId.setText(pictureItem.getId());
            tvDetailsAuthor.setText(pictureItem.getAuthor());
            tvDetailsWidth.setText(String.valueOf(pictureItem.getWidth())); //parseo de int a String
            tvDetailsHeight.setText(String.valueOf(pictureItem.getHeight()));
            Glide.with(getBaseContext()).
                    load(pictureItem.getImage_url()).into(ivDetails);
        }

    }

    //funcionalidad del back arrow:
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}