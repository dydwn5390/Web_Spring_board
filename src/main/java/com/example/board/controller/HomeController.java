package com.example.board.controller;

import com.example.board.mapper.BoardMapper;
import com.example.board.domain.BoardVO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
//초기화 되지 않은 final 필드나, @NonNull 이 붙은 필드에 대해 생성자를 생성해 줌
@RequiredArgsConstructor
public class HomeController {
    private final BoardMapper boardMapper;

    @RequestMapping(value = {"/", ""})
    public String index() {
        //int a = boardMapper.test();
        List<BoardVO> a = boardMapper.getList();
        log.info("{}", a);
        return "index";
    }
}
