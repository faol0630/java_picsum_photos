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

    public void requestPictureItems(){

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

    //--------------Room---------------
    public void deleteAllRoomItems(){
        repository.deleteAllRoomItems();
    }
}

//AndroidViewModel because you need to pass the context(application) by parameter.


