package com.jihee.msub.board.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jihee.msub.board.domain.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	Page<BoardEntity> findByTitleContaining(String keyword , Pageable paging);
	//Containing을 붙여주면 Like 검색이 된다
	
	Long countByTitleContaining(String keyword);
}
