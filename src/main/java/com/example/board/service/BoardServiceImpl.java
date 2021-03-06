package com.example.board.service;
import com.example.board.domain.BoardVO;
import com.example.board.domain.Criteria;
import com.example.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Log4j
//계층 구조상 주로 비즈니스 영역을 담당하는 객체임을 표시하기 위해 사용
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

    @Setter(onMethod_ = @Autowired)
    //spring 4.3 이상에서 자동처리
    private BoardMapper mapper;

    @Override
    public void register(BoardVO board){

        log.info("register..........." + board);

        mapper.insertSelectKey(board);

    }

   @Override
    public BoardVO get(Long bno){

        log.info("get......."+bno);

        return mapper.read(bno);

    }

    @Override
    public boolean modify(BoardVO board){

        log.info("modify......"+board);

        return mapper.update(board) == 1;

    }

    @Override
    public boolean remove(Long bno){

        log.info("remove......"+bno);

        return mapper.delete(bno) == 1;

    }

//    @Override
//    public List<BoardVO> getList(){
//
//        log.info("getList........");
//
//        return mapper.getList();
//
//    }

    @Override
    public List<BoardVO> getList(Criteria cri){
        log.info("get List with criteria: "+cri);
        return mapper.getListWithPaging(cri);
    }

    @Override
    public int getTotal(Criteria cri){

        log.info("get total count");
        return mapper.getTotalCount(cri);
    }

}
