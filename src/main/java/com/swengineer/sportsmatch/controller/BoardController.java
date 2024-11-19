package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.BoardDTO;
import com.swengineer.sportsmatch.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/member")
    public String member_findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.member_findAll();
        model.addAttribute("member_post_List",boardDTOList);
        return "member";
    }

    @GetMapping("/memberwrite")
    public String member_writeForm(){
        return "member_write";
    }

    @PostMapping("/memberwrite")
    public String member_write(@ModelAttribute BoardDTO boardDTO) {
        boardDTO.setPost_type("member");
        boardService.save(boardDTO);
        return "member";
    }

    @GetMapping("/member/{post_id}")
    public String member_findByPost_id(@PathVariable Long post_id, Model model){
        boardService.updateHits(post_id);
        BoardDTO boardDTO = boardService.findByPost_id(post_id);
        model.addAttribute("post_detail", boardDTO);
        return "member_detail";
    }

    @GetMapping("/memberupdate/{post_id}")
    public String member_updateForm(@PathVariable Long post_id, Model model){
        BoardDTO boardDTO = boardService.findByPost_id(post_id);
        model.addAttribute("post_update", boardDTO);
        return "member_update";
    }

    @PostMapping("/memberupdate")
    public String member_update(@ModelAttribute BoardDTO boardDTO, Model model){
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("post_detail", board);
        return "member_detail";
    }




    @GetMapping("/team")
    public String team_findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.team_findAll();
        model.addAttribute("team_post_List",boardDTOList);
        return "team";
    }

    @GetMapping("/teamwrite")
    public String team_writeForm(){
        return "team_write";
    }

    @PostMapping("/teamwrite")
    public String team_write(@ModelAttribute BoardDTO boardDTO) {
        boardDTO.setPost_type("team");
        boardService.save(boardDTO);
        return "team";
    }

    @GetMapping("/team/{post_id}")
    public String team_findByPost_id(@PathVariable Long post_id, Model model){
        boardService.updateHits(post_id);
        BoardDTO boardDTO = boardService.findByPost_id(post_id);
        model.addAttribute("post_detail", boardDTO);
        return "team_detail";
    }

    @GetMapping("/teamupdate/{post_id}")
    public String team_updateForm(@PathVariable Long post_id, Model model){
        BoardDTO boardDTO = boardService.findByPost_id(post_id);
        model.addAttribute("post_update", boardDTO);
        return "team_update";
    }

    @PostMapping("/teamupdate")
    public String team_update(@ModelAttribute BoardDTO boardDTO, Model model){
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("post_detail", board);
        return "team_detail";
    }
}
