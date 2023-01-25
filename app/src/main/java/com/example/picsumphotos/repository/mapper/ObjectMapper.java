package com.example.picsumphotos.repository.mapper;

import java.util.List;

public interface ObjectMapper<T, Model> {
    Model toModel(T object);
    T fromModel(Model object);
    List<T> fromModel(List<Model> object);
    List<Model> toModel(List<T> object);
}
//T can be the entity of room or the DTO of retrofit
//model: PictureItem