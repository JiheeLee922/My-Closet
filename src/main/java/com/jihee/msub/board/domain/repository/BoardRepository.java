package com.jihee.msub.board.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jihee.msub.board.domain.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

}
