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

        //llamado de room:(esta funcion seria la que se llamaria para el recycler view de room)
        ExecutorSupplier.getInstance().execute(() -> {
            //lista del entity
            //esto no puede estar por fuera de la funcion:
            List<PictureItemEntity> localItems = localStorage.getDAO().getAllRoomPictureItems();
            //En caso de conexion exitosa, del entity al neutro
            callback.onSuccess(entityMapper.toModel(localItems));
        });
        //la solucion es llamar esto desde viewModel y en el activity details, llamar el metodo
        //getOneItemToRoom y pasarle lo que está en los textView como partes del parametro(item)
        //para hacer eso esta funcion debe estar por fuera de getPicturesItem y se debe quitar de
        //abajo la parte en donde se salva en Room.(Linea 68 aprox)
        //-----------------------------------------------------------------------------------

        //llamado a la lista del DTO remote
        Call<List<PictureItemDTO>> call = remote.getPicturesItemDAOFromRemoteDataAccess().getInterfacePictureItems();

        call.enqueue(new Callback<List<PictureItemDTO>>() {
            @Override
            public void onResponse(@NonNull Call<List<PictureItemDTO>> call, @NonNull Response<List<PictureItemDTO>> response) {
                if (response.body() != null && !response.body().isEmpty()) {

                    //del DTO a neutro
                    //esto no puede estar por fuera de la funcion:
                    List<PictureItem> items = dtoMapper.toModel(response.body());

                    callback.onSuccess(items); //del DTO al neutro

                    //--------------------------------------------
                    //salvar toda la info que viene de retrofit en Room:
                    ExecutorSupplier.getInstance().execute(() -> {
                        //convirtiendo de neutro a entity:
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

//la primera vez que se descarga pone todos los items en Room(linea 48aprox)
//la segunda vez que se abra si no hay internet , igual muestra toda la info debido a que ya la habia
//guardado en Room la primera vez.

//executor impide que se abran mas de 4 hilos.si en algun momento se tienen abiertos 4 hilos
//y se necesita abrir otro, el va a esperar a que uno de los 4 este disponible.
