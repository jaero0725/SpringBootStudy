package kr.ac.hansung.cse.hellospringhateoas.assembler;

import kr.ac.hansung.cse.hellospringhateoas.controller.WebController;
import kr.ac.hansung.cse.hellospringhateoas.entity.ActorEntity;
import kr.ac.hansung.cse.hellospringhateoas.entity.AlbumEntity;
import kr.ac.hansung.cse.hellospringhateoas.model.ActorModel;
import kr.ac.hansung.cse.hellospringhateoas.model.AlbumModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ActorModelAssembler
        extends RepresentationModelAssemblerSupport<ActorEntity, ActorModel> {

    public ActorModelAssembler() {
        super(WebController.class, ActorModel.class);
    }

    //Entity 를 model 로 하나
    @Override
    public ActorModel toModel(ActorEntity entity)
    {
        ActorModel actorModel = instantiateModel(entity);

        actorModel.add( linkTo(methodOn(WebController.class).getActorById(entity.getId()))
                .withSelfRel() );

        actorModel.setId(entity.getId());
        actorModel.setFirstName(entity.getFirstName());
        actorModel.setLastName(entity.getLastName());
        actorModel.setBirthDate(entity.getBirthDate());
        actorModel.setAlbums(toAlbumModel(entity.getAlbums()));
        return actorModel;
    }

    //Entity 를 model 로 컬렉션 모델에
    @Override
    public CollectionModel<ActorModel> toCollectionModel(Iterable<? extends ActorEntity> entities)
    {
        CollectionModel<ActorModel> actorModels = super.toCollectionModel(entities);

        actorModels.add(linkTo(methodOn(WebController.class).getAllActors()).withSelfRel());

        return actorModels;
    }

    private List<AlbumModel> toAlbumModel(List<AlbumEntity> albums) {
        if (albums.isEmpty())
            return Collections.emptyList();

        return albums.stream()
                .map(album -> AlbumModel.builder()
                        .id(album.getId())
                        .title(album.getTitle())
                        .build()
                        .add(linkTo(methodOn(WebController.class).getAlbumById(album.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());
    }
}