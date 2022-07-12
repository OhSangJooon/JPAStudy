package com.study.board.model;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.board.dto.BoardRequestDto;
import com.study.board.dto.BoardResponseDto;
import com.study.board.entity.Board;
import com.study.board.entity.BoardRepository;
import com.study.exception.CustomException;
import com.study.exception.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {

    // JPA Repository 인터페이스
    private final BoardRepository boardRepository;

    /**
     * 게시글 생성
     */
    /* JPA를 사용한다면, 서비스(Service) 클래스에서 필수적으로 사용되어야 하는 어노테이션
        일반적으로 메서드 레벨에 선언하게 되며, 메서드의 실행, 종료, 예외를 기준으로
        각각 실행(begin), 종료(commit), 예외(rollback)를 자동으로 처리 */
    @Transactional
    public Long save(final BoardRequestDto params) {
    /*
      Entity 클래스는 절대로 요청(Request)에 사용되어서는 안 되기 때문에
        BoardRequestDto의 toEntity( ) 메서드를 이용해서 boardRepository의 save( ) 메서드를 실행
    * */
        Board entity = boardRepository.save(params.toEntity());
        return entity.getId();
    }

    /**
     * 게시글 리스트 조회
     */
    public List<BoardResponseDto> findAll() {

        Sort sort = Sort.by(Direction.DESC, "id", "createdDate");       // sort 객체는 ORDER BY id DESC, created_date DESC을 의미
        List<Board> list = boardRepository.findAll(sort);
        // 컬렉션 list에 Entity를 담은 뒤 각각의 Entity를 BoardResponseDto 타입으로 생성 후 리턴
        return list.stream().map(BoardResponseDto::new).collect(Collectors.toList());

        /* return 값에서 Stream API를 사용하지 않은 경우
        * List<BoardResponseDto> boardList = new ArrayList<>();
            for (Board entity : list) {
                boardList.add(new BoardResponseDto(entity));
            }
            return boardList;*/
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public Long update(final Long id, final BoardRequestDto params) {

        /* 메서드의 실행이 종료(commit)되면 update 쿼리가 자동으로 실행
        *  Entity를 조회하면 해당 Entity는 영속성 컨텍스트에 보관(포함)될 테고,
            영속성 컨텍스트에 포함된 Entity 객체의 값이 변경되면,
            트랜잭션(Transaction)이 종료(commit)되는 시점에 update 쿼리를 실행
            --> 이렇게 자동으로 쿼리가 실행되는 개념은 더티 체킹(Dirty Checking)
        * */

        Board entity = boardRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));
        entity.update(params.getTitle(), params.getContent(), params.getWriter());
        return id;
    }

}
