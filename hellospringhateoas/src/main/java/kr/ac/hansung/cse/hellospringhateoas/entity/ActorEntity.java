package kr.ac.hansung.cse.hellospringhateoas.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/*
    @Builder
    Builder - 빌터 패턴을 구현 할 수 있음.
    객체를 생성하고 property값을 생성할 수 있음.
 */
@Getter
@Setter
@ToString(exclude = "albums")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="actor")
public class ActorEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String birthDate;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "actor_album",
            joinColumns = @JoinColumn(name = "actor_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private List<AlbumEntity> albums;

    public ActorEntity(String firstName, String lastName, String birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
}