package com.study.board.entity;

import org.springframework.data.jpa.repository.JpaRepository;

/*
* Repository는 MyBatis의 SQL Mapper와 유사한 퍼시스턴스 영역에 사용되는 인터페이스로

MyBatis의 Mapper를 JPA에서는 Repository로 부름.

+ 엔티티(Entity) 클래스와 레파지토리(Repository) 인터페이스는 꼭 같은 패키지에 위치해야 함.
* */
public interface BoardRepository extends JpaRepository<Board, Long> {

}
