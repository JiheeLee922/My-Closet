package com.jihee.msub.board.domain.repository.search;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;

import com.jihee.msub.board.domain.entity.BoardEntity;
import com.jihee.msub.board.domain.entity.QBoardEntity;
import com.jihee.msub.board.dto.BoardDTO;
import com.jihee.msub.member.domain.entity.QMemberEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;

@Service
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository{

	public SearchBoardRepositoryImpl() {
		super(BoardEntity.class);
	}

	@Override
	public Page<Object[]> searchBoard(Pageable page, String keyword, String type) {
		
		QBoardEntity qBoard = QBoardEntity.boardEntity;
		QMemberEntity qMember = QMemberEntity.memberEntity;
		
		JPQLQuery<BoardEntity> jpqlQuery = from(qBoard);
		jpqlQuery.leftJoin(qMember).on(qBoard.member.eq(qMember));
		
		//select로 가져오는 데이터는 하나의 엔티티가 아닌,여러 엔티티들의 데이터가 섞인 object인데 이걸 Tuple 객체로 추출할 수 있다.
		JPQLQuery<Tuple> tuple = jpqlQuery.select(qBoard, qMember);
		
		BooleanBuilder booleanBuilder = new BooleanBuilder();
		BooleanExpression exp = qBoard.id.gt(0); // AND (board.id > 0)
		booleanBuilder.and(exp);
		
		if(type != null) {
			String[] typeArr = type.split("");
			
			BooleanBuilder searchBuilder = new BooleanBuilder();
			
			for(String t : typeArr) {
				switch (t) {
				case "t":
					searchBuilder.or(qBoard.title.contains(keyword));
					break;
				case "w":
					searchBuilder.or(qBoard.writer.contains(keyword));
					break;
				case "c":
					searchBuilder.or(qBoard.content.contains(keyword));
					break;
				}
			}
			
			booleanBuilder.and(searchBuilder);
		}
		
		tuple.where(booleanBuilder);
		
		//sort
//		Sort sort = page.getSort();
//		sort.stream().forEach( order -> {
//			Order direction = order.isAscending() ? Order.ASC : Order.DESC;
//			String property = order.getProperty();
//			
//			PathBuilder<BoardEntity> orderByExpression = new PathBuilder<>(BoardEntity.class,  "board");
//			tuple.orderBy(new OrderSpecifier<BoardEntity>());
//		});
		tuple.orderBy(qBoard.writer.desc()); // 위에 소스로 하려했는데 잘 안돼서 이렇게 처리.
		
		long count = tuple.fetchCount();
		
		tuple.offset(page.getOffset());
		tuple.limit(page.getPageSize());

		List<Tuple> result = tuple.fetch();
		
		Page<Object[]> pages = new PageImpl<Object[]>(result.stream().map( t -> t.toArray())
															.collect(Collectors.toList())
															, page , count);
				
		
		return pages;
	}


	
	
}
