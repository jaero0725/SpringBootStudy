package kr.ac.hansung.cse.hellospringhateoas.controller;

import kr.ac.hansung.cse.hellospringhateoas.assembler.ActorModelAssembler;
import kr.ac.hansung.cse.hellospringhateoas.assembler.AlbumModelAssembler;
import kr.ac.hansung.cse.hellospringhateoas.entity.ActorEntity;
import kr.ac.hansung.cse.hellospringhateoas.entity.AlbumEntity;
import kr.ac.hansung.cse.hellospringhateoas.model.ActorModel;
import kr.ac.hansung.cse.hellospringhateoas.model.AlbumModel;
import kr.ac.hansung.cse.hellospringhateoas.repository.ActorRepository;
import kr.ac.hansung.cse.hellospringhateoas.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebController {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ActorModelAssembler actorModelAssembler;

    @Autowired
    private AlbumModelAssembler albumModelAssembler;

    @GetMapping("/api/actors")
    public ResponseEntity<CollectionModel<ActorModel>> getAllActors() {
        List<ActorEntity> actorEntities = (List<ActorEntity>) actorRepository.findAll();

        return ResponseEntity.ok(actorModelAssembler.toCollectionModel(actorEntities));
//        return new ResponseEntity<>(
//                actorModelAssembler.toCollectionModel(actorEntities),
//                HttpStatus.OK);
    }

    // Optional

    //The map call is simply used to transform a value to some other value.

    // The orElse() method is used to retrieve the value wrapped inside an Optional instance.
    // It takes one parameter, which acts as a default value.
    // The orElse() method returns the wrapped value if it's present, and its argument otherwise:

    @GetMapping("/api/actors/{id}")
    public ResponseEntity<ActorModel> getActorById(@PathVariable("id") Long id) {
        return actorRepository.findById(id)
                .map(actorModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/api/albums")
    public ResponseEntity<CollectionModel<AlbumModel>> getAllAlbums() {
        List<AlbumEntity> albumEntities = (List<AlbumEntity>) albumRepository.findAll();

        return new ResponseEntity<>(
                albumModelAssembler.toCollectionModel(albumEntities),
                HttpStatus.OK);
    }

    @GetMapping("/api/albums/{id}")
    public ResponseEntity<AlbumModel> getAlbumById(@PathVariable("id") Long id) {
        return albumRepository.findById(id)
                .map(albumModelAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
