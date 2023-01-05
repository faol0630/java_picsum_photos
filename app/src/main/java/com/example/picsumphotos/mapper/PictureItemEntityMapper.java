package com.example.picsumphotos.mapper;

import com.example.picsumphotos.data.model.PictureItem;
import com.example.picsumphotos.data.model.PictureItemEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PictureItemEntityMapper implements ObjectMapper<PictureItemEntity, PictureItem>{

    private static final PictureItemEntityMapper pictureItemEntityMapper = new PictureItemEntityMapper();

    public static PictureItemEntityMapper getInstance(){
        return pictureItemEntityMapper;
    }

    //private empty constructor
    private  PictureItemEntityMapper() {
    }

    @Override
    public PictureItem toModel(PictureItemEntity object) {
        return new PictureItem(object.getId(), object.getAuthor(), object.getImage_url());
    }

    @Override
    public PictureItemEntity fromModel(PictureItem object) {
        return new PictureItemEntity(object.getId(), object.getAuthor(), object.getImage_url()) ;
    }

    //minimo tener minSdk 24 para lo siguiente:
    @Override
    public List<PictureItemEntity> fromModel(List<PictureItem> object) {
        return object.stream().map(this::fromModel).collect(Collectors.toList());
    }

    @Override
    public List<PictureItem> toModel(List<PictureItemEntity> object) {
        return object.stream().map(this::toModel).collect(Collectors.toList());
    }


}
