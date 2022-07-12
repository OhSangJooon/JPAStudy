package com.study.board;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.study.board.entity.Board;
import com.study.board.entity.BoardRepository;

@SpringBootTest
public class BoardTests {
    /* 테스트 방법
    * 1. 테스트할 메서드 명 더블 클릭
    * 2. 마우스 오른쪽 클릭
    * 3. Run (실행)
    * */
    @Autowired
    BoardRepository boardRepository;

    @Test
    void save() {
    // 게시글 저장
        // 1. 게시글 파라미터 생성
        Board params = Board.builder()          // Board 객체에서 @Builder 어노테이션으로 생성된 객체
                .title("1번 게시글 제목")
                .content("1번 게시글 내용")
                .writer("도뎡이")
                .hits(0)
                .deleteYn('N')
                .build();

        // 2. 게시글 저장
        boardRepository.save(params);           // insert문 실행

        // 3. 1번 게시글 정보 조회
        Board entity = boardRepository.findById((long) 1).get();
        assertThat(entity.getTitle()).isEqualTo("1번 게시글 제목");
        assertThat(entity.getContent()).isEqualTo("1번 게시글 내용");
        assertThat(entity.getWriter()).isEqualTo("도뎡이");
    }

    @Test
    void findAll() {

        // 1. 전체 게시글 수 조회
        long boardsCount = boardRepository.count();

        // 2. 전체 게시글 리스트 조회
        List<Board> boards = boardRepository.findAll();
    }

    @Test
    void delete() {

        /*
        * findById( )의 리턴 타입은 Optional<T>이라는 클래스인데요.
          옵셔널은 반복적인 NULL 처리를 피하기 위해 자바 8에서 최초로 도입된 클래스 (ex. 자바스크립트 옵셔널 체이닝)
        * */
        // 1. 게시글 조회
        Board entity = boardRepository.findById((long) 1).get();

        // 2. 게시글 삭제
        boardRepository.delete(entity);
    }

}