package com.example.picsumphotos.repository;


import androidx.annotation.NonNull;

import com.example.picsumphotos.data.local.Database;
import com.example.picsumphotos.data.model.PictureItem;
import com.example.picsumphotos.data.model.PictureItemDTO;
import com.example.picsumphotos.data.model.PictureItemEntity;
import com.example.picsumphotos.data.remote.RemoteDataAccess;
import com.example.picsumphotos.repository.mapper.PictureItemDTOMapper;
import com.example.picsumphotos.repository.mapper.PictureItemEntityMapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureItemsRepository {

    private final Database localStorage; //Room
    private final RemoteDataAccess remote; //Retrofit
    private final PictureItemEntityMapper entityMapper;
    private final PictureItemDTOMapper dtoMapper;

    //constructor
    public PictureItemsRepository(Database localStorage, RemoteDataAccess remote, PictureItemEntityMapper entityMapper, PictureItemDTOMapper dtoMapper) {
        this.localStorage = localStorage;
        this.remote = remote;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
    }


    public void getPictureItems(AsyncTaskReceiver<List<PictureItem>> callback) {

        //Room
        ExecutorSupplier.getInstance().execute(() -> {

            List<PictureItemEntity> localItems = localStorage.getDAO().getAllRoomPictureItems();

            callback.onSuccess(entityMapper.toModel(localItems));
        });

        //-----------------------------------------------------------------------------------

        //DTO remote
        Call<List<PictureItemDTO>> call = remote.getPicturesItemDAOFromRemoteDataAccess().getInterfacePictureItems();

        call.enqueue(new Callback<List<PictureItemDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<PictureItemDTO>> call, @NonNull Response<List<PictureItemDTO>> response) {
                if (response.body() != null && !response.body().isEmpty()) {

                    //using mapper:
                    List<PictureItem> items = dtoMapper.toModel(response.body());

                    callback.onSuccess(items);

                    //--------------------------------------------
                    //save all the info coming from retrofit inside Room:
                    ExecutorSupplier.getInstance().execute(() -> {

                        localStorage.getDAO().saveAllRoomPictureItems(entityMapper.fromModel(items)); //se deben salvar en modo Entity
                    });


                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PictureItemDTO>> call, @NonNull Throwable t) {
                callback.onFailure(t);
            }
        });


    }

    //---------------Room-----------------------------
    public void deleteAllRoomItems(){

        ExecutorSupplier.getInstance().execute(localStorage::clearAllTables);
    }
}

