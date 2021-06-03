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

// Class RepresentationModelAssemblerSupport<T,D extends RepresentationModel<D>>
@Component
public class AlbumModelAssembler
        extends RepresentationModelAssemblerSupport<AlbumEntity, AlbumModel> {

    // Creates a new RepresentationModelAssemblerSupport
    // using the given controller class and resource type.
    public AlbumModelAssembler() {
        super(WebController.class, AlbumModel.class);
    }

    // D toModel(T entity); Converts the given entity into a D, which extends RepresentationModel.
    @Override
    public AlbumModel toModel(AlbumEntity entity)
    {
        // Instantiates the resource object
        AlbumModel albumModel = instantiateModel(entity);

        albumModel.add( linkTo( methodOn(WebController.class).getAlbumById(entity.getId()) )
                .withSelfRel()   );

        albumModel.setId(entity.getId());
        albumModel.setTitle(entity.getTitle());
        albumModel.setDescription(entity.getDescription());
        albumModel.setReleaseDate(entity.getReleaseDate());
        albumModel.setActors(toActorModel(entity.getActors()));
        return albumModel;
    }

    // CollectionModel<D> toCollectionModel(Iterable<? extends T> entities)
    // Converts an Iterable or Ts into an Iterable of RepresentationModel and
    // wraps them in a CollectionModel instance.
    @Override
    public CollectionModel<AlbumModel> toCollectionModel(Iterable<? extends AlbumEntity> entities)
    {
        CollectionModel<AlbumModel> actorModels = super.toCollectionModel(entities);

        actorModels.add(linkTo(methodOn(WebController.class).getAllAlbums()).withSelfRel());

        return actorModels;
    }

    private List<ActorModel> toActorModel(List<ActorEntity> actors) {
        if (actors.isEmpty())
            return Collections.emptyList();

        return actors.stream()
                .map(actor -> ActorModel.builder()
                        .id(actor.getId())
                        .firstName(actor.getFirstName())
                        .lastName(actor.getLastName())
                        .build()
                        .add(linkTo(
                                methodOn(WebController.class)
                                        .getActorById(actor.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());
    }
}
