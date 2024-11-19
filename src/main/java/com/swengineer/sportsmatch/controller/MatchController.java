package com.swengineer.sportsmatch.controller;

import com.swengineer.sportsmatch.dto.MatchDTO;
import com.swengineer.sportsmatch.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    // 매칭 목록 조회
    @GetMapping
    public String getAllMatches(Model model) {
        List<MatchDTO> matchList = matchService.getAllMatches();
        model.addAttribute("matchList", matchList);
        return "match_list"; // templates/match_list.html
    }

    // 매칭 상세 조회
    @GetMapping("/{matchId}")
    public String getMatchDetail(@PathVariable Integer matchId, Model model) {
        MatchDTO match = matchService.getMatchDTOById(matchId);
        model.addAttribute("match_detail", match);
        return "match_detail"; // templates/match_detail.html
    }

    // 매칭 생성 폼 표시
    @GetMapping("/new")
    public String showCreateMatchForm(Model model) {
        model.addAttribute("matchDTO", new MatchDTO());
        return "match_create"; // templates/match_create.html
    }

    // 매칭 생성
    @PostMapping
    public String createMatch(@ModelAttribute MatchDTO matchDTO) {
        matchService.createMatch(matchDTO);
        return "redirect:/matches";
    }

    // 매칭 수정 폼 표시
    @GetMapping("/{matchId}/edit")
    public String showUpdateMatchForm(@PathVariable Integer matchId, Model model) {
        MatchDTO match = matchService.getMatchDTOById(matchId);
        model.addAttribute("match_update", match);
        return "match_update"; // templates/match_update.html
    }

    // 매칭 수정
    @PostMapping("/{matchId}/edit")
    public String updateMatch(@PathVariable Integer matchId, @ModelAttribute MatchDTO matchDTO) {
        matchService.updateMatch(matchId, matchDTO);
        return "redirect:/matches/" + matchId;
    }

    // 매칭 삭제
    @PostMapping("/{matchId}/delete")
    public String deleteMatch(@PathVariable Integer matchId) {
        matchService.deleteMatch(matchId);
        return "redirect:/matches";
    }
}
