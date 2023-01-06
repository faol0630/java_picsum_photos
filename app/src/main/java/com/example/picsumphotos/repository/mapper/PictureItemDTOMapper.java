package com.example.picsumphotos.repository.mapper;

import com.example.picsumphotos.data.model.PictureItem;
import com.example.picsumphotos.data.model.PictureItemDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PictureItemDTOMapper implements ObjectMapper<PictureItemDTO, PictureItem> {

    private static final PictureItemDTOMapper instance = new PictureItemDTOMapper();

    //private empty constructor
    public PictureItemDTOMapper() {
    }

    public static PictureItemDTOMapper getInstance(){
        return instance;
    }

    @Override
    public PictureItem toModel(PictureItemDTO object) {
        return new PictureItem(object.getId(), object.getAuthor(), object.getImage_url(), object.getWidth(), object.getHeight());
    }

    @Override
    public PictureItemDTO fromModel(PictureItem object) {
        PictureItemDTO pictureItemDTO = new PictureItemDTO();
        pictureItemDTO.setId(object.getId());
        pictureItemDTO.setAuthor(object.getAuthor());
        pictureItemDTO.setImage_url(object.getImage_url());
        pictureItemDTO.setWidth(object.getWidth());
        pictureItemDTO.setHeight(object.getHeight());
        return pictureItemDTO;
    }

    @Override
    public List<PictureItemDTO> fromModel(List<PictureItem> object) {
        return object.stream().map(this::fromModel).collect(Collectors.toList());
    }

    @Override
    public List<PictureItem> toModel(List<PictureItemDTO> object) {
        return object.stream().map(this::toModel).collect(Collectors.toList());
    }

}
