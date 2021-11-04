package com.jihee.msub.board.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jihee.msub.board.domain.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	Page<BoardEntity> findByTitleContaining(String keyword , Pageable paging);
	//Containing�� �ٿ��ָ� Like �˻��� �ȴ�
	
	Long countByTitleContaining(String keyword);
}
