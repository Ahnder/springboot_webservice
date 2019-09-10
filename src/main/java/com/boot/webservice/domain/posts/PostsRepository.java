package com.boot.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

/**
 * 인테페이스 생성 후, JpaRepository<Entity 클래스, PK 타입> 를 상속하면,
 * 기본적인 CRUD 메서드가 자동 생성된다. 특별히 @Repository 를 추가할 필요 없다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p " +
            "FROM Posts p " +
            "ORDER BY p.id DESC")
    Stream<Posts> findAllDesc();

}
