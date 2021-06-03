package kr.ac.hansung.cse.hellospringhateoas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

// These are DTO objects which will be returned from controller classes as representation models.
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "albums", itemRelation = "album")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumModel extends RepresentationModel<AlbumModel> {
    private Long id;
    private String title;
    private String description;
    private String releaseDate;

    private List<ActorModel> actors;
}