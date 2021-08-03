package com.jihee.msub.test.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jihee.msub.test.domain.entity.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, Long> {

}
