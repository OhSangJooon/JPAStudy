package com.study.board.dto;
import com.study.board.entity.Board;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
* Entity 클래스는 테이블(Table) 또는 레코드(Record) 역할을 하는 데이터베이스 그 자체로 생각할 수 있고,
    절대로 요청(Request)이나 응답(Response)에 사용되어서는 안 되기 때문에
    반드시 Request, Response 클래스를 따로 생성(구분)해 주어야 한다.
* */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardRequestDto {

    private String title; // 제목
    private String content; // 내용
    private String writer; // 작성자
    private char deleteYn; // 삭제 여부

    /*
    * Entity 클래스는 절대로 요청(Request)에 사용되어서는 안 되기 때문에
        BoardRequestDto의 toEntity( ) 메서드를 이용해서 boardRepository의 save( ) 메서드를 실행
    * */
    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .hits(0)
                .deleteYn(deleteYn)
                .build();
    }

}