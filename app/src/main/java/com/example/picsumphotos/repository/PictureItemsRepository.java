package com.example.picsumphotos.repository;


import com.example.picsumphotos.data.local.Database;
import com.example.picsumphotos.data.model.PictureItem;
import com.example.picsumphotos.data.model.PictureItemDTO;
import com.example.picsumphotos.data.model.PictureItemEntity;
import com.example.picsumphotos.data.remote.RemoteDataAccess;
import com.example.picsumphotos.repository.mapper.PictureItemDTOMapper;
import com.example.picsumphotos.repository.mapper.PictureItemEntityMapper;
import com.example.picsumphotos.viewmodel.ExecutorSupplier;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PictureItemsRepository {

    private Database localStorage; //Room
    private RemoteDataAccess remote; //Retrofit
    private PictureItemEntityMapper entityMapper;
    private PictureItemDTOMapper dtoMapper;

    //constructor
    public PictureItemsRepository(Database localStorage, RemoteDataAccess remote, PictureItemEntityMapper entityMapper, PictureItemDTOMapper dtoMapper) {
        this.localStorage = localStorage;
        this.remote = remote;
        this.entityMapper = entityMapper;
        this.dtoMapper = dtoMapper;
    }

    public void getPictureItems(AsyncTaskReceiver<List<PictureItem>> callback) {

        ExecutorSupplier.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                List<PictureItemEntity> localItems = localStorage.getDAO().getAllRoomPictureItems();
                callback.onSuccess(entityMapper.toModel(localItems)); //del entity al neutro
            }
        });


        Call<List<PictureItemDTO>> call = remote.getPicturesItemDAOFromRemoteDataAccess().getInterfacePictureItems();

        call.enqueue(new Callback<List<PictureItemDTO>>() {
            @Override
            public void onResponse(Call<List<PictureItemDTO>> call, Response<List<PictureItemDTO>> response) {
                if (response.body() != null && !response.body().isEmpty()) {

                    List<PictureItem> items = dtoMapper.toModel(response.body()); //del DTO a neutro

                    callback.onSuccess(items); //del DTO al neutro
                    ExecutorSupplier.getInstance().execute(new Runnable() {
                        @Override
                        public void run() {
                            //salvar toda la info que viene de retrofit en Room:
                            localStorage.getDAO().saveAllRoomPictureItems(entityMapper.fromModel(items)); //se deben salvar en modo Entity
                        }
                    });


                }
            }

            @Override
            public void onFailure(Call<List<PictureItemDTO>> call, Throwable t) {
                callback.onFailure(t);
            }
        });


    }
}

//la primera vez que se descarga pone todos los items en Room(linea 48aprox)
//la segunda vez que se abra si no hay internet , igual muestra toda la info debido a que ya la habia
//guardado en Room la primera vez.

//executor impide que se abran mas de 4 hilos.si en algun momento se tienen abiertos 4 hilos
//y se necesita abrir otro, el va a esperar a que uno de los 4 este disponible.
