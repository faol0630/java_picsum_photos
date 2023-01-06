package com.example.picsumphotos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.picsumphotos.data.local.Database;
import com.example.picsumphotos.data.model.PictureItem;
import com.example.picsumphotos.data.remote.RemoteDataAccess;
import com.example.picsumphotos.repository.mapper.PictureItemDTOMapper;
import com.example.picsumphotos.repository.mapper.PictureItemEntityMapper;
import com.example.picsumphotos.repository.AsyncTaskReceiver;
import com.example.picsumphotos.repository.PictureItemsRepository;

import java.util.List;

public class PictureItemViewModel extends AndroidViewModel {

    private final PictureItemsRepository repository;

    public PictureItemViewModel(@NonNull Application application) {
        super(application);
        repository = new PictureItemsRepository(
                Database.getInstance(application),
                RemoteDataAccess.getRemoteDataInstance(),
                PictureItemEntityMapper.getInstance(),
                PictureItemDTOMapper.getInstance()
        );
    }

    private final MutableLiveData<List<PictureItem>> itemLiveData = new MutableLiveData<>();//este se usa para el postValue

    public LiveData<List<PictureItem>> getPicturesLiveData(){ //sobre esto se monta el observer en el activity
        return itemLiveData;
    }

    public void requestPictureItems(){ //este es el metodo que se llama dentro de onCreate en el activity
        //dentro del getPicturesItems de repository está room incluido.Por lo tanto con este llamado
        //se llaman las dos cosas.Por eso aca no hay logica de Room.
        repository.getPictureItems(new AsyncTaskReceiver<List<PictureItem>>() {
            @Override
            public void onSuccess(List<PictureItem> result) {
                itemLiveData.postValue(result);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    //aca no hay nada de Room porque toda la logica está en repository.
    //aca solo está lo que viene de Retrofit.
}

//AndroidViewModel porque se necesita pasar por parametro al context(application).

//en el activity con el viewModel hay 4 movimientos:
    //* declarar el viewModel por fuera de onCreate sin instanciar
    //* instanciar viewModel dentro de onCreate
    //* crear el observer
    //* llamar el metodo(en este caso esta puesto dentro del onCLik del botton load)
