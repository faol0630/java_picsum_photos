package com.example.picsumphotos.repository.mapper;

import java.util.List;

public interface ObjectMapper<T, Model> {
    Model toModel(T object);
    T fromModel(Model object);
    List<T> fromModel(List<Model> object);
    List<Model> toModel(List<T> object);
}
//T puede ser el entity de room o el DTO de retrofit
//model viene siendo PictureItem(ni entity ni DTO)