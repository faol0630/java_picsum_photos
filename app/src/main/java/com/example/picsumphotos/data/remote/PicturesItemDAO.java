package com.example.picsumphotos.data.remote;

import com.example.picsumphotos.data.model.PictureItemDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PicturesItemDAO {
    @GET(ConstantsAPI.ENDPOINT)
    Call<List<PictureItemDTO>> getInterfacePictureItems();
}
