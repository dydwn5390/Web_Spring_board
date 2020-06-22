package com.example.board.controller;

import com.example.board.domain.BoardVO;
import com.example.board.service.BoardService;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


//string의 bean으로 인식할 수 있게 함
@Controller
@Log4j
// '/board'로 시작하는 모든 처리를 BoardController가 하도록 지정함
@RequestMapping("/board/*")
//생성자 자동 생성 및 주입(만일 생성자를 만들지 않을 경우에는 @Setter(onMethod_={@Autowired}) 이용
@AllArgsConstructor
public class BoardController {

    private BoardService service;

    @GetMapping("/list")
    public void list(Model model){ //게시물의 목록을 Model에 담아 전달

        log.info("list");
        model.addAttribute("list",service.getList());
    }

    @PostMapping("/register")
    //등록 작업이 끝난 후 다시 목록 화면으로 이동하기 위해 RedirectAttribute를 파라미터로 지정
    public String register(BoardVO board, RedirectAttributes rttr){

        log.info("register: "+board);

        service.register(board);

        //추가적으로 새롭게 등록된 게시물의 번호를 같이 전달
        rttr.addFlashAttribute("result", board.getBno());

        //'redirect:'접두어 - 스프랑 MVC가 내부적으로 response.sendRedirect() 처리
        return "redirect:/board/list";

    }

    @GetMapping("/get")
    public void get(@RequestParam("bno") Long bno, Model model){

        log.info("/get");
        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO board, RedirectAttributes rttr){

        log.info("modify: " + board);

        if(service.modify(board)){
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr){

        log.info("remove..." + bno);
        if (service.remove(bno)){
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }
}
