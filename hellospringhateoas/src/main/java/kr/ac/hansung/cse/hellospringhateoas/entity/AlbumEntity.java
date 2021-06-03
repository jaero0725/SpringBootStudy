package kr.ac.hansung.cse.hellospringhateoas.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString(exclude = "actors")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="album")
public class AlbumEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;
    private String releaseDate;

    @ManyToMany(mappedBy = "albums",fetch = FetchType.EAGER)
    private List<ActorEntity> actors;

    public AlbumEntity(String title, String description, String releaseDate) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
    }
}
