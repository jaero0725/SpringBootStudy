package kr.ac.hansung.cse.hellospringhateoas.repository;


import kr.ac.hansung.cse.hellospringhateoas.entity.ActorEntity;
import kr.ac.hansung.cse.hellospringhateoas.entity.AlbumEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AlbumRepository extends PagingAndSortingRepository<AlbumEntity, Long> {

}