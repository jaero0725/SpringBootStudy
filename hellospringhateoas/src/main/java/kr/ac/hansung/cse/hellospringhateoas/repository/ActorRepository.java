package kr.ac.hansung.cse.hellospringhateoas.repository;

import kr.ac.hansung.cse.hellospringhateoas.entity.ActorEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ActorRepository extends PagingAndSortingRepository<ActorEntity, Long> {

}