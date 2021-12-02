package com.jihee.msub.board.domain.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {

	public Page<Object[]> searchBoard(Pageable page, String keyword, String type);
	
}
