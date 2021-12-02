package com.jihee.msub.board.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jihee.msub.board.domain.entity.BoardEntity;
import com.jihee.msub.board.domain.repository.BoardRepository;
import com.jihee.msub.board.domain.repository.search.SearchBoardRepositoryImpl;
import com.jihee.msub.board.dto.BoardDto;
import com.jihee.msub.member.domain.entity.MemberEntity;
import com.jihee.msub.member.dto.MemberDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {
	
	private BoardRepository boardRepository;
	private SearchBoardRepositoryImpl searchBoardRepository;
	
    private static final int BLOCK_PAGE_NUM_COUNT = 5; // 블럭에 존재하는 페이지 번호 수
    private static final int PAGE_POST_COUNT = 4; // 한 페이지에 존재하는 게시글 수
	
    
    
	@Transactional
	public Long savePost(BoardDto boardDto) {
		return boardRepository.save(boardDto.toEntity()).getId();
	}
	
	/**
	* <pre>
	* - 작성일		: 2021. 11. 04.
	* - 작성자		: leejh
	* - 설명			: JPA를 사용하여 게시판 페이징,검색 처리
	* </pre>
	*/
	@Transactional
	public List<BoardDto> getBoardList(Integer pageNum, String keyword){
		
		Page<BoardEntity> page = null;
		if(keyword == null) {
			page = boardRepository.findAll(PageRequest.of(pageNum -1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));
		}else {
			page = boardRepository.findByTitleContaining(keyword, PageRequest.of(pageNum -1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createdDate")));
		}
		
		List<BoardEntity> boardEntites =  page.getContent();
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		for (BoardEntity boardEntity : boardEntites) {
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }
		
		return boardDtoList;
	}

	
	/**
	* <pre>
	* - 작성일		: 2021. 12. 01.
	* - 작성자		: leejh
	* - 설명			: QueryDSL을 사용하여 게시판 페이징,검색 처리
	* </pre>
	*/
	@Transactional
	public List<BoardDto> getBoardListDsl(Integer pageNum, String keyword){
		
		Pageable pageable = PageRequest.of(0, 5, Sort.Direction.ASC, "writer");
		
		Page<Object[]> result = searchBoardRepository.searchBoard(pageable, "가", "t");
		List<BoardDto> boardDtoList = new ArrayList<>();
		
//		result.stream().forEach( o -> {
//			System.out.println(Arrays.toString(o));
//		});
		
		List<Object[]> boardList = result.getContent();
		
		
		for(Object[] board: boardList) {
			BoardDto b = this.convertEntityToDto((BoardEntity)board[0]);
			
			MemberEntity member = (MemberEntity)board[1];
			MemberDto memberDto =  MemberDto.builder()
			.id(member.getId())
			.email(member.getEmail())
			.nickname(member.getEmail())
			.build();
			
			b.setMember(memberDto);
			boardDtoList.add(b);
		}
		
		return boardDtoList;
	}
	
	
	@Transactional
    public BoardDto getPost(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDTO = BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();

        return boardDTO;
    }

		
    @Transactional
    public void deletePost(Long id) {
    	boardRepository.deleteById(id);
    }
    
    @Transactional
    public Long getBoardCount() {
    	return boardRepository.count();
    }

    @Transactional
    public Long getBoardCount(String keyword) {
    	return boardRepository.countByTitleContaining(keyword);
    }

    
    public Integer[] getPageList(Integer curPageNum, String keyword) {
    	Integer[] pageList = new Integer[BLOCK_PAGE_NUM_COUNT];
    	
    	Double postsTotalCount = 0.0; //총 게시글 수
    	if(keyword == null) {
    		postsTotalCount = Double.valueOf(this.getBoardCount());
    	}else {
    		postsTotalCount = Double.valueOf(this.getBoardCount(keyword));
    	}
    		
    	Integer totalLastPageNum = (int)(Math.ceil((postsTotalCount/PAGE_POST_COUNT)));	//마지막 페이지 번호 계산
    	curPageNum = (curPageNum <= 3 ) ? 1 : curPageNum -2;	//페이지 시작번호 조정
    	Integer blockLastPageNum = (totalLastPageNum > curPageNum + BLOCK_PAGE_NUM_COUNT -1)	//현재 페이지 기준 블럭의 마지막 페이지 번호 계산 
    					? curPageNum + BLOCK_PAGE_NUM_COUNT -1
    					: totalLastPageNum;
    	for(int val = curPageNum, idx = 0; val <= blockLastPageNum; val++, idx++) {  //페이지 번호 할당
    		pageList[idx] = val;
    	}
    	
    	return pageList;
    }
    
    private BoardDto convertEntityToDto(BoardEntity entity) {
    	return BoardDto.builder()
    			.id(entity.getId())
    			.title(entity.getTitle())
    			.content(entity.getContent())
    			.writer(entity.getWriter())
    			.createdDate(entity.getCreatedDate())
    			.build();
    }
    
    
}
