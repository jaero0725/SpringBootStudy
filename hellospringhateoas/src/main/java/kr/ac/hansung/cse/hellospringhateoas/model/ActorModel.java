package kr.ac.hansung.cse.hellospringhateoas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Relation(collectionRelation = "actors", itemRelation = "actor")
@JsonInclude(JsonInclude.Include.NON_NULL) //null이면 빼라
public class ActorModel extends RepresentationModel<ActorModel> {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;

    private List<AlbumModel> albums;
}