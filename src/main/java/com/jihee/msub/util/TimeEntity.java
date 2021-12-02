package com.jihee.msub.util;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass // 해당 어노테이션이 적용된 클래스는 테이블로 생성되지않음. 이 클래스를 상속받은 엔티티에 매핑되는 테이블에 아래 컬럼들이 생성된다. 부모 클래스로만 사용!
@EntityListeners(AuditingEntityListener.class) //JPA에서 엔티티객체에 어떤 변화(생성,변경)가 생기는 것을 감지하는 리스너. AuditingEntityListener 이용하려면 main 클래스에  @EnableJpaAuditing 추가해주기! 
public abstract class TimeEntity {
	@CreatedDate
	@Column(updatable = false)  //false는 생성된 컬럼값이 변경되지 않는다.
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
}
